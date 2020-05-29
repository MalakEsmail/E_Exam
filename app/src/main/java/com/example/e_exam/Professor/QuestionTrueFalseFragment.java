package com.example.e_exam.Professor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class QuestionTrueFalseFragment extends Fragment {
    String subject,chapter;
    EditText questionEditText,correctAnswerEditText,categoryEditText;
    String question,correctAnswer,category;
    DatabaseReference reference;

    public QuestionTrueFalseFragment(String subject, String chapter) {
        this.subject = subject;
        this.chapter = chapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.set_true_false_question,container,false);
        categoryEditText=(EditText)view.findViewById(R.id.set_Category_id);
        questionEditText=(EditText)view.findViewById(R.id.true_false_questionEditText_id);
         correctAnswerEditText=(EditText)view.findViewById(R.id.true_false_correctAnswer_EditText_id);

         Button addQuestion=(Button)view.findViewById(R.id.true_false_button_add);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category=categoryEditText.getText().toString();
                question=questionEditText.getText().toString();
                correctAnswer=correctAnswerEditText.getText().toString();

                validation();
            //    Toast.makeText(getContext(), "Question added", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void validation() {
        if(TextUtils.isEmpty(category) )
        {
            Toast.makeText(getContext(), "Please..Enter Category", Toast.LENGTH_SHORT).show();
        }
        else if(!((TextUtils.equals(category,"A")) || (TextUtils.equals(category,"B"))|| (TextUtils.equals(category,"C"))))
        {
            Toast.makeText(getContext(), "Please..Enter Category From only A or B or C At Capital Letters", Toast.LENGTH_LONG).show();
        }
         else if(TextUtils.isEmpty(question)){
            Toast.makeText(getContext(), "Please..Enter Question", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(correctAnswer))
        {
            Toast.makeText(getContext(), "Please..Enter correct Answer", Toast.LENGTH_SHORT).show();

        }
        else if(
                !(TextUtils.equals(correctAnswer,"true") ||
                        TextUtils.equals(correctAnswer,"false") )
        )
        {
            Toast.makeText(getContext(), "this Answer should be true or false ", Toast.LENGTH_SHORT).show();
        }
        else
            {
                addToDB();
        }

    }

    private void addToDB() {
        reference= FirebaseDatabase.getInstance().getReference()
                .child("chapters").child(subject).child(chapter).child("TF").child(category);
        final HashMap hashMap=new HashMap();
        hashMap.put("question",question);
         hashMap.put("correctAnswer",correctAnswer);
        reference.child(question).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                categoryEditText.setText("");
                questionEditText.setText("");
                correctAnswerEditText.setText("");
                Toast.makeText(getContext(), "Question Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
