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

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.R;

import java.util.ArrayList;

public class ExamPaperActivity extends AppCompatActivity {
    TextView showTimer;
    TextView questionTitle;
    RadioGroup radioButtonGroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    RadioButton answer4;
    Button next;
    CountDownTimer timer;
    ArrayList<QuestionStore> question;
    Integer count;
    int correctAnswer;
    int index=0;
    int degree=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_paper);
        // Text View of Question Title
        questionTitle=(TextView) findViewById(R.id.questionTitleId);
        //timer text view
        showTimer=(TextView)findViewById(R.id.textViewTimerId) ;
        //radio button group
        radioButtonGroup=(RadioGroup)findViewById(R.id.radioGroupId);
        //first answer radio button
        answer1=(RadioButton) findViewById(R.id.radioButton1Id);
        //second answer radio button
        answer2=(RadioButton) findViewById(R.id.radioButton2Id);
        //third answer radio button
        answer3=(RadioButton) findViewById(R.id.radioButton3Id);
        //fourth answer radio button
        answer4=(RadioButton) findViewById(R.id.radioButton4Id);
        //button next to go to next question
        next=(Button)findViewById(R.id.buttonNextId);
        //todo time 2 usage
        timer=new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                showTimer.setText("seconds remaining: " + ((millisUntilFinished / 1000) + 1));
            }

            @Override
            public void onFinish() {
                //todo submit Exam
                //Todo make intent to result activity
                Toast.makeText(ExamPaperActivity.this, "Time finished", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
        setQuestionStore();
        populateQuestion(index);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index+=1;
                if(index==count){
                    Intent i=new Intent(ExamPaperActivity.this,ExamDegree.class);
                    i.putExtra("degree",degree);
                    startActivity(i);
                    finish();
                }else if(index==count-1){
                    next.setText("Submit");
                    populateQuestion(index);

                }
                else {
                    populateQuestion(index);
                }

                radioButtonGroup.clearCheck();

            }
        });
        radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View selectedRadioButton = radioButtonGroup.findViewById(checkedId);
                int selectedRadioButtonIndex = radioButtonGroup.indexOfChild(selectedRadioButton);
                if(selectedRadioButtonIndex==correctAnswer){
                     ++degree;
                }

            }
        });

    }
    private void setQuestionStore() {
        question=new ArrayList<>();
        question.add(new QuestionStore("How do you maka that ?",
                "By Default","By Technology","By my tool","Its easy",1));
        question.add(new QuestionStore("When does blabla build ?",
                "1999","2000","1500","3000",1));

        question.add(new QuestionStore("what is it in picture ?",
                "dog","cat","door","table",3));
        question.add(new QuestionStore("what time ?",
                "12","9","3","6",0));
        question.add(new QuestionStore("what does it mean?",
                "no thing","pure","cake","apple",2));
        count=question.size();
    }
    public void populateQuestion(int index){
        this.index=index;
        String getQuestionTitle= question.get(index).getQuestionTitle();
        questionTitle.setText(getQuestionTitle);
        String getAnswer1=question.get(index).getAnswer1();
        answer1.setText(getAnswer1);
        String getAnswer2=question.get(index).getAnswer2();
        answer2.setText(getAnswer2);
        String getAnswer3=question.get(index).getAnswer3();
        answer3.setText(getAnswer3);
        String getAnswer4=question.get(index).getAnswer4();
        answer4.setText(getAnswer4);
        correctAnswer=question.get(index).getCorrectAnswer();

    }

}
