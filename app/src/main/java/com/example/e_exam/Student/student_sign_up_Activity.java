package com.example.e_exam.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;

import java.util.ArrayList;

public class student_sign_up_Activity extends AppCompatActivity {
    Button studentLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up_);
        //getting Login button for student
         studentLoginButton=(Button)findViewById(R.id.studentSignInButton);

        //spinner for departments
        Spinner spinner = findViewById(R.id.departments_spinner);

        //ToDO Admin who is responsible for adding available departments

        //Array for departments options
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CS");
        arrayList.add("SE");
        arrayList.add("IT");
        arrayList.add("IS");

        //Array adapter fpr departments spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);

        //set drop down on spinner department
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //spinner for levels option
        Spinner levels_spinner = findViewById(R.id.levels_spinner);

        //ToDO Admin who responsible for adding available levels
        //Array for levels options
        ArrayList<String> levelsArrayList = new ArrayList<>();
        levelsArrayList.add("one");
        levelsArrayList.add("two");
        levelsArrayList.add("three");
        levelsArrayList.add("four");

        //Array adapter fpr levels spinner
        ArrayAdapter<String> levelsarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levelsArrayList);

        //set drop down on spinner levels
        levelsarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levels_spinner.setAdapter(levelsarrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String DepartmentName = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + DepartmentName,Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        levels_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String levelNumber = parent.getItemAtPosition(position).toString();
               // Toast.makeText(parent.getContext(), "Selected: " + levelNumber,Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
       /* studentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(loginActivity.class,studentSubjectsActivity.class);
                startActivity(i);
            }
        });*/
    }


    public void gotoStudentSubjectsList(View view) {
        Intent i=new Intent(this, studentSubjectsActivity.class);
        startActivity(i);
    }
}
