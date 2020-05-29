package com.example.e_exam.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

public class ProfessorRequestsViewHolder extends RecyclerView.ViewHolder {
  public TextView nameEditText,phoneTextView;
 // public Button deleteRequest;
 // public  Button approveRequest;
    public ProfessorRequestsViewHolder(@NonNull View itemView) {
        super(itemView);
        nameEditText=itemView.findViewById(R.id.professors_requests_name);
        phoneTextView=itemView.findViewById(R.id.professors_requests_phone);
       // deleteRequest=itemView.findViewById(R.id.buttonDeleteProfessorsRequests);
      //  approveRequest=itemView.findViewById(R.id.buttonApproveProfessorsRequests);

    }
}
