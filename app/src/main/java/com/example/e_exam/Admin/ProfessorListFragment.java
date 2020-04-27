package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class ProfessorListFragment extends Fragment {

    RecyclerView professorRecyclerViewList;
    RecyclerView.LayoutManager layoutManager6;
    ProfessorListRecyclerViewAdapter professorAdapterRecyclerView;
    ArrayList<Set_get_professor> professorArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.recycler_view,container,false);
        //add recycler view to show Professors list
        professorList();
        professorRecyclerViewList=(RecyclerView) v.findViewById(R.id.RecyclerViewId);
        professorRecyclerViewList.setHasFixedSize(true);
        layoutManager6=new LinearLayoutManager(getContext());
        professorRecyclerViewList.setLayoutManager(layoutManager6);
        professorAdapterRecyclerView=new ProfessorListRecyclerViewAdapter(getContext(),professorArrayList);
        professorRecyclerViewList.setAdapter(professorAdapterRecyclerView);

        return v;
    }
    private void professorList() {
        professorArrayList =new ArrayList<Set_get_professor> ();
        // Todo Admin who is responsible for adding  professors in  this system
        professorArrayList.add(new Set_get_professor("ahmed"));
        professorArrayList.add(new Set_get_professor("Loka"));
        professorArrayList.add(new Set_get_professor("spa"));
        professorArrayList.add(new Set_get_professor("ahmed"));


    }
}
