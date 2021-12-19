package com.example.todoapplication_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextPassword, editTextUser;
    Button btnSignIn, btnSignUp;
    ImageView imageView;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        signIn();
        signUp();
    }

    private void signUp() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void signIn() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.equals("")||password.equals("")) {
                    Toast.makeText(MainActivity.this, "You're missing something, check again", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkCredentials = database.checkUsrPwd(username, password);
                    if (checkCredentials) {
                        Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Homepage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "This information not match our database", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        /*btnSignIn.setOnClickListener(
                v -> Toast.makeText(MainActivity.this, "This button is on development", Toast.LENGTH_LONG).show());
        /*btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });*/
    }
    private void initView() {
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUser = findViewById(R.id.editTextUser);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        imageView = findViewById(R.id.imageView);
        database = new DBHelper(this);
    }
}