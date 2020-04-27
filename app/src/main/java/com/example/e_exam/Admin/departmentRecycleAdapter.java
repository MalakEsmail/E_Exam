package com.example.e_exam.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class departmentRecycleAdapter extends RecyclerView.Adapter<departmentRecycleAdapter.myHolder3> {
    Context context3;
    ArrayList<set_get_department_name> DepartmentNameList;
    public  departmentRecycleAdapter(Context context3, ArrayList<set_get_department_name> DepartmentNameList){
        this.context3=context3;
        this.DepartmentNameList=DepartmentNameList;
    }

    @NonNull
    @Override
    public myHolder3 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context3).inflate(R.layout.department_recycler_view_row,viewGroup,false);
        myHolder3 holder3=new myHolder3(view);
        return holder3;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder3 holder, int i) {
        TextView showDepartmentList=(TextView) holder.v.findViewById(R.id.departmentTextViewIdOnList);
        showDepartmentList.setText(DepartmentNameList.get(i).get_department_name());

        Button deleteDepartmentButton=(Button) holder.v.findViewById(R.id.buttonDeleteDepartmentId);
        deleteDepartmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context3, "department deleted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return DepartmentNameList.size();
    }

    public  static  class myHolder3 extends RecyclerView.ViewHolder {
        View v;
        public myHolder3(@NonNull View itemView) {
            super(itemView);
            v=itemView;

        }
    }
}
