package com.example.e_exam.Professor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_exam.R;

public class AddQuestionFragment extends Fragment {
    String subject, chapter, phone;

    public AddQuestionFragment(String subject, String chapter, String phone) {
        this.subject = subject;
        this.chapter = chapter;
        this.phone = phone;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_question, container, false);

        //Button to set MCQ Question
        Button setQuestion = (Button) view.findViewById(R.id.set_question_Button_id);
        setQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction tansaction = manager.beginTransaction();
                Fragment setQuestionFragment = new SetQuestionFragment(subject, chapter,phone);
                tansaction.replace(R.id.frame_Subject_container, setQuestionFragment);
                tansaction.commit();
            }
        });
        //Button to set T&F Question
        Button setTFQuestion = (Button) view.findViewById(R.id.set_True_false__question_Button_id);
        setTFQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction tansaction = manager.beginTransaction();
                Fragment setTFQuestionFragment = new QuestionTrueFalseFragment(subject, chapter,phone);
                tansaction.replace(R.id.frame_Subject_container, setTFQuestionFragment);
                tansaction.commit();

            }
        });
        return view;
    }

}
