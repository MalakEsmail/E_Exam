package com.example.e_exam.Professor;

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

import java.util.ArrayList;

public class AddQuestionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.add_question,container,false);
        //Chapter spinner
        Spinner chapterSpinner=(Spinner) view.findViewById(R.id.chapters_spinner_id);
        ArrayList<String> chaptersArrayList=new ArrayList<String>();
        chaptersArrayList.add("chapter_one");
        chaptersArrayList.add("chapter_two");
        chaptersArrayList.add("chapter_three");
        ArrayAdapter<String> chaptersArrayAdapter=new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,chaptersArrayList);
        chapterSpinner.setAdapter(chaptersArrayAdapter);


        //category spinner
        Spinner chategorySpinner=(Spinner) view.findViewById(R.id.category_spinner_id);
        ArrayList<String> categoryArrayList=new ArrayList<String>();
        categoryArrayList.add("A");
        categoryArrayList.add("B");
        categoryArrayList.add("C");
        ArrayAdapter<String> categoryArrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,categoryArrayList);
        chategorySpinner.setAdapter(categoryArrayAdapter);

        //Button to set MCQ Question
        Button setQuestion=(Button)view.findViewById(R.id.set_question_Button_id);
        setQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FragmentManager manager=getActivity().getSupportFragmentManager();
              FragmentTransaction tansaction=   manager.beginTransaction();
                Fragment setQuestionFragment=new SetQuestionFragment();
                tansaction.replace(R.id.frame_Subject_container,setQuestionFragment);
                tansaction.commit();

            }
        });
        //Button to set T&F Question
        Button setTFQuestion=(Button)view.findViewById(R.id.set_True_false__question_Button_id);
        setTFQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction tansaction=   manager.beginTransaction();
                Fragment setTFQuestionFragment=new QuestionTrueFalseFragment();
                tansaction.replace(R.id.frame_Subject_container,setTFQuestionFragment);
                tansaction.commit();

            }
        });



        return view;
    }
}
