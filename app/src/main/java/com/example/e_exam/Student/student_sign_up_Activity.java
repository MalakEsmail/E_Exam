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
import com.example.e_exam.model.Departments;
import com.example.e_exam.model.Levels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class student_sign_up_Activity extends AppCompatActivity {
    private EditText nameEt, emailEt, passwordEt, confirmPasswordEt;
    private MaterialSpinner departmentSpinner, levelSpinner;

    private Button signUp;
    private DatabaseReference reference;
    private List<String> departmentsList, levelsList;

    private String name, email, password, confirmPassword, departmentName, levelName, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up_);
        //departments Spinner
        departmentSpinner = findViewById(R.id.departments_spinner);
        fillDepartmentSpinner();
        //Levels Spinner
        levelSpinner = findViewById(R.id.levels_spinner);
        fillLevelsSpinner();

        nameEt = findViewById(R.id.studentUserNameEditText);
        emailEt = findViewById(R.id.studentEmailEditText);
        passwordEt = findViewById(R.id.studentPasswordEditText);
        confirmPasswordEt = findViewById(R.id.studentConfirmPasswordEditText);

        getSelectedDept();
        getSelectedLevel();

        signUp = findViewById(R.id.studentSignUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInputs();
            }
        });

    }


    private void fillDepartmentSpinner() {
        departmentsList = new ArrayList();
        reference = FirebaseDatabase.getInstance().getReference().child("Departments");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        Departments departments = item.getValue(Departments.class);
                        departmentsList.add(departments.getDepartmentName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        departmentSpinner.setHint("Departments");
        departmentSpinner.setItems(departmentsList);
    }

    private void fillLevelsSpinner() {
        levelsList = new ArrayList();
        reference = FirebaseDatabase.getInstance().getReference().child("Levels");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        Levels levels = item.getValue(Levels.class);
                        levelsList.add(levels.getLevelName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        levelSpinner.setHint("Levels");
        levelSpinner.setItems(levelsList);
    }

    private void getSelectedDept() {
        departmentSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                departmentName = item.toString();
            }
        });
    }

    private void getSelectedLevel() {
        levelSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                levelName = item.toString();
            }
        });
    }


    private void validateInputs() {
        name = nameEt.getText().toString();
        email = emailEt.getText().toString();
        password = passwordEt.getText().toString();
        confirmPassword = confirmPasswordEt.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter Your Name..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Your Email..", Toast.LENGTH_SHORT).show();
        } else if (password.length() <= 6) {
            Toast.makeText(this, "Enter Valid Password ..", Toast.LENGTH_SHORT).show();
        } else if (!(confirmPassword.equals(password))) {
            Toast.makeText(this, "Password Not Matching !", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(departmentName)) {
            Toast.makeText(this, "please.. select your department ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(levelName)) {
            Toast.makeText(this, "please.. select your Level ", Toast.LENGTH_SHORT).show();
        } else {
            setToAuth();

        }
    }

    private void setToAuth() {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser != null;
                    userId = firebaseUser.getUid();
                    saveToDB();
                }
            }
        });
    }

    private void saveToDB() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
        HashMap hashMap = new HashMap<>();
        hashMap.put("userName", name);
        hashMap.put("userEmail", email);
        hashMap.put("userPassword", password);
        hashMap.put("userId", userId);
        hashMap.put("departmentName", departmentName);
        hashMap.put("levelName", levelName);


        reference.child(userId).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getBaseContext(), "Done successfully..", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(student_sign_up_Activity.this, studentLoginActivity.class);
                startActivity(i);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Check your Internet Connection And Try Again ..", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
