package com.example.e_exam.Professor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;

public class professorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_login);

    }

    public void openProfessorPage(View view) {
        Intent i=new Intent(this,ProfessorActivity.class);
        startActivity(i);
    }

    public void GotoProfessorSignUpPage(View view) {
        Intent i=new Intent(this,ProfessorSignUpActivity.class);
        startActivity(i);
    }
}
