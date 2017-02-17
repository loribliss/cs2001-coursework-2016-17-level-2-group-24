package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import layout.FindFoodFragment;
import layout.FoodCupboardFragment;
import layout.Main_menu;
import layout.RecipesFragment;
import layout.ShoppingListFragment;

import static android.R.attr.fragment;
import static com.example.cs15fmk.foodmanagement.R.id.main_menu;
import static com.example.cs15fmk.foodmanagement.R.id.search;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //creates a actionbar(top) with attributes of toolbar id
        setSupportActionBar(toolbar);
        //checks whether you are sliding hamburger menu and the menu icon toggles to it
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //opens and closes hamburger menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar setting(top right).
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        String title = getString(R.string.app_name);
        Fragment fragment = null;
        int id = item.getItemId();
        switch (id) {
            case R.id.cupboard:

                fragment = new FoodCupboardFragment();
                title = "Food Cupboard";
                break;
            case R.id.find_food:
                fragment = new FindFoodFragment();
                title = "Find Food";
                break;
            case R.id.recipes:
                fragment = new RecipesFragment();
                title = "Recipes";
                break;
            case R.id.shopping_list:
                fragment = new ShoppingListFragment();
                title = "Shopping List";
                break;
        }
        //changes to different fragment when clicked
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        //set title for action bar
        getSupportActionBar().setTitle(title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onClickSignIn(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        {
            MainActivity.this.startActivity(intent);
        }
    }

    public void onClickSignUp(View v) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        {
            MainActivity.this.startActivity(intent);
        }
    }

    public void onClick(View v) {
        Fragment fragment = null;
        //NOT A CLUE HOW TO LINK IT TO THE PAGES
        switch (v.getId()) {
            case R.id.cupboard_main:
                Toast.makeText(MainActivity.this,
                        "Cupboard page", Toast.LENGTH_SHORT).show();
                fragment = new FoodCupboardFragment();
                break;
            case R.id.find_food_main:
                Toast.makeText(MainActivity.this,
                        "FIND FOOD PAGE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.recipes_main:
                Toast.makeText(MainActivity.this,
                        "RECIPES PAGE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shopping_list_main:
                Toast.makeText(MainActivity.this,
                        "SHOPPING LIST PAGE", Toast.LENGTH_SHORT).show();
                break;
        }
    }





}
