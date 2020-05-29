package com.example.e_exam.Admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSubjectFragement extends Fragment {
     DatabaseReference ref;
     Spinner levelSpinner;
     Spinner departmentSpinner;
     ValueEventListener listener;
     ArrayList<String> levelArrayList;
     ArrayAdapter<String> levelAdapter;
    ArrayList<String> departmentArrayList;
    ArrayAdapter<String> departmentAdapter;
    Button addSubject;
    EditText enterSubjectName;
    String subjectName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.add_subject,container,false);
        enterSubjectName=v.findViewById(R.id.add_Subject_EditText_id);
        addSubject=v.findViewById(R.id.add_subject_Button_id);
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSubjectName();
            }
        });
        ref= FirebaseDatabase.getInstance().getReference();

        //levels Spinner
        levelSpinner = v.findViewById(R.id.add_subject_level_spinner_id);
        levelArrayList=new ArrayList<>();
        levelAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,levelArrayList);
        retrieveLevelData();
        levelSpinner.setAdapter(levelAdapter);


        //Departments Spinner
        departmentSpinner = v.findViewById(R.id.add_subject_department_spinner_id);
        departmentArrayList=new ArrayList<>();
        departmentAdapter=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,departmentArrayList);
        retrieveDepartmentsData();
        departmentSpinner.setAdapter(departmentAdapter);

        return v;
    }

    private void validateSubjectName() {
        subjectName=enterSubjectName.getText().toString();
        if(TextUtils.isEmpty(subjectName)){
            Toast.makeText(getContext(), "Please Enter Subject Name", Toast.LENGTH_SHORT).show();
        }
        else{
            String level = levelSpinner.getSelectedItem().toString();
            String department=departmentSpinner.getSelectedItem().toString();
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("subjectName",subjectName);
            ref.child("Subjects").child(level).child(department).push().updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    enterSubjectName.setText("");
                    Toast.makeText(getContext(), "Subject Added Successfully", Toast.LENGTH_SHORT).show();
           ref.child("subjectList").push().setValue(subjectName);
                }
            });

        }
    }


    public  void  retrieveLevelData(){
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

    public void retrieveDepartmentsData() {
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
