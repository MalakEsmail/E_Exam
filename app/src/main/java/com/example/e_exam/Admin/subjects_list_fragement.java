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

public class subjects_list_fragement extends Fragment {
    RecyclerView subjectNamesRecyclerViewList;
    RecyclerView.LayoutManager layoutManager4;
    show_edit_subject_recyclerview_adapter subjectNameAdapterRecyclerView;
    ArrayList<set_get_subject> subjectNameArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.subject_recycler_view,container,false);
        //add recycler view to show subjects list
        subjectNamesList();
        subjectNamesRecyclerViewList=(RecyclerView) v.findViewById(R.id.showSubjectsRecyclerViewId);
        subjectNamesRecyclerViewList.setHasFixedSize(true);
        layoutManager4=new LinearLayoutManager(getContext());
        subjectNamesRecyclerViewList.setLayoutManager(layoutManager4);
        subjectNameAdapterRecyclerView=new show_edit_subject_recyclerview_adapter(getContext(),subjectNameArrayList);
        subjectNamesRecyclerViewList.setAdapter(subjectNameAdapterRecyclerView);

        return v;
    }
    //method date set at recyclerView list
    private void subjectNamesList() {
        subjectNameArrayList =new ArrayList<set_get_subject>();
        // Todo Admin who is responsible for adding  subjects in  this system
        subjectNameArrayList.add(new set_get_subject("Subject_one"));
        subjectNameArrayList.add(new set_get_subject("Subject_two"));
        subjectNameArrayList.add(new set_get_subject("Subject_three"));
        subjectNameArrayList.add(new set_get_subject("Subject_four"));


    }
}
