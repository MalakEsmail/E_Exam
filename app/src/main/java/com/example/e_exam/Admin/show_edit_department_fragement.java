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

public class show_edit_department_fragement extends Fragment {
    RecyclerView departmentNamesRecyclerViewList;
    RecyclerView.LayoutManager layoutManager3;
    departmentRecycleAdapter departmentNameAdapterRecyclerView;
    ArrayList<set_get_department_name> departmentNameArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.department_recycler_view_list,container,false);

        //Add level recyclerView List
        departmentNamesList();
        departmentNamesRecyclerViewList=(RecyclerView) v.findViewById(R.id.departmentRecyclerViewId);
        departmentNamesRecyclerViewList.setHasFixedSize(true);
        layoutManager3=new LinearLayoutManager(getContext());
        departmentNamesRecyclerViewList.setLayoutManager(layoutManager3);
        departmentNameAdapterRecyclerView=new departmentRecycleAdapter(getContext(),departmentNameArrayList);
        departmentNamesRecyclerViewList.setAdapter(departmentNameAdapterRecyclerView);

        return v;

    }
    private void departmentNamesList() {
        departmentNameArrayList =new ArrayList<set_get_department_name>();
        // Todo Admin who is responsible for adding  departments in  this system
        departmentNameArrayList.add(new set_get_department_name("cs"));
        departmentNameArrayList.add(new set_get_department_name("it"));
        departmentNameArrayList.add(new set_get_department_name("se"));
        departmentNameArrayList.add(new set_get_department_name("none"));


    }
}
