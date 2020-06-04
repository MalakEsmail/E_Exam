package com.example.e_exam.Student;

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
import com.example.e_exam.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentLoginActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private Button login;
    private String email, password, uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        emailEt = findViewById(R.id.studentLoginEmailEditText);
        passwordEt = findViewById(R.id.studentLoginPasswordEditText);
        login = findViewById(R.id.studentSignInButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInputs();
            }
        });


    }

    private void validateInputs() {
        email = emailEt.getText().toString();
        password = passwordEt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Your Email..", Toast.LENGTH_SHORT).show();
        } else if (password.length() <= 6) {
            Toast.makeText(this, "Enter Valid Password ..", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot item : dataSnapshot.getChildren()) {
                            Users users = item.getValue(Users.class);
                            String emailInDb = users.getUserEmail();
                            String passInDb = users.getUserPassword();
                            if (email.equals(emailInDb)) {
                                if (password.equals(passInDb)) {
                                    Intent intent = new Intent(studentLoginActivity.this, studentSubjectsActivity.class);
                                    uId = users.getUserId();
                                    String departmentName = users.getDepartmentName();
                                    String levelName = users.getLevelName();
                                    intent.putExtra("uId", uId);
                                    intent.putExtra("departmentName", departmentName);
                                    intent.putExtra("levelName", levelName);

                                    startActivity(intent);
                                    passwordEt.setText("");
                                    emailEt.setText("");
                                } else {
                                    Toast.makeText(studentLoginActivity.this, "Password in not matching..Try Again !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void openStudentPage(View view) {
        Intent i = new Intent(this, studentSubjectsActivity.class);
        startActivity(i);
    }

    public void GotoStudentSignUpPage(View view) {
        Intent i = new Intent(this, student_sign_up_Activity.class);
        startActivity(i);
    }
}
