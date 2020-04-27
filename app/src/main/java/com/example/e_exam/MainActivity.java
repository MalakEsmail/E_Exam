package com.example.e_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.Admin.AdminLoginActivity;
import com.example.e_exam.Professor.professorLoginActivity;
import com.example.e_exam.Student.studentLoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAdminPage(View view) {

        Intent i=new Intent(this, AdminLoginActivity.class);
        startActivity(i);
    }

    public void gotoProfessorsPage(View view) {
        Intent intent=new Intent(this, professorLoginActivity.class);
        startActivity(intent);
    }

    public void goTostudentPage(View view) {
        Intent intent=new Intent(this, studentLoginActivity.class);
        startActivity(intent);
    }
}
