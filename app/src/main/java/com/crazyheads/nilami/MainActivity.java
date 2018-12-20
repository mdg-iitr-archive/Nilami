package com.crazyheads.nilami;

import android.content.Intent;
import android.graphics.Color;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        AllAuctionFragment.AllAuctionListner
{
    //declarations
    private DrawerLayout mDrawerLayout;
    FirebaseAuth mAuth;
    Toolbar toolbar;
    ActionBar actionbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        setToolbar();

        setNavigationDrawer();


        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new NeedHelpFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_all_auctions);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {   case R.id.nav_all_auctions:
                break;

            case R.id.nav_your_auctions:
                break;

            case R.id.nav_interested_in:
                break;

            case R.id.nav_bidding_arena:
                break;

            case R.id.nav_help:
                replaceFragment(new NeedHelpFragment());


                break;

            case R.id.nav_notifications:
                break;

            //case R.id.nav_ongoing_auction:
            //    break;

            case R.id.nav_signout:

                //show pop-up seeking confirmation
                mAuth.signOut();
                finish();
                Intent intent=new Intent(MainActivity.this,Login.class);

                //to clear stack
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }


    //Open the drawer when the button is tapped
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);


    }


    @Override
    public void requestForAddAuctionFragment() {

        replaceFragment(new AddAuctionFragment());

    }

    private void replaceFragment(Fragment newFragment) {
        // Create new transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


    private void setNavigationDrawer() {

        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

        //making selection action on navigation drawer work
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setToolbar() {

        //make toolbar act as app bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        //add button on toolbar
        actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu_icon);

    }
}



