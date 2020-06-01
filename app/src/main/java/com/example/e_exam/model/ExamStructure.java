package com.example.e_exam.model;

public class ExamStructure {
    private String subject, chapter, questionType, category, time, questionNumber;

    public ExamStructure() {
    }

    public ExamStructure(String subject, String chapter, String questionType, String category, String time, String questionNumber) {
        this.subject = subject;
        this.chapter = chapter;
        this.questionType = questionType;
        this.category = category;
        this.time = time;
        this.questionNumber = questionNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
}
