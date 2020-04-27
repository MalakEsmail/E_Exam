package com.example.e_exam.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class student_subject_class_adapter extends RecyclerView.Adapter<student_subject_class_adapter.MyHolder1>  {
    Context context1;
    FragmentManager manager;
    ArrayList<student_set_get_subjects> subjectlist;
    public  student_subject_class_adapter(Context context1, ArrayList<student_set_get_subjects> subjectlist){
        this.context1=context1;
        this.subjectlist=subjectlist;
    }

    @NonNull
    @Override
    public MyHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context1).inflate(R.layout.custom_list,viewGroup,false);
        MyHolder1 myHolder=new MyHolder1(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder1 holder, int i) {
        holder.sub.setText(subjectlist.get(i).get_Student_Subject());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here You Do Your Click Magic
                manager=((AppCompatActivity)context1).getSupportFragmentManager();
                MyDialogFragment myDialogFragment=new MyDialogFragment();
                myDialogFragment.show(manager,"myDialogFragment");
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectlist.size();
    }
    public static class MyHolder1 extends  RecyclerView.ViewHolder{
        TextView sub;
        View mView;
        public MyHolder1(@NonNull View itemView) {
            super(itemView);
            sub=itemView.findViewById(R.id.subject_textView_id);
            mView = itemView;
        }
    }
}
