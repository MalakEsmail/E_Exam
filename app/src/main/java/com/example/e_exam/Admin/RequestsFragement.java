package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;

import java.util.ArrayList;

public class RequestsFragement extends Fragment {
    RecyclerView professorRequestsRecyclerViewList;
    RecyclerView.LayoutManager layoutManager5;
    Requests_recycler_view_Adapter requestsAdapterRecyclerView;
    ArrayList<Set_get_professors_name_requests> requestsArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.professors_requests_recycler_view,container,false);
        //add recycler view to show subjects list
        requestsList();
        professorRequestsRecyclerViewList=(RecyclerView) v.findViewById(R.id.professorsRequestsRecyclerViewId);
        professorRequestsRecyclerViewList.setHasFixedSize(true);
        layoutManager5=new LinearLayoutManager(getContext());
        professorRequestsRecyclerViewList.setLayoutManager(layoutManager5);
        requestsAdapterRecyclerView=new Requests_recycler_view_Adapter(getContext(),requestsArrayList);
        professorRequestsRecyclerViewList.setAdapter(requestsAdapterRecyclerView);

        return v;
    }
    //method date set at recyclerView list
    private void requestsList() {
        requestsArrayList =new ArrayList<Set_get_professors_name_requests> ();
        // Todo Admin who is responsible for adding  subjects in  this system
        requestsArrayList.add(new Set_get_professors_name_requests("ahmed"));
        requestsArrayList.add(new Set_get_professors_name_requests("Ali"));
        requestsArrayList.add(new Set_get_professors_name_requests("Rem"));
        requestsArrayList.add(new Set_get_professors_name_requests("nora"));


    }
}
