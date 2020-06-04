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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetQuestionFragment extends Fragment {
    EditText categoryEditText, questionEditText, option1Edittext, option2Edittext, option3Edittext, option4EditText, correctAnswerEditText;
    String category, question, option1, option2, option3, option4, correctAnswer;
    String subject, chapter, phone;
    DatabaseReference reference;

    public SetQuestionFragment(String subject, String chapter, String phone) {
        this.subject = subject;
        this.chapter = chapter;
        this.phone = phone;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_question, container, false);

        categoryEditText = (EditText) view.findViewById(R.id.set_Category_id);
        questionEditText = (EditText) view.findViewById(R.id.question_editText_id);
        option1Edittext = (EditText) view.findViewById(R.id.option1_editText_id);
        option2Edittext = (EditText) view.findViewById(R.id.option2_editText_id);
        option3Edittext = (EditText) view.findViewById(R.id.option3_editText_id);
        option4EditText = (EditText) view.findViewById(R.id.option4_editText_id);
        correctAnswerEditText = (EditText) view.findViewById(R.id.correctAnswer_editText_id);

        // button to add Question
        Button addQuestion = (Button) view.findViewById(R.id.add_question_Button_id);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = categoryEditText.getText().toString();
                question = questionEditText.getText().toString();
                option1 = option1Edittext.getText().toString();
                option2 = option2Edittext.getText().toString();
                option3 = option3Edittext.getText().toString();
                option4 = option4EditText.getText().toString();
                correctAnswer = correctAnswerEditText.getText().toString();

                validation();

                //    Toast.makeText(getContext(), "Question added", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void validation() {
        if (TextUtils.isEmpty(category)) {
            Toast.makeText(getContext(), "Please..Enter Category", Toast.LENGTH_SHORT).show();
        } else if (!((TextUtils.equals(category, "A")) || (TextUtils.equals(category, "B")) || (TextUtils.equals(category, "C")))) {
            Toast.makeText(getContext(), "Please..Enter Category From only A or B or C At Capital Letters", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(question)) {
            Toast.makeText(getContext(), "Please..Enter Question", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(option1)) {
            Toast.makeText(getContext(), "Please..Enter First Option", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(option2)) {
            Toast.makeText(getContext(), "Please..Enter Second Option", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(option3)) {
            Toast.makeText(getContext(), "Please..Enter Third Option", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(option4)) {
            Toast.makeText(getContext(), "Please..Enter Last Option", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(correctAnswer)) {
            Toast.makeText(getContext(), "Please..Specify Correct Answer", Toast.LENGTH_SHORT).show();
        } else if (
                !(TextUtils.equals(correctAnswer, option1) ||
                        TextUtils.equals(correctAnswer, option2) ||
                        TextUtils.equals(correctAnswer, option3) || TextUtils.equals(correctAnswer, option4))
        ) {
            Toast.makeText(getContext(), "this Answer is not from options you Entered ", Toast.LENGTH_SHORT).show();
        } else {
            addToDB();
        }

    }

    private void addToDB() {
        reference = FirebaseDatabase.getInstance().getReference()
                .child("chapters").child(subject).child(chapter).child("mcq").child(category);
        final HashMap hashMap = new HashMap();
        hashMap.put("question", question);
        hashMap.put("option1", option1);
        hashMap.put("option2", option2);
        hashMap.put("option3", option3);
        hashMap.put("option4", option4);
        hashMap.put("correctAnswer", correctAnswer);

        reference.child(question).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                emptyEditText();

                Toast.makeText(getContext(), "Question Added Successfully", Toast.LENGTH_SHORT).show();
                replaceFragment(getFragmentManager(), new HomeFragment(subject, phone), R.id.frame_Subject_container);

            }
        });

    }

    private void emptyEditText() {
        categoryEditText.setText("");
        questionEditText.setText("");
        option1Edittext.setText("");
        option2Edittext.setText("");
        option3Edittext.setText("");
        option4EditText.setText("");
        correctAnswerEditText.setText("");

    }

    public static void replaceFragment(FragmentManager getChildFragmentManager, Fragment fragment, int id) {
        FragmentTransaction transaction = getChildFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}