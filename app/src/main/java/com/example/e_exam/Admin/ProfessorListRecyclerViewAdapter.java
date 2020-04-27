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

public class ProfessorListRecyclerViewAdapter extends RecyclerView.Adapter<ProfessorListRecyclerViewAdapter.myHolder6>{
    Context context6;
    ArrayList<Set_get_professor> professersList;
    public  ProfessorListRecyclerViewAdapter(Context context6, ArrayList<Set_get_professor> professersList){
        this.context6=context6;
        this.professersList=professersList;
    }

    @NonNull
    @Override
    public myHolder6 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context6).inflate(R.layout.professors_list_row,viewGroup,false);
        myHolder6 holder6=new myHolder6(view);
        return holder6;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder6 holder, int i) {
        TextView professorsName=(TextView) holder.myView.findViewById(R.id.professorTextViewId);
        professorsName.setText(professersList.get(i).getProfessors_name());
        Button deleteProfessor=(Button) holder.myView.findViewById(R.id.buttonDeleteProfessorId);
        deleteProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context6, "deleted", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return professersList.size();
    }

    public  static  class myHolder6 extends RecyclerView.ViewHolder {
        View myView;
        public myHolder6(@NonNull View itemView) {
            super(itemView);
            myView=itemView;
        }
    }
}
