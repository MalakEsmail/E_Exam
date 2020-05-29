package com.example.e_exam.Admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_exam.R;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        Toolbar toolbar;
        DrawerLayout drawerLayout;
        NavigationView navigationView;
    FragmentTransaction transaction;
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

         manager=getSupportFragmentManager();
         transaction =manager.beginTransaction();

        toolbar =findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.opne, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView =findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.AddlevelsId:
                //calling class addLevel that extend Fragment
                AddLevel levelFragment=new AddLevel();
                transaction.replace(R.id.frame_container,levelFragment);
                transaction.commit();
                break;
            case R.id.showOrEditlevelsId:

                //calling class levelFragment that extend Fragment
                levelFragment levelsFragment=new levelFragment();

                transaction.replace(R.id.frame_container,levelsFragment);
                transaction.commit();
                break;
            case R.id.AddDepartmentsId:
                AddDepartment AddDepartmentFragement=new AddDepartment();
                transaction.replace(R.id.frame_container,AddDepartmentFragement);
                transaction.commit();
                break;
            case R.id.showOrEditDepartmentsId:
                DepartmentFragment departmentFragment=new DepartmentFragment();

                transaction.replace(R.id.frame_container,departmentFragment);
                transaction.commit();
                break;
            case R.id.addSubjectsId:
                AddSubjectFragement ADDSubjectFragement=new AddSubjectFragement();
                transaction.replace(R.id.frame_container,ADDSubjectFragement);
                transaction.commit();

                break;
            case R.id.showOrEditSubjectsId:
                show_edit_subject_fragment Show_edit_SubjectFragment=new show_edit_subject_fragment();

                transaction.replace(R.id.frame_container,Show_edit_SubjectFragment);
                transaction.commit();

                break;

            case R.id.ProfessorsRequestsId:
                RequestsFragement proRequestsFragement=new RequestsFragement();
                transaction.replace(R.id.frame_container,proRequestsFragement);
                transaction.commit();
                break;
            case R.id.ShowProfessorsListId:
                ProfessorsListFragment professorsFragement=new ProfessorsListFragment();
                transaction.replace(R.id.frame_container,professorsFragement);
                transaction.commit();
                break;

            case R.id.LogoutId:
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
