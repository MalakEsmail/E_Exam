package com.example.e_exam.Admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.ViewHolders.ProfessorRequestsViewHolder;
import com.example.e_exam.model.Professors;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RequestsFragement extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Professors, ProfessorRequestsViewHolder> adapter;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.professors_requests_recycler_view,container,false);

        recyclerView = v.findViewById(R.id.professorsRequestsRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager2);
        showFromDatabase();

        return v;
    }

    private void showFromDatabase() {
        reference = FirebaseDatabase.getInstance().getReference().child("Professors");

        FirebaseRecyclerOptions<Professors> options = new FirebaseRecyclerOptions.Builder<Professors>()
                .setQuery(reference.child("Requests"),Professors.class)
                .build();
        adapter=new FirebaseRecyclerAdapter<Professors, ProfessorRequestsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ProfessorRequestsViewHolder holder, int i,
                                            @NonNull final Professors professors) {

                holder.nameEditText.setText(professors.getName());
                holder.phoneTextView.setText(professors.getPhone());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence option[] = new CharSequence[]{
                                "Accept",
                                "Delete"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("This Professors Request");
                        builder.setItems(option, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i1) {
                            if(i1==0){

                                //this is Accept Button
                                reference.child("ProfessorsList").child(professors.getPhone()).
                                        addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.exists()){
                                                    Toast.makeText(getContext(), "This Professor is Already Founded", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    HashMap professorsList=new HashMap();
                                                    professorsList.put("name",professors.getName());
                                                    professorsList.put("password",professors.getPassword());
                                                    professorsList.put("phone",professors.getPhone());
                                                    reference.child("ProfessorsList").child(professors.getPhone())
                                                            .updateChildren(professorsList)
                                                            .addOnCompleteListener(new OnCompleteListener() {
                                                                @Override
                                                                public void onComplete(@NonNull Task task) {
                                                                    if(task.isSuccessful()) {
                                                                        Toast.makeText(getContext(), "Professor Request Accepted Successfully", Toast.LENGTH_LONG).show();
                                                                    }
                                                                    }
                                                            });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                reference.child("Requests").child(professors.getPhone()).removeValue();


                            }
                            if(i1==1){
                                //this Delete Button
                                reference.child("Requests").child(professors.getPhone()).removeValue();
                                Toast.makeText(getContext(), "Professor Request deleted Successfully", Toast.LENGTH_LONG).show();

                            }
                            }
                        });
                        builder.show();



                    }

                });

            }

            @NonNull
            @Override
            public ProfessorRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requests_row, parent, false);
                ProfessorRequestsViewHolder holder = new ProfessorRequestsViewHolder(view);
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
