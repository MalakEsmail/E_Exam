package com.example.e_exam.Professor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_exam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddChaptersFragment extends Fragment {
    String sunName;
    String chapter;
    EditText chapterEditText;
    Button addChapter;
    DatabaseReference reference;
    public AddChaptersFragment(String sunName) {
        this.sunName = sunName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_chapters,container,false);
        reference= FirebaseDatabase.getInstance().getReference().child("chapters");
         chapterEditText=(EditText)view.findViewById(R.id.editTextAddChaptersId);
         addChapter=(Button) view.findViewById(R.id.buttonAddChapterId);

        addChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              chapter= chapterEditText.getText().toString();
              if(TextUtils.isEmpty(chapter)){
                  Toast.makeText(getContext(), "Please..Enter Chapter Name !", Toast.LENGTH_SHORT).show();
              }else {
                  reference.child(sunName).child(chapter).child("chapterName").setValue(chapter);
                  DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
                  ref.child("chaptersList").push().setValue(chapter);
                  chapterEditText.setText("");
                  Toast.makeText(getContext(), "Chapter Added", Toast.LENGTH_SHORT).show();
              }
            }
        });

        return view;
    }
}
