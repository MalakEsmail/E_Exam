package com.example.e_exam.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

public class ChaptersViewHolder  extends RecyclerView.ViewHolder{
    public TextView chapterName;

    public ChaptersViewHolder(@NonNull View itemView) {
        super(itemView);
        chapterName = itemView.findViewById(R.id.chapterTextViewId);

    }
}
