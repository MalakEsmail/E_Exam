package com.example.e_exam;

import com.example.e_exam.Professor.ProfessorSignUpActivity;
import com.example.e_exam.model.Users;

public class Controller {
    private Users model;
    private ProfessorSignUpActivity view;

    public Controller(Users model, ProfessorSignUpActivity view) {
        this.model = model;
        this.view = view;
    }
    public void userNameSet(String name){
        model.setUserName(name);
    }
    public String userNameGet(){
        return model.getUserName();
    }
    public  void userEmailSet(String email){
        this.model.setUserEmail(email);
    }
    public String USerEmailGet(){
        return model.getUserEmail();
    }
    public void userPasswordSet(String password){
        this.model.setUserPassword(password);
    }
    public String userPasswordGet(){
        return model.getUserPassword();
    }
}