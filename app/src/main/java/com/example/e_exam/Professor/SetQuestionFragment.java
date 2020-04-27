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

public class SetQuestionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.set_question,container,false);
        //Question EditText
        EditText question=(EditText)view.findViewById(R.id.question_editText_id);

        //option 1 EditText
        EditText option1=(EditText)view.findViewById(R.id.option1_editText_id);
        //option 2 EditText
        EditText option2=(EditText)view.findViewById(R.id.option2_editText_id);
        //option 3 EditText
        EditText option3=(EditText)view.findViewById(R.id.option3_editText_id);
        //option 4 EditText
        EditText option4=(EditText)view.findViewById(R.id.option4_editText_id);


        //correct Answer spinner
        Spinner correctAnswer=(Spinner) view.findViewById(R.id.correct_answer__spinner_id);
        ArrayList<String> correctAnswerOption=new ArrayList<String>();
        correctAnswerOption.add("correct Answer");
        correctAnswerOption.add("option1");
        correctAnswerOption.add("option2");
        correctAnswerOption.add("option3");
        ArrayAdapter optionAdapter=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,correctAnswerOption);
        correctAnswer.setAdapter(optionAdapter);
        // button to add Question
        Button addQuestion=(Button)view.findViewById(R.id.add_question_Button_id);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Question added", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
}
