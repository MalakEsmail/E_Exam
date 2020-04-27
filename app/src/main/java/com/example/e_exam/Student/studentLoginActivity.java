package com.example.e_exam.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;

public class studentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
    }

    public void openStudentPage(View view) {
        Intent i=new Intent(this, studentSubjectsActivity.class);
        startActivity(i);
    }

    public void GotoStudentSignUpPage(View view) {
        Intent i=new Intent(this, student_sign_up_Activity.class);
        startActivity(i);
    }
}
