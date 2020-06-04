package com.example.e_exam.Professor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_exam.R;
import com.example.e_exam.model.Professors;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {
    private TextView name, phone, password;
    private String profPhone;
    private DatabaseReference reference;

    public Profile(String profPhone) {
        this.profPhone = profPhone;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        name = view.findViewById(R.id.name_name);
        phone = view.findViewById(R.id.phone_phone);
        password = view.findViewById(R.id.password_password);
        reference = FirebaseDatabase.getInstance().getReference().child("Professors").child("ProfessorsList").child(profPhone);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Professors professors = dataSnapshot.getValue(Professors.class);
                name.setText(professors.getName().toString());
                phone.setText(professors.getPhone().toString());
                password.setText(professors.getPassword().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
