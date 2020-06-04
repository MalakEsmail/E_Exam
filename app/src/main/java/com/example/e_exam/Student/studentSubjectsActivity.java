package com.example.e_exam.Student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.ViewHolders.SubjectsViewHolder;
import com.example.e_exam.model.Subjects;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentSubjectsActivity extends AppCompatActivity {
    RecyclerView studentSubjectsRecyclerView;
    RecyclerView.LayoutManager layoutManager1;

    private String userId, departmentName, levelName;
    private DatabaseReference subRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subjects);
        studentSubjectsRecyclerView = findViewById(R.id.recycler_view_Student_subject_listView);
        studentSubjectsRecyclerView.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        studentSubjectsRecyclerView.setLayoutManager(layoutManager1);
        Intent intent = getIntent();
        userId = intent.getStringExtra("uId");
        departmentName = intent.getStringExtra("departmentName");
        levelName = intent.getStringExtra("levelName");
        subRef = FirebaseDatabase.getInstance().getReference().child("Subjects").child(levelName).child(departmentName);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Subjects> options = new FirebaseRecyclerOptions.Builder<Subjects>()
                .setQuery(subRef, Subjects.class).build();
        FirebaseRecyclerAdapter<Subjects, SubjectsViewHolder> adapter =
                new FirebaseRecyclerAdapter<Subjects, SubjectsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final SubjectsViewHolder holder, int i, @NonNull final Subjects subjects) {
                        holder.subjectTextView.setText(subjects.getSubjectName());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Set Alert Dialog
                                CharSequence option[] = new CharSequence[]{
                                        "Start",
                                        "Cancel"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(studentSubjectsActivity.this);
                                builder.setTitle("Are You Ready To Start Exam ?");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        if (i == 0) {
                                            DatabaseReference degreeRef = FirebaseDatabase.getInstance().getReference().child("Degrees").child(userId).child(subjects.getSubjectName().toString());
                                            degreeRef.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        Toast.makeText(studentSubjectsActivity.this, "This subject Exam done previously.. select another subject !", Toast.LENGTH_LONG).show();
                                                        finish();
                                                    } else {
                                                        Intent intent = new Intent(studentSubjectsActivity.this, ExamPaperActivity.class);
                                                        String subject = subjects.getSubjectName().toString();
                                                        intent.putExtra("id", userId);
                                                        intent.putExtra("subject", subject);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });

                                        }
                                        if (i == 1) {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_edit_subjects_recyclerview_row, parent, false);
                        SubjectsViewHolder subjectsViewHolder = new SubjectsViewHolder(view);
                        return subjectsViewHolder;
                    }
                };
        studentSubjectsRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
