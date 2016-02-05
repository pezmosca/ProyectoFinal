package com.example.toni.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etnick, etpassword;
    private LoginHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etnick = (EditText) findViewById(R.id.etnick);
        etpassword = (EditText) findViewById(R.id.etpassword);

        loginHelper = new LoginHelper(getApplicationContext(), "usuarios", null, 1);
    }

    public void newUser(View v) {

        ContentValues valuesToStore = new ContentValues();
        valuesToStore.put("nick", etnick.getText().toString());
        valuesToStore.put("password", etpassword.getText().toString());
        loginHelper.createPlayer(valuesToStore, "jugadores");

        Toast.makeText(getApplicationContext(), "Usuario añadido", Toast.LENGTH_SHORT).show();
        etnick.setText("");
        etpassword.setText("");
    }

    public void logIn(View v) {
        String nick = etnick.getText().toString();
        String password = etpassword.getText().toString();
        String realPassword = loginHelper.getPlayerPassword(nick);

        if (password.equals(realPassword)) {
            SharedPreferences logeado = getSharedPreferences("logs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = logeado.edit();
            editor.putBoolean("login", true);
            editor.commit();

            Intent intent = new Intent(this, LoginOk.class);
            startActivity(intent);
            finish();

        }

        else Toast.makeText(getApplicationContext(), "mal", Toast.LENGTH_SHORT).show();
    }
}
