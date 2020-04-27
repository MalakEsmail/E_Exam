package com.example.e_exam.Admin;

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

public class AdminLoginActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText;
    Button buttonLogin;
    DatabaseReference databaseReference;
     String email;
    String password;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        emailEditText=(EditText)findViewById(R.id.adminEmailEditTextId);
        passwordEditText=(EditText)findViewById(R.id.adminPasswordEditTextId);
        buttonLogin=(Button) findViewById(R.id.adminLoginButtonId);

        loadingBar = new ProgressDialog(this);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admins");
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailEditText.getText().toString();
                password=passwordEditText.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(AdminLoginActivity.this, " Please..Enter your Email ", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(AdminLoginActivity.this, "Please..Enter your password ", Toast.LENGTH_SHORT).show();
                }else{
                    // wait to check is phone number is available in database
                    loadingBar.setTitle("Login Account");
                    loadingBar.setMessage("please wait , while we are checking the credentials .");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    openAdminPage();
                }
            }
        });
    }
        private void openAdminPage() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("1").exists() || dataSnapshot.child("2").exists()){
                    //first Admin Email and password
                    String dbEmail= String.valueOf(dataSnapshot.child("1").child("email").getValue());
                    String dbpassword= String.valueOf(dataSnapshot.child("1").child("password").getValue());
                    //second Admin Email and password
                    String dbSecondEmail= String.valueOf(dataSnapshot.child("2").child("email").getValue());
                    String dbSecondpassword= String.valueOf(dataSnapshot.child("2").child("password").getValue());

                    if(dbEmail.equals(email)&& dbpassword.equals(password) || (dbSecondEmail.equals(email)&& dbSecondpassword.equals(password))){
                        Toast.makeText(AdminLoginActivity.this, "logging successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                            Intent i=new Intent(AdminLoginActivity.this,AdminActivity.class);
                            startActivity(i);
                    }
                    else{
                        Toast.makeText(AdminLoginActivity.this, " someThing Wrong ", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    }
                }
                else{
                    Toast.makeText(AdminLoginActivity.this, "not found this Admin", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
