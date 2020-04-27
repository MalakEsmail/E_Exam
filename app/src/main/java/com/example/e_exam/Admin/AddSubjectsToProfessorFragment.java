package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_exam.R;

import java.util.ArrayList;

public class AddSubjectsToProfessorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_subjects_to_professors,container,false);
        Spinner professorSpinner=view.findViewById(R.id.prof_spinner);
        //Handle professor spinner
        final ArrayList professorArrayList=new ArrayList();
        professorArrayList.add("Ahmed");
        professorArrayList.add("Malak");
        professorArrayList.add("noor");
        professorArrayList.add("alaa");
        ArrayAdapter professorArrayAdapter=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,professorArrayList);
        professorSpinner.setAdapter(professorArrayAdapter);
        professorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Selected: " + professorArrayList.get(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner subjectsSpinner=view.findViewById(R.id.sub_spinner);
        //Handle Subjects spinner
        final ArrayList subjectsArrayList=new ArrayList();
        subjectsArrayList.add("history");
        subjectsArrayList.add("law");
        subjectsArrayList.add("maths");
        subjectsArrayList.add("science");
        ArrayAdapter subjectArrayAdapter=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,subjectsArrayList);
        subjectsSpinner.setAdapter(subjectArrayAdapter);
        subjectsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Selected: " + subjectsArrayList.get(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Handle button Add
        Button AddsubToProf=(Button)view.findViewById(R.id.addSubjectsToProfessorsButton);
        AddsubToProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "subject Added " ,Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
