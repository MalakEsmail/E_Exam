package com.example.e_exam.Student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class studentSubjectsActivity extends AppCompatActivity {
    RecyclerView studentSubjectsRecyclerView;
    RecyclerView.LayoutManager layoutManager1;
    student_subject_class_adapter adapterRecyclerLiner1;
    ArrayList<student_set_get_subjects> studentSubjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subjects);
        linerStudentSubjectList();
        studentSubjectsRecyclerView =findViewById(R.id.recycler_view_Student_subject_listView);
        studentSubjectsRecyclerView.setHasFixedSize(true);
        layoutManager1=new LinearLayoutManager(this);
        studentSubjectsRecyclerView.setLayoutManager(layoutManager1);
        adapterRecyclerLiner1 =new student_subject_class_adapter(this,studentSubjectList);
        studentSubjectsRecyclerView.setAdapter(adapterRecyclerLiner1);

    }
    //list subject names to be shown for students

    private void linerStudentSubjectList() {
        studentSubjectList =new ArrayList<student_set_get_subjects>();
        // Todo Admin who is responsible for adding to each student his subjects depending on his level and department
        studentSubjectList.add(new student_set_get_subjects("First Subject "));
        studentSubjectList.add(new student_set_get_subjects("second Subject "));
        studentSubjectList.add(new student_set_get_subjects("third Subject "));

    }
}
