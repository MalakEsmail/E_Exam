package com.example.e_exam.Student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;

public class ExamDegree extends AppCompatActivity {
    ExamPaperActivity examPaperActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_degree);
        Intent i=getIntent();
       int degree=i.getIntExtra("degree",0);
        TextView showDegree=(TextView)findViewById(R.id.degreeTextViewId);
        showDegree.setText("Your Exam Degree is "+degree);

    }
}
