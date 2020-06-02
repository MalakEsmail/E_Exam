package com.example.e_exam.Professor;

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

public class subjectActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar subject_toolbar;
    DrawerLayout subject_drawerLayout;
    NavigationView subject_navigationView;
    FragmentTransaction transaction;
    FragmentManager manager;
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        subject = getIntent().getExtras().getString("sub");
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        subject_toolbar = findViewById(R.id.toolBarSubjectId);
        setSupportActionBar(subject_toolbar);
        subject_drawerLayout = findViewById(R.id.drawerSubjectLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, subject_drawerLayout, subject_toolbar,
                R.string.opne, R.string.close);
        subject_drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        subject_navigationView = findViewById(R.id.navigation_subject_view);

        subject_navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment(subject);
        transaction.replace(R.id.frame_Subject_container, homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.FinishId:
                finish();
                break;
        }
        subject_drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
