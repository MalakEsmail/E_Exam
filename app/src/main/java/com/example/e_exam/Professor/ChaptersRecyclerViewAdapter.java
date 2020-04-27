package com.example.e_exam.Professor;

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

public class ChaptersRecyclerViewAdapter extends RecyclerView.Adapter
        <ChaptersRecyclerViewAdapter.myHolder5> {
    Context context5;
    ArrayList<SetGetChaptersName> chapterNameList;
    public  ChaptersRecyclerViewAdapter(Context context5, ArrayList<SetGetChaptersName> chapterNameList){
        this.context5=context5;
        this.chapterNameList=chapterNameList;
    }


    @NonNull
    @Override
    public myHolder5 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context5).inflate(R.layout.show_edit_chapters_row,viewGroup,false);
        myHolder5 holder5=new myHolder5(view);
        return holder5;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder5 holder, int i) {
        TextView showChaptersList=(TextView) holder.myView.findViewById(R.id.chapterTextViewId);
        showChaptersList.setText(chapterNameList.get(i).get_Chapter_name());

        Button deleteChaptersButton=(Button) holder.myView.findViewById(R.id.buttonDeleteChaptersId);
        deleteChaptersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context5, "Chapter deleted", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return chapterNameList.size();
    }

    public static class  myHolder5 extends RecyclerView.ViewHolder {
        View myView;
        public myHolder5(@NonNull View itemView) {
            super(itemView);
            myView=itemView;
        }
    }
}
