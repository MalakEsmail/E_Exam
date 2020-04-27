package com.example.e_exam.Student;

public class QuestionStore {
    public   String questionTitle;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public int correctAnswer;

    public  QuestionStore
            ( String questionTitle,
              String answer1, String answer2,
              String answer3, String answer4,
              int correctAnswer)
    {
        this.questionTitle=questionTitle;
        this.answer1=answer1;
        this.answer2=answer2;
        this.answer3=answer3;
        this.answer4=answer4;
        this.correctAnswer=correctAnswer;


    }




    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

}
