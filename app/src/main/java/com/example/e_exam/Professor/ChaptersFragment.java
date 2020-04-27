package com.example.e_exam.Professor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class ChaptersFragment extends Fragment {
    RecyclerView ChaptersRecyclerView;
    RecyclerView.LayoutManager layoutManager5;
    ChaptersRecyclerViewAdapter chaptersRecyclerViewAdapter;
    ArrayList<SetGetChaptersName> chaptersArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.recycler_view,container,false);
        ChaptersName();
        ChaptersRecyclerView=(RecyclerView)view.findViewById(R.id.RecyclerViewId);
        ChaptersRecyclerView.setHasFixedSize(true);
        layoutManager5=new LinearLayoutManager(getContext());
        ChaptersRecyclerView.setLayoutManager(layoutManager5);
        chaptersRecyclerViewAdapter=new ChaptersRecyclerViewAdapter(getContext(),chaptersArrayList);
        ChaptersRecyclerView.setAdapter(chaptersRecyclerViewAdapter);
        return view;

    }
    private void ChaptersName() {
        chaptersArrayList =new ArrayList<SetGetChaptersName>();
        // Todo Admin who is Professor for adding  Chapters of subjects
        chaptersArrayList.add(new SetGetChaptersName("Chapter_one"));
        chaptersArrayList.add(new SetGetChaptersName("Chapter_two"));
        chaptersArrayList.add(new SetGetChaptersName("Chapter_three"));
        chaptersArrayList.add(new SetGetChaptersName("Chapter_four"));


    }
}
