package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_exam.R;

public class LevelsFragment extends Fragment {
    FragmentTransaction transaction;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_levels, container, false);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        LinearLayout addLevel = (LinearLayout) view.findViewById(R.id.add_level);
        LinearLayout levelList = (LinearLayout) view.findViewById(R.id.level_list);

        addLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLevel levelFragment = new AddLevel();
                transaction.replace(R.id.frame_container, levelFragment);
                transaction.commit();
            }
        });
        levelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelFragment levelsFragment = new levelFragment();
                transaction.replace(R.id.frame_container, levelsFragment);
                transaction.commit();
            }
        });

        return view;
    }
}
