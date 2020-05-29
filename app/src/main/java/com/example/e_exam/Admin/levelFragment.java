package com.example.e_exam.Admin;

import android.os.Bundle;
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
import com.example.e_exam.ViewHolders.LevelViewHolder;
import com.example.e_exam.model.Levels;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class levelFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Levels, LevelViewHolder> adapter;
    DatabaseReference levelRef;

    public levelFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.level_recycler_view, viewGroup, false);

        recyclerView = view.findViewById(R.id.levelRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager2);
        showFromDatabase();
        return view;
    }

    private void showFromDatabase() {
        levelRef = FirebaseDatabase.getInstance().getReference().child("Levels");

        FirebaseRecyclerOptions<Levels> options = new FirebaseRecyclerOptions.Builder<Levels>()
                .setQuery(levelRef, Levels.class)
                .build();
       adapter =
                new FirebaseRecyclerAdapter<Levels, LevelViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull LevelViewHolder holder, final int position, @NonNull  Levels model) {
                        holder.levelName.setText(model.getLevelName());
                        holder.delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(getContext(), "level deleted", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @NonNull
                    @Override
                    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levels_list, parent, false);
                        LevelViewHolder holder = new LevelViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

         adapter.startListening();

    }
}
