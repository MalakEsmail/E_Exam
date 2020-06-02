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

public class DepartmemtsFragment extends Fragment {
    FragmentTransaction transaction;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_departmentss, container, false);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        LinearLayout addDepartment = (LinearLayout) view.findViewById(R.id.add_departmentss);
        LinearLayout allDepartments = (LinearLayout) view.findViewById(R.id.all_departmentss);

        addDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDepartment AddDepartmentFragement = new AddDepartment();
                transaction.replace(R.id.frame_container, AddDepartmentFragement);
                transaction.commit();
            }
        });
        allDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentFragment departmentFragment = new DepartmentFragment();
                transaction.replace(R.id.frame_container, departmentFragment);
                transaction.commit();
            }
        });

        return view;
    }
}
