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
import com.example.e_exam.ViewHolders.SubjectsViewHolder;
import com.example.e_exam.model.Subjects;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubjectsListFragment extends Fragment {
    String level,department;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Subjects, SubjectsViewHolder> adapter;
    DatabaseReference ref;

    public SubjectsListFragment() {
    }

    public SubjectsListFragment(String level, String department) {
        this.level = level;
        this.department = department;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.subject_recycler_view,container,false);
        recyclerView = v.findViewById(R.id.showSubjectsRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager2);
        showFromDatabase();

        return v;

    }

    private void showFromDatabase() {
        ref = FirebaseDatabase.getInstance().getReference().child("Subjects").child(level).child(department);
        FirebaseRecyclerOptions<Subjects> options = new FirebaseRecyclerOptions.Builder<Subjects>()
                .setQuery(ref, Subjects.class)
                .build();
    adapter=new FirebaseRecyclerAdapter<Subjects, SubjectsViewHolder>(options) {
        @Override
        protected void onBindViewHolder(@NonNull SubjectsViewHolder holder, int i, @NonNull Subjects subjects) {
            holder.subjectTextView.setText(subjects.getSubjectName());
            holder.deleteSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getContext(), "subject deleted", Toast.LENGTH_SHORT).show();
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
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
