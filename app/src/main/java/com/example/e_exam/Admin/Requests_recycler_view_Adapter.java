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

public class Requests_recycler_view_Adapter  extends RecyclerView.Adapter<Requests_recycler_view_Adapter.myHolder5>{
    Context context5;
    ArrayList<Set_get_professors_name_requests> professersRequest;
    public  Requests_recycler_view_Adapter(Context context5, ArrayList<Set_get_professors_name_requests> professersRequest){
        this.context5=context5;
        this.professersRequest=professersRequest;
    }

    @NonNull
    @Override
    public myHolder5 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context5).inflate(R.layout.requests_row,viewGroup,false);
        myHolder5 holder5=new myHolder5(view);
        return holder5;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder5 holder, int i) {
        TextView professorsRequests=(TextView) holder.myView.findViewById(R.id.professors_requests_name);
        professorsRequests.setText(professersRequest.get(i).getProfessors_name_requests());
        Button approveProfessorsButton=(Button) holder.myView.findViewById(R.id.buttonApproveProfessorsRequests);
        approveProfessorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context5, "Approved", Toast.LENGTH_SHORT).show();

            }
        });
        Button deleteProfesorsRequestButton=(Button) holder.myView.findViewById(R.id.buttonDeleteProfessorsRequests);
        deleteProfesorsRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context5, "request deleted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return professersRequest.size();
    }

    public  static  class myHolder5 extends RecyclerView.ViewHolder {
        View myView;
        public myHolder5(@NonNull View itemView) {
            super(itemView);
            myView=itemView;
        }
    }

}
