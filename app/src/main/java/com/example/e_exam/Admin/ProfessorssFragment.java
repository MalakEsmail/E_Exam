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

public class ProfessorssFragment extends Fragment {
    FragmentTransaction transaction;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_professorss, container, false);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        LinearLayout requests = (LinearLayout) view.findViewById(R.id.requests);
        LinearLayout addProfessorSubjects = (LinearLayout) view.findViewById(R.id.add_professor_subjectss);

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestsFragement proRequestsFragement = new RequestsFragement();
                transaction.replace(R.id.frame_container, proRequestsFragement);
                transaction.commit();
            }
        });
        addProfessorSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfessorsListFragment professorsFragement = new ProfessorsListFragment();
                transaction.replace(R.id.frame_container, professorsFragement);
                transaction.commit();
            }
        });

        return view;
    }
}
