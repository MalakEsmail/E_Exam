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

public class show_edit_subject_recyclerview_adapter  extends RecyclerView.Adapter
        <show_edit_subject_recyclerview_adapter.myHolder4>{
    Context context4;
    ArrayList<set_get_subject> subjectNameList;
    public  show_edit_subject_recyclerview_adapter(Context context4, ArrayList<set_get_subject> subjectNameList){
        this.context4=context4;
        this.subjectNameList=subjectNameList;
    }

    @NonNull
    @Override
    public myHolder4 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context4).inflate(R.layout.show_edit_subjects_recyclerview_row,viewGroup,false);
        myHolder4 holder4=new myHolder4(view);
        return holder4;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder4 holder, int i) {
        TextView showsubjectList=(TextView) holder.myView.findViewById(R.id.show_subject_TextViewIdOnList);
        showsubjectList.setText(subjectNameList.get(i).getSubject_name());

        Button deleteSubjectButton=(Button) holder.myView.findViewById(R.id.buttonDeleteSubjectId);
        deleteSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context4, "subject deleted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return  subjectNameList.size();
    }

    public  static  class myHolder4 extends RecyclerView.ViewHolder {
        View myView;
        public myHolder4(@NonNull View itemView) {
            super(itemView);
            myView=itemView;
        }
    }
}
