package com.example.e_exam.Student;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;
import com.example.e_exam.model.ExamStructure;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExamPaperActivity extends AppCompatActivity {

    private TextView showTimer, questionTitleTV;
    private String question, option1, option2, option3, option4, correctAnswer;
    private String subject, chapter, questionType, category, time, questionNumber;
    private String valueSelected, userId;

    private RadioGroup radioButtonGroup;
    private RadioButton option1RadioBut, option3RadioBut, option2RadioBut, option4RadioBut;

    private Button next;

    private CountDownTimer timer;
    private ValueEventListener listener;

    private DatabaseReference examStructureRef;
    private DatabaseReference questionsRef;
    private ArrayList<ExamStructure> examStructureArrayList;
    // ArrayList<QuestionStore> question;
    Integer count;
    //   int correctAnswer;
    int index = 0;
    int degree = 0;
    int x = 0;
    int lastQuestionIndex;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_paper);

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        userId = intent.getStringExtra("id");

        examStructureRef = FirebaseDatabase.getInstance().getReference();

        showTimer = (TextView) findViewById(R.id.textViewTimerId);

        questionTitleTV = (TextView) findViewById(R.id.questionTitleId);

        radioButtonGroup = (RadioGroup) findViewById(R.id.radioGroupId);

        option1RadioBut = (RadioButton) findViewById(R.id.radioButton1Id);
        option2RadioBut = (RadioButton) findViewById(R.id.radioButton2Id);
        option3RadioBut = (RadioButton) findViewById(R.id.radioButton3Id);
        option4RadioBut = (RadioButton) findViewById(R.id.radioButton4Id);

        next = (Button) findViewById(R.id.buttonNextId);

        int time = 2;
        timer = new CountDownTimer(15000 * time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                showTimer.setText("seconds remaining : " + ((millisUntilFinished / 1000) + 1));
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(ExamPaperActivity.this, ExamDegree.class);
                i.putExtra("degree", degree);
                i.putExtra("id", userId);
                i.putExtra("subject", subject);

                //  Toast.makeText(ExamPaperActivity.this, "Time finished", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }
        };
        timer.start();
        getExamStructure();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // count is question needed number from this category
                //index counter for questionNumber needed get From Db
                //lastQuestionIndex is length of array of exam structure

                if (x == lastQuestionIndex - 1) {
//                        index = 0;
//                        getExamStructure();
                    Intent i = new Intent(ExamPaperActivity.this, ExamDegree.class);
                    i.putExtra("degree", degree);
                    i.putExtra("id", userId);
                    i.putExtra("subject", subject);

                    finish();
                    startActivity(i);
                } else {
                    radioButton.setChecked(false);
                    if (index == count) {
                        x += 1;
                        index = 0;
                        getExamStructure();

                    } else {
                        index += 1;
                        getExamStructure();

                    }

                }


                //  index += 1;
//                if (index == count) {
//                    x+=1;
//                    getExamStructure();
//                    Intent i = new Intent(ExamPaperActivity.this, ExamDegree.class);
//                    i.putExtra("degree", degree);
//                    startActivity(i);
//                    finish();
//                } else if (index == count - 1) {
//                    x+=1;
//                    getExamStructure();
////                    next.setText("Submit");
//                    setQuestionStore();

//                } else {
//                    x+=1;
//                    getExamStructure();
//                  //  setQuestionStore();
//                }


            }
        });


    }

    private void getExamStructure() {
        examStructureArrayList = new ArrayList<ExamStructure>();

        examStructureRef.child("examStructure").child(subject).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot items : dataSnapshot.getChildren()) {
                        ExamStructure examStructure = items.getValue(ExamStructure.class);
                        examStructureArrayList.add(examStructure);

                    }
                    chapter = examStructureArrayList.get(x).getChapter().toString();
                    questionType = examStructureArrayList.get(x).getQuestionType().toString();
                    category = examStructureArrayList.get(x).getCategory().toString();
                    time = examStructureArrayList.get(x).getTime().toString();
                    questionNumber = examStructureArrayList.get(x).getQuestionNumber().toString();
                    count = (Integer.valueOf(questionNumber) - 1);
                    lastQuestionIndex = examStructureArrayList.size();
                    questionsRef = FirebaseDatabase.getInstance().getReference()
                            .child("chapters").child(subject).child(chapter).child(questionType).child(category);
                    setQuestionStore();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setQuestionStore() {
        final ArrayList<QuestionStore> questionsList = new ArrayList<>();

        listener = questionsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        QuestionStore questionStore = item.getValue(QuestionStore.class);
                        questionsList.add(questionStore);
                    }
                    populateQuestions(questionsList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void populateQuestions(ArrayList<QuestionStore> questionsList) {
        question = questionsList.get(index).getQuestion().toString();
        correctAnswer = questionsList.get(index).getCorrectAnswer().toString();
        option1 = questionsList.get(index).getOption1().toString();
        option2 = questionsList.get(index).getOption2().toString();
        option3 = questionsList.get(index).getOption3().toString();
        option4 = questionsList.get(index).getOption4().toString();

        //set Question in Views
        questionTitleTV.setText(question);
        option1RadioBut.setText(option1);
        option2RadioBut.setText(option2);
        option3RadioBut.setText(option3);
        option4RadioBut.setText(option4);

        getSelectedOption(correctAnswer);
    }

    private void getSelectedOption(final String correctAnswer) {
        radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selectedId = group.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);
                valueSelected = radioButton.getText().toString();

                if (valueSelected.equalsIgnoreCase(correctAnswer)) {

                    ++degree;
                    Toast.makeText(ExamPaperActivity.this, "degree :" + degree, Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}
