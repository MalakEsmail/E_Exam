package com.example.e_exam.Admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfessorsListFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Professors, ProfessorRequestsViewHolder> adapter;
    DatabaseReference reference;
    AlertDialog alertD;
    Spinner spinner;
    ValueEventListener listener;
    ArrayList<String> subjectArrayList;
    ArrayAdapter<String> subjectAdapter;
    String phone;
     String subject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.professors_requests_recycler_view,container,false);
        recyclerView = v.findViewById(R.id.professorsRequestsRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager2);
        showFromDatabase();
        ArrayList x=new ArrayList();

            x.add(subject);


        return v;
    }

    private void showFromDatabase() {
        reference = FirebaseDatabase.getInstance().getReference().child("Professors");

        FirebaseRecyclerOptions<Professors> options = new FirebaseRecyclerOptions.Builder<Professors>()
                .setQuery(reference.child("ProfessorsList"),Professors.class)
                .build();
        adapter=new FirebaseRecyclerAdapter<Professors, ProfessorRequestsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProfessorRequestsViewHolder holder, int i, @NonNull final Professors professors) {
                holder.nameEditText.setText(professors.getName());
                holder.phoneTextView.setText(professors.getPhone());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence option[] = new CharSequence[]{
                                "Delete",

                                "Add Subject"
                        };
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("You want to delete this Professor Or Add Subject ?");
                        builder.setItems(option, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i1) {
                                if(i1==0){
                                    reference.child("ProfessorsList").child(professors.getPhone()).removeValue();
                                    Toast.makeText(getContext(), "Professor Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }

                                if(i1==1){
                                addPopUpDialog();
                         phone=professors.getPhone();
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



    private void getSpinnerOptionFromDb() {

            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("subjectList");
         listener= ref.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                  ArrayList x=new ArrayList();
//                  for(DataSnapshot removed: dataSnapshot.getChildren()){
//                      x.add(removed.getValue().toString());
//
//                  }
                  for (DataSnapshot item: dataSnapshot.getChildren()){
                     // subjectArrayList.remove(subject);

                      subjectArrayList.add(item.getValue().toString());

                  }
                  subjectAdapter.notifyDataSetChanged();

                  // Toast.makeText(getContext(), "Subject Added to Professor" + dataSnapshot.child("subjectName").getValue(), Toast.LENGTH_SHORT).show();

              }
              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });
    }
    private void addSelectedSubjectToProfessor() {
        subject = spinner.getSelectedItem().toString();
        reference.child("Subjects").child(phone).push().child("subjectName").setValue(subject);
        Toast.makeText(getContext(), "Subject Added to Professor Successfully", Toast.LENGTH_SHORT).show();

    }
    private void addPopUpDialog() {
        //pop Up dialog to Add subject to Professor
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptView = layoutInflater.inflate(R.layout.pop_up, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);

        spinner = (Spinner) promptView.findViewById(R.id.spinner);
        subjectArrayList=new ArrayList<>();
        subjectAdapter=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,subjectArrayList);
        getSpinnerOptionFromDb();

        spinner.setAdapter(subjectAdapter);


        Button btn_1= (Button)promptView.findViewById(R.id.button);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSpinnerOptionFromDb();
                addSelectedSubjectToProfessor();
                alertD.dismiss();


            }
        });

        alertDialogBuilder
                .setCancelable(false);


        alertD = alertDialogBuilder.create();
        alertD.show();
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
