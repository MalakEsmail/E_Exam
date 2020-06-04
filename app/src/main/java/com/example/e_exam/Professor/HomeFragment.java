package com.example.e_exam.Professor;

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

public class HomeFragment extends Fragment {
    FragmentTransaction transaction;
    FragmentManager manager;
    String subject, phone;

    public HomeFragment(String subject, String phone) {
        this.subject = subject;
        this.phone = phone;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        LinearLayout profile = (LinearLayout) view.findViewById(R.id.profile);
        LinearLayout addChapter = (LinearLayout) view.findViewById(R.id.add_chapter);
        LinearLayout addQuestion = (LinearLayout) view.findViewById(R.id.add_question);
        LinearLayout examStructure = (LinearLayout) view.findViewById(R.id.exam_structure);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile1 = new Profile(phone);
                transaction.replace(R.id.frame_Subject_container, profile1);
                transaction.commit();
            }
        });

        addChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddChaptersFragment addChaptFragment = new AddChaptersFragment(subject, phone);
                transaction.replace(R.id.frame_Subject_container, addChaptFragment);
                transaction.commit();
            }
        });

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChaptersFragment showEditchpFragment = new ChaptersFragment(subject, phone);
                transaction.replace(R.id.frame_Subject_container, showEditchpFragment);
                transaction.commit();
            }
        });

        examStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamStructureFragment examStructureFragment = new ExamStructureFragment(subject, phone);
                transaction.replace(R.id.frame_Subject_container, examStructureFragment);
                transaction.commit();
            }
        });
        return view;
    }
}
