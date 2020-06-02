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

public class SubjectssFragmets extends Fragment {
    FragmentTransaction transaction;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_subjectss, container, false);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        LinearLayout addSubject = (LinearLayout) view.findViewById(R.id.add_subject);
        LinearLayout allSubjects = (LinearLayout) view.findViewById(R.id.all_subjects);

        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSubjectFragement ADDSubjectFragement = new AddSubjectFragement();
                transaction.replace(R.id.frame_container, ADDSubjectFragement);
                transaction.commit();
            }
        });
        allSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_edit_subject_fragment Show_edit_SubjectFragment = new show_edit_subject_fragment();
                transaction.replace(R.id.frame_container, Show_edit_SubjectFragment);
                transaction.commit();
            }
        });

        return view;
    }
}
