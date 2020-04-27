package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_exam.R;

import java.util.ArrayList;

public class AddSubjectFragement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // add add subject layout at fragment
        View v=inflater.inflate(R.layout.add_subject,container,false);
        Spinner s = v.findViewById(R.id.add_subject_level_spinner_id);
        //Array for levels options
        //TODO levels will taken from levels that admin added
        ArrayList<String> arrList = new ArrayList<>();
        arrList.add("Level_one");
        arrList.add("Level_two");
        arrList.add("Level_three");
        arrList.add("Level_four");
        //Array adapter fpr levels spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(arrayAdapter);
        //  add department options to spinner
        Spinner spi = v.findViewById(R.id.add_subject_department_spinner_id);
        //Array for departments options
        //TODO departments will taken from departments that admin added

        ArrayList<String> arr = new ArrayList<>();
        arr.add("cs");
        arr.add("it");
        arr.add("se");
        arr.add("is");
        //Array adapter fpr departments spinner
        ArrayAdapter<String> arradapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(arradapter);

        return v;
    }
}
