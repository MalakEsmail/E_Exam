package com.example.e_exam.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

public class SubjectsViewHolder extends RecyclerView.ViewHolder {
   public TextView subjectTextView;
 public    Button deleteSubject;
    public SubjectsViewHolder(@NonNull View itemView) {
        super(itemView);
        subjectTextView=itemView.findViewById(R.id.show_subject_TextViewIdOnList);
        deleteSubject=itemView.findViewById(R.id.buttonDeleteSubjectId);
    }
}
