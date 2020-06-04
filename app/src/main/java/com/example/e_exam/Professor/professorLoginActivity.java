package com.example.e_exam.Professor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class professorLoginActivity extends AppCompatActivity {
    EditText phoneEditText, passwordEditText;
    Button buttonLogin;
    String phone, password;
    DatabaseReference ref;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_login);
        phoneEditText = (EditText) findViewById(R.id.ProfessorLoginPhoneEditTextId);
        passwordEditText = (EditText) findViewById(R.id.ProfessorLoginPasswordEditTextId);
        buttonLogin = (Button) findViewById(R.id.ProfessorSignInButtonId);
        loadingBar = new ProgressDialog(this);

        ref = FirebaseDatabase.getInstance().getReference().child("Professors").child("ProfessorsList");


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    private void validation() {
        phone = phoneEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(professorLoginActivity.this, " Please..Enter your Phone ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(professorLoginActivity.this, "Please..Enter your password ", Toast.LENGTH_SHORT).show();
        } else {
            checkInRequestsOrNot();
        }
    }

    private void checkInRequestsOrNot() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Professors").child("Requests");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String phoneRequest = String.valueOf(dataSnapshot.child(phone).child("phone").getValue());
                if (dataSnapshot.child(phoneRequest).exists()) {
                    // wait to check is phone number is available in database
                    loadingBar.setTitle("Login Account");
                    loadingBar.setMessage("please wait Until Your Request has been Approved.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                } else {
                    openProfessorPage();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void openProfessorPage() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //professor information
                String dbpassword = String.valueOf(dataSnapshot.child(phone).child("password").getValue());
                if (dataSnapshot.child(phone).exists()) {
                    if (dbpassword.equals(password)) {
                        loadingBar.dismiss();
                        Intent i = new Intent(professorLoginActivity.this, ProfessorActivity.class);
                        i.putExtra("phone", phone);
                        startActivity(i);
                        finish();
                        phoneEditText.setText("");
                        passwordEditText.setText("");
                    } else {
                        Toast.makeText(professorLoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(professorLoginActivity.this, "Not Found this Professor ,Please..Sign Up First !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void GotoProfessorSignUpPage(View view) {
        Intent i = new Intent(this, ProfessorSignUpActivity.class);
        startActivity(i);
    }
}
