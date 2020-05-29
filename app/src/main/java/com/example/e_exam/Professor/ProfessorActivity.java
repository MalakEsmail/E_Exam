package com.example.e_exam.Professor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.ViewHolders.SubjectsViewHolder;
import com.example.e_exam.model.Subjects;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfessorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Subjects, SubjectsViewHolder> adapter;
    DatabaseReference ref;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
         phone=getIntent().getExtras().getString("phone");

        recyclerView =findViewById(R.id.recycler_view_liner);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        showFromDatabase();

    }

    private void showFromDatabase() {
        ref = FirebaseDatabase.getInstance().getReference().child("Professors").child("Subjects").child(phone);
        FirebaseRecyclerOptions<Subjects> options = new FirebaseRecyclerOptions.Builder<Subjects>()
                .setQuery(ref, Subjects.class)
                .build();
        adapter=new FirebaseRecyclerAdapter<Subjects, SubjectsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SubjectsViewHolder holder, int i, @NonNull final Subjects subjects) {
                holder.subjectTextView.setText(subjects.getSubjectName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ProfessorActivity.this,subjectActivity.class);
                        intent.putExtra("sub",subjects.getSubjectName());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_edit_subjects_recyclerview_row, parent, false);
                SubjectsViewHolder holder = new SubjectsViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
