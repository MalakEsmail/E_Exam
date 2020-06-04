package com.example.e_exam;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.e_exam.Admin.AdminLoginActivity;
import com.example.e_exam.Professor.professorLoginActivity;
import com.example.e_exam.Student.studentLoginActivity;

public class MainActivity extends AppCompatActivity {
    private Button student, admin, professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = findViewById(R.id.studentButton);
        professor = findViewById(R.id.professorButton);
        admin = findViewById(R.id.adminButton);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);

        student.setTypeface(typeface);
        admin.setTypeface(typeface);
        professor.setTypeface(typeface);


    }

    public void goToAdminPage(View view) {

        Intent i = new Intent(this, AdminLoginActivity.class);
        startActivity(i);
    }

    public void gotoProfessorsPage(View view) {
        Intent intent = new Intent(this, professorLoginActivity.class);
        startActivity(intent);
    }

    public void goTostudentPage(View view) {
        Intent intent = new Intent(this, studentLoginActivity.class);
        startActivity(intent);
    }
}
