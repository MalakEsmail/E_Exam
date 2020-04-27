package com.example.e_exam.Professor;

import android.os.Bundle;
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

import java.util.ArrayList;

public class ExamStructureFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exam_structure,container,false);
        // Maximum Questions number allowed for each subject Exam
        EditText MaxQuestionNum=(EditText) view.findViewById(R.id.ExamQuestionNumbersEditTextId);
       // MaxQuestionNum.setInputType();
        // Time allowed for each subject Exam
        EditText ExamAllowedTime=(EditText) view.findViewById(R.id.ExamAllowedTimeEditTextId);
       // ExamAllowedTime.setInputType();

        //choose chapter spinner
        Spinner chooseChpSpinner=(Spinner) view.findViewById(R.id.spinnerChooseChapterId);
        ArrayList<String> chpArrayList=new ArrayList<String>();
        chpArrayList.add("choose chapter");
        chpArrayList.add("chapter one");
        chpArrayList.add("chapter two");
        chpArrayList.add("chapter three");

        ArrayAdapter chpArrayAdapter=new ArrayAdapter(getContext(),
                R.layout.support_simple_spinner_dropdown_item,chpArrayList);
        chooseChpSpinner.setAdapter(chpArrayAdapter);

        //choose question type spinner
        Spinner chooseQuestionTypeSpinner=(Spinner) view.findViewById(R.id.spinnerChooseQuestionTypeId);
        ArrayList<String> questionTypeArrayList=new ArrayList<String>();
        questionTypeArrayList.add("choose question");
        questionTypeArrayList.add("Question one");
        questionTypeArrayList.add("Question two");
        questionTypeArrayList.add("Question three");

        ArrayAdapter questionArrayAdapter=new ArrayAdapter(getContext(),
                R.layout.support_simple_spinner_dropdown_item,questionTypeArrayList);
        chooseQuestionTypeSpinner.setAdapter(questionArrayAdapter);


        //choose category spinner
        Spinner chooseCategorySpinner=(Spinner) view.findViewById(R.id.spinnerChooseCategoryId);
        ArrayList<String> categoryArrayList=new ArrayList<String>();
        categoryArrayList.add("choose category");
        categoryArrayList.add("A");
        categoryArrayList.add("B");
        categoryArrayList.add("C");

        ArrayAdapter categoryArrayAdapter=new ArrayAdapter(getContext(),
                R.layout.support_simple_spinner_dropdown_item,categoryArrayList);
        chooseCategorySpinner.setAdapter(categoryArrayAdapter);

        //needed number of question
        EditText neededQuestionNum=(EditText) view.findViewById(R.id.allowedQuestionEditTextId);

        //Button to add selected specefication to exam
        Button addButton=(Button) view.findViewById(R.id.buttonAddExamQuestionId);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Added to Exam", Toast.LENGTH_SHORT).show();
            }
        });






        return view;
    }
}
