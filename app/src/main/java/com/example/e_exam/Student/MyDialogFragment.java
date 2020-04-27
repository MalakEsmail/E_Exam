package com.example.e_exam.Student;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        //exam question number and time will get from num ant time that professor detect
        //todo professor is responsible for those two variables
        int num=5;
        //todo time 1 usage
        int time=60;
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Your Exam contains "+num+" questions At "+time+" minutes");
        builder.setCancelable(false);
        builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i=new Intent(getActivity(),ExamPaperActivity.class);
                startActivity(i);


                        }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Exam Canceled", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        return alertDialog;
    }
}
