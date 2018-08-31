package com.android.ohara.telemaco.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.android.ohara.telemaco.R;
import com.android.ohara.telemaco.data.DAO.UserDAO;
import com.android.ohara.telemaco.entity.SingletonUser;
import com.android.ohara.telemaco.entity.User;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView( R.layout.activity_splash );
        getSupportActionBar().hide();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                getSession();
            }
        }, 3000);

    }

    public void getSession() {
        SharedPreferences prefs = getSharedPreferences( "SESSION", 0 );
        int user_id = prefs.getInt( "USER_ID", -1 );

        SingletonUser singletonUser = SingletonUser.getInstance();
        if (user_id != -1) {
            UserDAO userDAO = UserDAO.getInstance( this );
            User user = userDAO.select( user_id );
            singletonUser.setUser( user );

            Intent intent = new Intent( this, LoggedActivity.class );
            startActivity(intent);
        } else {
            Intent intent = new Intent( this, LoginActivity.class );
            startActivity(intent);
        }
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
    }
}
