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

public class QuestionTrueFalseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.set_true_false_question,container,false);
        EditText TFquestion=(EditText)view.findViewById(R.id.true_false_questionEditText_id);

        //option 1 EditText
        EditText TFoption1=(EditText)view.findViewById(R.id.true_false_option1_EditText_id);
        //option 2 EditText
        EditText TFoption2=(EditText)view.findViewById(R.id.true_false_option2_EditText_id);


        //correct Answer spinner
        Spinner TFcorrectAnswer=(Spinner) view.findViewById(R.id.true_false_spinner_correct_answer);
        ArrayList<String> TFcorrectAnswerOption=new ArrayList<String>();
        TFcorrectAnswerOption.add("correct Answer");
        TFcorrectAnswerOption.add("option1");
        TFcorrectAnswerOption.add("option2");
        TFcorrectAnswerOption.add("option3");
        ArrayAdapter TFoptionAdapter=new ArrayAdapter(getContext(),
                R.layout.support_simple_spinner_dropdown_item,TFcorrectAnswerOption);
        TFcorrectAnswer.setAdapter(TFoptionAdapter);
        // button to add Question
        Button addQuestion=(Button)view.findViewById(R.id.true_false_button_add);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Question added", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
}
