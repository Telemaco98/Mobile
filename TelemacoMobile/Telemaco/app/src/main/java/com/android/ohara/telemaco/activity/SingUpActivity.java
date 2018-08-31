package com.android.ohara.telemaco.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.ohara.telemaco.Messages;
import com.android.ohara.telemaco.dialog.BackToLogin;
import com.android.ohara.telemaco.exceptions.UserAlreadyExistsException;
import com.android.ohara.telemaco.buniness.UserValidate;
import com.android.ohara.telemaco.R;

import java.text.ParseException;

public class SingUpActivity extends AppCompatActivity implements BackToLogin.BackListener {
    private Button btnConfirm;
    private String name;
    private String lastName;
    private String email;
    private String psw;
    private String pswConfirm;
    private String birth;
    private String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sing_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SF_Distant_Galaxy.ttf");
        btnConfirm.setTypeface(typeface);
    }

    public void onClick (View view) {
        if (!fieldEmpty() && !(pswError())) {
            try {
                UserValidate userValidate = new UserValidate();
                userValidate.validateRegisterUser(this, name, lastName, email, psw, birth, genre);

                Toast.makeText( this, Messages.USER_REGISTER_OK, Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( this, LoginActivity.class );
                startActivity( intent );
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText( this, Messages.BIRTH_INVALID, Toast.LENGTH_SHORT ).show();
            } catch (UserAlreadyExistsException e) {
                Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        }
    }

    private boolean fieldEmpty() {
        name        = String.valueOf( ((EditText) findViewById( R.id.inputName )).getText() );
        lastName    = String.valueOf( ((EditText) findViewById( R.id.inputLastName )).getText() );
        email       = String.valueOf( ((EditText) findViewById( R.id.inputEmail )).getText() );
        psw         = String.valueOf( ((EditText) findViewById( R.id.inputPsw )).getText() );
        pswConfirm  = String.valueOf( ((EditText) findViewById( R.id.inputPswConfirm )).getText() );
        birth       = String.valueOf( ((EditText) findViewById( R.id.inputBirthday )).getText() );
        RadioButton woman = (RadioButton) findViewById( R.id.rbtnWoman);
        if (woman.isChecked()) genre = "M";
        else genre = "H";

        if (name == null || name.equals("") ||
                lastName == null || lastName.equals("") ||
                email == null || email.equals("") ||
                psw == null  || psw.equals("") ||
                pswConfirm == null || pswConfirm.equals("") ||
                birth == null || birth.equals("")) {

            Toast.makeText( this, Messages.EMPTY_FIELDS, Toast.LENGTH_SHORT ).show();
            return true;
        } return false;
    }

    private boolean pswError () {
        if (!psw.equals( pswConfirm )) {
            ((EditText) findViewById( R.id.inputPswConfirm )).setText("");
            Toast.makeText( this, Messages.PSW_NOT_MATCH, Toast.LENGTH_SHORT ).show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        BackToLogin backToLogin = new BackToLogin();
        backToLogin.show( getFragmentManager(), "backToLogin" );
    }

    @Override
    public void onBack() {
        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );
    }
}
