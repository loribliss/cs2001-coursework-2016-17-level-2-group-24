package com.example.cs15fmk.foodmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import layout.FindFoodFragment;
import layout.FoodCupboardFragment;
import layout.RecipesFragment;
import layout.ShoppingListFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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
/*
        ImageView cupboardButton = (ImageView) findViewById(R.id.cupboard_main);
        cupboardButton.setOnClickListener(this);
        ImageView findFoodButotn = (ImageView) findViewById(R.id.find_food);
        findFoodButotn.setOnClickListener(this);
        ImageView shoppingListButton = (ImageView) findViewById(R.id.shopping_list);
        shoppingListButton.setOnClickListener(this);
        ImageView recipesButton = (ImageView) findViewById(R.id.recipes);
        recipesButton.setOnClickListener(this);*/

//bottom right button on - need to change
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
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
                title = "Shoppping List";
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

   /* public void displayView(int ViewID) {
        String title = getString(R.string.app_name);
        Fragment fragment = null;
        switch (ViewID) {
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
                title = "Shoppping List";
                break;
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        //set title
        getSupportActionBar().setTitle(title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }*/


    public void onClickSignIn(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        {
            MainActivity.this.startActivity(intent);
        }
    }

    public void onClick(View v) {
         //doesnt work currently
        switch (v.getId()) {
            case R.id.cupboard:
                Toast.makeText(MainActivity.this,
                        "CUPBOARD PAGE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.find_food:
                Toast.makeText(MainActivity.this,
                        "FIND FOOD PAGE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.recipes:
                Toast.makeText(MainActivity.this,
                        "RECIPES PAGE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shopping_list:
                Toast.makeText(MainActivity.this,
                        "SHOPPING LIST PAGE", Toast.LENGTH_SHORT).show();
                break;
        }
       /* Fragment fragment = null;
        switch (v.getId()) {
            case R.id.cupboard:
                displayView();
                break;
            case R.id.find_food:
                fragment = new FindFoodFragment();
                break;
            case R.id.recipes:
                fragment = new RecipesFragment();
                break;
            case R.id.shopping_list:
                fragment = new ShoppingListFragment();
                break;
        }
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }*/
    }


}
