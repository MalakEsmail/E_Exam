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
import com.example.e_exam.ViewHolders.DepartmentsViewHolder;
import com.example.e_exam.model.Departments;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DepartmentFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Departments, DepartmentsViewHolder> adapter;
    DatabaseReference departmentRef;


    public DepartmentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.department_recycler_view_list, viewGroup, false);

        recyclerView = v.findViewById(R.id.departmentRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(getContext(), "Departments part", Toast.LENGTH_SHORT).show();
        addDatabaseToRecyclerView();
        return v;
    }

    private void addDatabaseToRecyclerView() {
        departmentRef = FirebaseDatabase.getInstance().getReference().child("Departments");

        FirebaseRecyclerOptions<Departments> options = new FirebaseRecyclerOptions.Builder<Departments>()
                .setQuery(departmentRef, Departments.class)
                .build();
        adapter =
                new FirebaseRecyclerAdapter<Departments, DepartmentsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final DepartmentsViewHolder holder, int i, @NonNull final Departments model) {
                        holder.departmentName.setText(model.getDepartmentName());

                    }

                    @NonNull
                    @Override
                    public DepartmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_recycler_view_row, parent, false);
                        DepartmentsViewHolder holder = new DepartmentsViewHolder(view);
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
