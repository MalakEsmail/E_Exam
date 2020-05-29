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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class show_edit_subject_fragment  extends Fragment {
    DatabaseReference ref;
    Spinner levelSpinner;
    Spinner departmentSpinner;
    ValueEventListener listener;
    ArrayList<String> levelArrayList;
    ArrayAdapter<String> levelAdapter;
    ArrayList<String> departmentArrayList;
    ArrayAdapter<String> departmentAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.admin_show_or_edit_subject,container,false);

        ref= FirebaseDatabase.getInstance().getReference();

        Button showSubject=v.findViewById(R.id.show_subjects_Button_id);


        //levels Spinner
        levelSpinner = v.findViewById(R.id.show_edit_subject_level_spinner_id);
        levelArrayList=new ArrayList<>();
        levelAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,levelArrayList);
        retrieveLevelData();
        levelSpinner.setAdapter(levelAdapter);

        //Departments Spinner
        departmentSpinner = v.findViewById(R.id.show_edit_subject_department_spinner_id);
        departmentArrayList=new ArrayList<>();
        departmentAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,departmentArrayList);
        retrieveDepartmentsData();
        departmentSpinner.setAdapter(departmentAdapter);

        showSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //send data from fragment to another fragment
              moveToNextFragment();
                   }
        });
        return v;
    }

    private void moveToNextFragment() {

        String level = levelSpinner.getSelectedItem().toString();
        String department=departmentSpinner.getSelectedItem().toString();


        Fragment fragment = new SubjectsListFragment(level,department);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();

    }


    private void retrieveLevelData() {
        listener=ref.child("levelName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item: dataSnapshot.getChildren()){
                    levelArrayList.add(item.getValue().toString());
                }
                levelAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void retrieveDepartmentsData() {
        listener=ref.child("departmentName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item: dataSnapshot.getChildren()){
                    departmentArrayList.add(item.getValue().toString());
                }
                departmentAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
