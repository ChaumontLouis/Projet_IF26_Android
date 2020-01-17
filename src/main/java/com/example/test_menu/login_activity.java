package com.example.test_menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends AppCompatActivity {

    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 2;

    private UserRoomDataBase userRoomDataBase;
    private UserDAO userDAO;

    private EditText userName;
    private EditText userMdp;
    private Button loginButton;
    private Button facebbok;
    private Button google;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.login_username);
        userMdp = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login);
        facebbok = findViewById(R.id.button_facebook);
        google = findViewById(R.id.button_google);
        signUp = findViewById(R.id.button_sign_up);
        userRoomDataBase = UserRoomDataBase.getDatabase(getApplicationContext());
        userDAO = userRoomDataBase.UserDAO();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users[] users;
                users = userDAO.getAlphabetizedUsers();
                for (Users users1 : users) {
                    if (users1.getName().equals(userName.getText().toString()) && users1.getMdp().equals(userMdp.getText().toString())) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        UserLogged.UserLooged = users1.getName();
                        startActivity(intent);
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();

            String username = bundle.getString("username");
            String password = bundle.getString("password");

            Users user = new Users(username,password);
            userDAO.insert(user);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Enregistrement échoué",
                    Toast.LENGTH_LONG).show();
        }
    }

}
