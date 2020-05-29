package com.example.e_exam.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

public class DepartmentsViewHolder extends RecyclerView.ViewHolder {
    public TextView departmentName;
    Button deleteDepartment;
    public DepartmentsViewHolder(@NonNull View itemView) {
        super(itemView);
        departmentName=itemView.findViewById(R.id.departmentTextViewIdOnList);
        deleteDepartment=itemView.findViewById(R.id.buttonDeleteDepartmentId);
    }
}
