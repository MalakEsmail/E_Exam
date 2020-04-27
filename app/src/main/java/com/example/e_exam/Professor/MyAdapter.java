package com.example.e_exam.Professor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

//class Adapter for list of subjects of professor that admin who detect them
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Context context;
    ArrayList<pojo> listPojo;
    public  MyAdapter(Context context, ArrayList<pojo> listPojo){
        this.context=context;
        this.listPojo=listPojo;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_list,viewGroup,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.sub.setText(listPojo.get(i).getSubject());
        myHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here You Do Your Click Magic

                Intent intent = new Intent(v.getContext(), subjectActivity.class);
                context.startActivity(intent);
            }
            });

        }


    @Override
    public int getItemCount() {
        return listPojo.size();
    }

    public static class MyHolder extends  RecyclerView.ViewHolder{
        TextView sub;
        View mView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            sub=itemView.findViewById(R.id.subject_textView_id);
            mView = itemView;
        }
    }
}
