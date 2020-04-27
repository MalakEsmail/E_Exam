package com.example.e_exam.Admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.model.Levels;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class levelFragment extends Fragment {
    RecyclerView recyclerView;

    DatabaseReference levelRef;

    public levelFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.level_recycler_view, viewGroup, false);

        //Add level recyclerView List
        recyclerView = view.findViewById(R.id.levelRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager2);

        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //get  FireBase reference
        levelRef = FirebaseDatabase.getInstance().getReference().child("Levels").child("levelName");



        FirebaseRecyclerOptions<Levels> options = new FirebaseRecyclerOptions.Builder<Levels>()
                .setQuery(levelRef, Levels.class)
                .build();
        Log.d("gg" , "dd");
        FirebaseRecyclerAdapter<Levels, LevelViewHolder> adapter =
                new FirebaseRecyclerAdapter<Levels, LevelViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull LevelViewHolder holder, int position, @NonNull final Levels model) {
//                        holder.levelName.setVisibility(View.VISIBLE);

                        Log.d("gg" , model.getLevelName());

                        Log.d("gg" , model.getId());

                    }

                    @NonNull
                    @Override
                    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        //we need to make a item layout to inflate here
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levels_list, parent, false);
                        LevelViewHolder holder = new LevelViewHolder(view);
                        return holder;
                    }
                };
        Log.d("ss" , adapter + "");
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
