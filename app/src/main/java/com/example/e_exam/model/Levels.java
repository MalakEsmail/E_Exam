package com.example.e_exam.model;

public class Levels {

   private String levelName , id;

    public Levels() {

    }

    public Levels(String levelName, String id) {
        this.levelName = levelName;
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
