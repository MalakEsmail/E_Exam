package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_exam.R;

import java.util.ArrayList;

public class show_edit_subject_fragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //inflate view as set page show or edit student at frameLayout place
       View v=inflater.inflate(R.layout.admin_show_or_edit_subject,container,false);
       //Add spinner for levels
        Spinner levelSpinner=v.findViewById(R.id.show_edit_subject_level_spinner_id);
        ArrayList<String> levelArray = new ArrayList<>();
        levelArray.add("Level_one");
        levelArray.add("Level_two");
        levelArray.add("Level_three");
        levelArray.add("Level_four");
        ArrayAdapter<String> levelArrayAdapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, levelArray);
        levelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelArrayAdapter);
        //Add spinner for departments
        Spinner departmentSpinner=v.findViewById(R.id.show_edit_subject_department_spinner_id);
        ArrayList<String> departmentArray = new ArrayList<>();
        departmentArray.add("cs");
        departmentArray.add("it");
        departmentArray.add("se");
        departmentArray.add("is");
        ArrayAdapter<String> departmentArrayAdapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, departmentArray);
        departmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentArrayAdapter);
        Button showSubject=v.findViewById(R.id.show_subjects_Button_id);
        showSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new subjects_list_fragement();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

}
