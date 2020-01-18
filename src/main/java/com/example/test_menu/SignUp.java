package com.example.test_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText repeatPassword;
    Button validate;
    Button cancelSignUp;
    UserRoomDataBase dataBase;
    UserDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dataBase = UserRoomDataBase.getDatabase(getApplicationContext());
        dao = dataBase.UserDAO();
        Users[] users = dao.getAlphabetizedUsers();


        username = findViewById(R.id.signUp_userName);
        password = findViewById(R.id.signUp_password);
        repeatPassword = findViewById(R.id.signUp_repeat_password);
        validate = findViewById(R.id.createAccount);
        cancelSignUp = findViewById(R.id.cancelSignUp);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatPassword.getText().toString().equals(password.getText().toString())) {
                    Boolean notInBase = true;
                    for (Users user : users) {
                        System.out.println("/////" + user.getName()+"///////");
                        if (username.getText().toString().equals(user.getName())) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Un compte avec ce même nom d'utilisateur existe dèja.",
                                    Toast.LENGTH_LONG).show();
                            notInBase = false;
                        }
                    }

                    if (notInBase == true) {
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username.getText().toString());
                        bundle.putString("password", password.getText().toString());

                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Les champs password et Repeat Password ne sont pas équivalent",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
