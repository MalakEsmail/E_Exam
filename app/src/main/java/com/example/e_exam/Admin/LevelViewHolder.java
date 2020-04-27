package com.example.e_exam.Admin;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.helper.ItemClickListener;


public class LevelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView levelName;
    public Button delete;

    public ItemClickListener listener;


    public LevelViewHolder(@NonNull View itemView) {
        super(itemView);
        levelName = itemView.findViewById(R.id.levelTextViewOnList);
        delete = itemView.findViewById(R.id.buttonDeleteLevelId);

    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
