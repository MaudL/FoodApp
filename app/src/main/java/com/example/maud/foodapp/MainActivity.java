package com.example.maud.foodapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {

        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout goToRecipes = findViewById(R.id.goToRecipes);
        goToRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iAddStudent = new Intent(MainActivity.this, ListRecipesActivity.class);
                startActivity(iAddStudent);
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        getMenuInflater().inflate(R.menu.main, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        int id = pItem.getItemId();
       /* if (id == R.id.action_settings) {
            Intent iAddStudent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(iAddStudent);
            return true;
        }*/

        return super.onOptionsItemSelected(pItem);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_recipe) {
            Intent iRecipe = new Intent(MainActivity.this, AddRecipeActivity.class);
            buildAlertDialogDataset(iRecipe);

        } if (id == R.id.go_to_salty) {
            Intent iRecipe = new Intent(MainActivity.this, ListRecipesActivity.class);
            startActivity(iRecipe);
        } if (id == R.id.go_to_sweet) {
            Intent iRecipe = new Intent(MainActivity.this, ListRecipesActivity.class);
            startActivity(iRecipe);
        } if (id == R.id.deconnection) {
            //finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        return true;
    }

    private void buildAlertDialogDataset(final Intent iRecipe) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText edittext = new EditText(this);
        alert.setMessage("Nom de ma recette");

        alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String nameRecipe = edittext.getText().toString();
                iRecipe.putExtra("nameRecipe", nameRecipe);
                startActivity(iRecipe);
            }
        });

        alert.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }
}