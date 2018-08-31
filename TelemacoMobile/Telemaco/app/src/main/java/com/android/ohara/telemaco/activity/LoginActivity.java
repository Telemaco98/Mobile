package com.android.ohara.telemaco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ohara.telemaco.R;
import com.android.ohara.telemaco.data.DAO.UserDAO;
import com.android.ohara.telemaco.entity.SingletonUser;
import com.android.ohara.telemaco.exceptions.UserNotExistsException;
import com.android.ohara.telemaco.buniness.UserValidate;
import com.android.ohara.telemaco.entity.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button   btnConfirm;
    private TextView email;
    private TextView psw;
    private String   userEmail;
    private String   userPsw;
    private User     user;
    private SingletonUser singletonUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        getSupportActionBar().hide();

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SF_Distant_Galaxy.ttf");
        btnConfirm.setTypeface(typeface);

        email = (TextView) findViewById(R.id.inputEmail);
        psw   = (TextView) findViewById(R.id.inputPsw);
    }

//    private void showUsers() {
//        UserDAO userDAO = UserDAO.getInstance( this );
//        ArrayList<User> users = userDAO.selectAll();
//        for (User u : users) {
//            System.out.println(u.toString());
//            userDAO.delete(u);
//        }
//        System.out.println("onlar");
//    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.txtGoSingUp:
                Intent intent = new Intent(this, SingUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConfirm:
                verifyFields();
                break;
        }
    }

    public void verifyFields () {
        userEmail = email.getText().toString();
        userPsw  = psw.getText().toString();
        userPsw  = psw.getText().toString();
        if (userEmail == null || userEmail.equals("") || userPsw == null || userPsw.equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            UserValidate userValidate = new UserValidate();

            try {
                user = userValidate.validateLoginUser( this, userEmail, userPsw );

                SharedPreferences prefs = getSharedPreferences( "SESSION", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt( "USER_ID", user.getId() );
                editor.commit();

                singletonUser = SingletonUser.getInstance();
                singletonUser.setUser( user );

                Intent intent = new Intent(this, LoggedActivity.class);
                startActivity(intent);
            } catch (UserNotExistsException e) {
                Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
