package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_exam.R;

public class AdminHomeFragment extends Fragment {
    FragmentTransaction transaction;
    FragmentManager manager;

    public AdminHomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_home_fragmnet, container, false);

        LinearLayout levels = (LinearLayout) view.findViewById(R.id.levels);
        LinearLayout departments = (LinearLayout) view.findViewById(R.id.departments);
        LinearLayout subjects = (LinearLayout) view.findViewById(R.id.subjects);
        LinearLayout professors = (LinearLayout) view.findViewById(R.id.professors);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelsFragment levelsFragment = new LevelsFragment();
                transaction.replace(R.id.frame_container, levelsFragment);
                transaction.commit();
            }
        });
        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmemtsFragment departmemtsFragment = new DepartmemtsFragment();
                transaction.replace(R.id.frame_container, departmemtsFragment);
                transaction.commit();
            }
        });
        subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectssFragmets subjectssFragmets = new SubjectssFragmets();
                transaction.replace(R.id.frame_container, subjectssFragmets);
                transaction.commit();
            }
        });
        professors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfessorssFragment professorssFragment = new ProfessorssFragment();
                transaction.replace(R.id.frame_container, professorssFragment);
                transaction.commit();
            }
        });

        return view;
    }
}
