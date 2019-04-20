package com.example.oldbookbuyorsell;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mDrawerLayout = findViewById(R.id.userActivityLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BookListFragment bookListFragment = new BookListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.userFragmentContainer, bookListFragment).commit();

        navigationView = findViewById(R.id.user_drawer_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_user_profile:
                        //selectedFragment = new HomeFragment();
                        Toast.makeText(UserActivity.this,"Under Working",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_user_books:
                        selectedFragment = new BookListFragment();
                        break;
                    case R.id.nav_user_add_book:
                        selectedFragment = new AddBookFragment();
                        break;
                    case R.id.nav_user_my_book:
                        selectedFragment = new MyBookListFragment();
                        break;
                    case R.id.nav_user_logout:
                        Intent intent = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.userFragmentContainer, selectedFragment).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
