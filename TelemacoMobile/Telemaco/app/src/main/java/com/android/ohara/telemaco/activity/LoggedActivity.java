package com.android.ohara.telemaco.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.ohara.telemaco.R;
import com.android.ohara.telemaco.dialog.ExitAccount;
import com.android.ohara.telemaco.entity.SingletonUser;

import java.util.ResourceBundle;

public class LoggedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ExitAccount.SingoutListener {
    private String userName;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_logged );

        SingletonUser singletonUser = SingletonUser.getInstance();
        userName  = singletonUser.getUser().getName();
        userEmail = singletonUser.getUser().getEmail();

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorOrange));

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.logged, menu );
        ((TextView) findViewById( R.id.txt_user_name )).setText( userName );
        ((TextView) findViewById( R.id.txt_user_email )).setText( userEmail );
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

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_getOut) {
            ExitAccount exitAccount = new ExitAccount();
            exitAccount.show(getFragmentManager(), "singoutAcount");
        } else if (id == R.id.nav_aboutUs) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity( intent );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    public void onSingOut() {
        SingletonUser singletonUser = SingletonUser.getInstance();
        singletonUser.setUser( null );

        SharedPreferences prefs = getSharedPreferences( "SESSION", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt( "USER_ID", -1 );
        editor.commit();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity( intent );
    }
}
