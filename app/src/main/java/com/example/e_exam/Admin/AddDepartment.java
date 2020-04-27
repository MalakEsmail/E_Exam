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

public class AddDepartment extends Fragment {
    Button buttonAddDept;
    EditText enterDepartName;
    String department;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.add_department,container,false);
         buttonAddDept=(Button) view.findViewById(R.id.buttonAddDepartmentId);
        enterDepartName=(EditText) view.findViewById(R.id.editTextAddDepartmentId);
        ref= FirebaseDatabase.getInstance().getReference();


        buttonAddDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                department = enterDepartName.getText().toString();
                if (TextUtils.isEmpty(department)) {
                    Toast.makeText(getContext(), "Please enter Department Name ", Toast.LENGTH_LONG).show();
                }
                else {
                    ref.child("Departments").push().setValue(department);
                    enterDepartName.setText("");
                    Toast.makeText(getContext(),"department added",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view ;
    }
}
