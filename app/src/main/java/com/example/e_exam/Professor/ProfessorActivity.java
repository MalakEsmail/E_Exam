package com.example.e_exam.Professor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class ProfessorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter adapterRecyclerLiner;
    ArrayList<pojo>  list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        linerList();
        recyclerView =findViewById(R.id.recycler_view_liner);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterRecyclerLiner =new MyAdapter(this,list);
        recyclerView.setAdapter(adapterRecyclerLiner);

    }
    //list subject names to be shown for each professor
    private void linerList() {
        list =new ArrayList<pojo>();
        // Todo Admin who is responsible for adding to each professor his subjects
        list.add(new pojo("First Subject "));
        list.add(new pojo("Second Subject "));
        list.add(new pojo("Third Subject "));



    }
}
