package com.example.e_exam.Admin;

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

import java.util.HashMap;

public class AddLevel extends Fragment {
    Button btnAddLevel;
    EditText enterLevelEditText;
    String level;
    DatabaseReference ref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.add_level,container,false);
        btnAddLevel=(Button) view.findViewById(R.id.buttonAddLevelId);
        enterLevelEditText=(EditText) view.findViewById(R.id.editTextAddLevelId);
        //get reference to database
        ref= FirebaseDatabase.getInstance().getReference();

        btnAddLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = enterLevelEditText.getText().toString();
                if (TextUtils.isEmpty(level)) {
                    Toast.makeText(getContext(), "Please enter level Name ", Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String , Object > hashMap = new HashMap<>();
                    hashMap.put("levelName" , level);
                    ref.child("Levels").child("levelName").push().setValue(hashMap);
                    enterLevelEditText.setText("");
                    Toast.makeText(getContext(), "Level Added", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
