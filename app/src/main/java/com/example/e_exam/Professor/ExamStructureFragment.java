package com.example.e_exam.Professor;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.e_exam.model.Question;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExamStructureFragment extends Fragment {
    private DatabaseReference reference;

    private Spinner chapterSpinner, questionTypeSpinner, categorySpinner;

    private ArrayList<String> chapterArray, categoryList, questionTypesList;

    private ArrayAdapter<String> chapterAdapter, adapter, questionAdapter;

    private String subject, time, questionNum, chapter, type, category;

    ValueEventListener listener;
    Question question;
    ArrayList<Question> questionsArr;
    int i = 0;


    public ExamStructureFragment(String subject) {
        this.subject = subject;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exam_structure, container, false);

        reference = FirebaseDatabase.getInstance().getReference();

        final EditText allowedTime = (EditText) view.findViewById(R.id.ExamAllowedTimeEditTextId);
        final EditText numOfQuestions = (EditText) view.findViewById(R.id.allowedQuestionEditTextId);

        //chapter spinner
        chapterSpinner = (Spinner) view.findViewById(R.id.spinnerChooseChapterId);
        addChapterSpinner();

        //question type spinner
        questionTypeSpinner = (Spinner) view.findViewById(R.id.spinnerChooseQuestionTypeId);
        addQuestionTypeSpinner();

        //category spinner
        categorySpinner = (Spinner) view.findViewById(R.id.spinnerChooseCategoryId);
        addCategorySpinner();

        Button addButton = (Button) view.findViewById(R.id.buttonAddExamQuestionId);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = allowedTime.getText().toString();
                questionNum = numOfQuestions.getText().toString();
                validation();

            }
        });

        return view;
    }

    private void validation() {
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(getContext(), "please Enter Exam Time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(questionNum)) {
            Toast.makeText(getContext(), "please detect number needed", Toast.LENGTH_SHORT).show();
        } else {
            chapter = chapterSpinner.getSelectedItem().toString();
            type = questionTypeSpinner.getSelectedItem().toString();
            category = categorySpinner.getSelectedItem().toString();
            addToDB();
            // getQuestionsFromDb();
            //  Toast.makeText(getContext(), "correct Answer"+questionsArr.get(0), Toast.LENGTH_SHORT).show();
        }
    }

    private void addToDB() {
        HashMap hashMap = new HashMap();
        hashMap.put("subject", subject);
        hashMap.put("chapter", chapter);
        hashMap.put("questionType", type);
        hashMap.put("category", category);
        hashMap.put("time", time);
        hashMap.put("questionNumber", questionNum);

        reference.child("examStructure").child(subject).push().updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getContext(), "Exam Structure Added Successfully..", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Check your Internet Connection ..And Try Again !", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getQuestionsFromDb() {
        final List<Question> list = new ArrayList<>();
        final List<String> correct = new ArrayList<>();
        //  questionsArr=new ArrayList<>();
        reference.child("chapters").child(subject).child(chapter).child(type).child(category)
                /*.child("what's our major character ?")*/.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            /*  // question=dataSnapshot.getValue(Question.class);
                 Iterator<DataSnapshot> iterator=dataSnapshot.getChildren().iterator();
                int length = (int) dataSnapshot.getChildrenCount();
                String[] sampleString = new String[length];
                while(i < length) {
                    sampleString[i] = iterator.next().getValue().toString();
                  //  Toast.makeText(getContext(), ""+sampleString[i], Toast.LENGTH_SHORT).show();
                    i++;
              }*/
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Question q = item.getValue(Question.class);
                    list.add(q);
                    correct.add(q.getCorrectAnswer());
                }
                Toast.makeText(getContext(), "" + correct, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addCategorySpinner() {
        categoryList = new ArrayList();
        categoryList.add("A");
        categoryList.add("B");
        categoryList.add("C");

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoryList);
        categorySpinner.setAdapter(adapter);

    }

    private void addQuestionTypeSpinner() {
        questionTypesList = new ArrayList();
        questionTypesList.add("mcq");
        questionTypesList.add("TF");
        questionAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, questionTypesList);
        questionTypeSpinner.setAdapter(questionAdapter);
    }

    private void addChapterSpinner() {
        chapterArray = new ArrayList<>();
        chapterAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, chapterArray);
        retrieveFromDB();
        chapterSpinner.setAdapter(chapterAdapter);

    }

    private void retrieveFromDB() {
        listener = reference.child("chaptersList").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot item : dataSnapshot.getChildren()) {
                            chapterArray.add(item.getValue().toString());
                        }
                        chapterAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
}
