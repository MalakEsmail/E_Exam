package com.example.e_exam.Professor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfessorSignUpActivity extends AppCompatActivity {
    EditText nameEditText,phoneEditText,passwordEditText,confirmPasswordEditText;
    Button buttonSignUp;
    String name,phone,password,confirmPassword;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_sign_up);
          //get reference to Firebase Database

        //get Views from Layout
        nameEditText=(EditText)findViewById(R.id.professorUserNameEditText);
        phoneEditText=(EditText)findViewById(R.id.ProfessorPhoneEditText);
        passwordEditText=(EditText)findViewById(R.id.professorPasswordEditText);
        confirmPasswordEditText=(EditText)findViewById(R.id.ProfessorConfirmPasswordEditText);
        buttonSignUp=(Button) findViewById(R.id.professorSignUpButton);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nameEditText.getText().toString();
                phone=phoneEditText.getText().toString();
                password=passwordEditText.getText().toString();
                confirmPassword=confirmPasswordEditText.getText().toString();
                validation();
            }
        });

    }

    private void validation() {
        if(TextUtils.isEmpty(name)){
            Toast.makeText(ProfessorSignUpActivity.this, " Please..Enter your name ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(ProfessorSignUpActivity.this, " Please..Enter your Phone ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(ProfessorSignUpActivity.this, " Please..Enter your password ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(ProfessorSignUpActivity.this, " Please..repeat your password", Toast.LENGTH_SHORT).show();

        }
        else if (!(confirmPassword.equals(password))){
            Toast.makeText(ProfessorSignUpActivity.this, " password not matches", Toast.LENGTH_SHORT).show();

        }else{
          setToFireBase();
        }
    }

    private void setToFireBase() {
        ref= FirebaseDatabase.getInstance().getReference().child("Professors").child("Requests");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(phone).exists()){
                    Toast.makeText(ProfessorSignUpActivity.this, "This professor is  already exist ", Toast.LENGTH_LONG).show();
                    nameEditText.setText("");
                    phoneEditText.setText("");
                    passwordEditText.setText("");
                    confirmPasswordEditText.setText("");


                }
                else{
                    HashMap<String, Object> professorData = new HashMap<>();
                    professorData.put("name",name);
                    professorData.put("password",password);
                    professorData.put("phone",phone);
                    ref.child(phone).updateChildren(professorData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ProfessorSignUpActivity.this, "Congratulation professor "+name+"  , your account has been created.", Toast.LENGTH_LONG).show();
                                   nameEditText.setText("");
                                phoneEditText.setText("");
                                passwordEditText.setText("");
                                confirmPasswordEditText.setText("");


                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
