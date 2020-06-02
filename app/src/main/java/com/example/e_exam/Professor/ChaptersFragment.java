package com.example.e_exam.Professor;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.ViewHolders.ChaptersViewHolder;
import com.example.e_exam.model.Chapters;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChaptersFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Chapters, ChaptersViewHolder> adapter;

    DatabaseReference reference;
   String subName;

    public ChaptersFragment(String subName) {
        this.subName = subName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.recycler_view,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.RecyclerViewId);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        showFromDatabase();

        return view;

    }

    private void showFromDatabase() {
        reference= FirebaseDatabase.getInstance().getReference().child("chapters").child(subName);

        final FirebaseRecyclerOptions<Chapters> options = new FirebaseRecyclerOptions.Builder<Chapters>()
                .setQuery(reference, Chapters.class)
                .build();
        adapter=new FirebaseRecyclerAdapter<Chapters, ChaptersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ChaptersViewHolder holder, int i, @NonNull final Chapters chapters) {
                holder.chapterName.setText(chapters.getChapterName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence option[] = new CharSequence[]{
                                "Delete",
                                "Add Questions"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Managing this Chapter By : ");
                        builder.setItems(option, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(i==0){
                                    Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                                reference.child(chapters.getChapterName()).removeValue();
                                }
                                if(i==1){
                                    Fragment fragment = new AddQuestionFragment(subName,chapters.getChapterName());
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.frame_Subject_container, fragment);
                                    fragmentTransaction.commit();

                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public ChaptersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_edit_chapters_row, parent, false);
                ChaptersViewHolder holder = new ChaptersViewHolder(view);
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
