package com.example.e_exam.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.MainActivity;
import com.example.e_exam.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExamDegree extends AppCompatActivity {
    private String userId, subject;
    private DatabaseReference degreeRef;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_degree);
        Intent i = getIntent();
        int degree = i.getIntExtra("degree", 0);
        userId = i.getStringExtra("id");
        subject = i.getStringExtra("subject");

        degreeRef = FirebaseDatabase.getInstance().getReference();
        degreeRef.child("Degrees").child(userId).child(subject).setValue(degree).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "check your Internet connection !", Toast.LENGTH_SHORT).show();

            }
        });
        TextView showDegree = (TextView) findViewById(R.id.degreeTextViewId);
        showDegree.setText("Your Exam Degree is " + degree);

        finish = findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(ExamDegree.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(ExamDegree.this, MainActivity.class);
        startActivity(i);
    }
}
