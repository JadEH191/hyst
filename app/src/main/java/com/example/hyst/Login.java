package com.example.hyst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference myDB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hyst-f90db-default-rtdb.firebaseio.com/");
    TextView aSignup;
    Button btnConfirm, btnTesting;
    EditText etEmail, etPassword;

    String name, emailtest, pass;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnTesting = findViewById(R.id.btnTesting);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        aSignup = findViewById(R.id.aSignUp);
        aSignup.setPaintFlags(aSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        aSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ASignUp();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = etEmail.getText().toString();
                String getPass = etPassword.getText().toString();

                String email = etEmail.getText().toString();
                String username = email.replaceAll("@.*", "").replaceAll("[^a-zA-Z]+", " ").trim();

                user = username;

                if (getEmail.equals("") || getPass.equals("")) {
                    if (getEmail.equals("") && getPass.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill up the field.", Toast.LENGTH_LONG).show();
                    } else if (getPass.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill up Password.", Toast.LENGTH_LONG).show();
                    } else if (getEmail.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill up Email.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    myDB.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(username)) {
                                String getDBName = snapshot.child(username).child("username").getValue(String.class);
                                String getDBEmail = snapshot.child(username).child("email").getValue(String.class);
                                String getDBPass = snapshot.child(username).child("password").getValue(String.class);

                                name = getDBName;
                                emailtest = getDBEmail;
                                pass = getDBPass;

                                if (getEmail.equals(getDBEmail) && getPass.equals(getDBPass)) {
                                    Login();

                                    Toast.makeText(getApplicationContext(), getDBEmail + " " + getDBPass, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please fill up the field.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = etEmail.getText().toString();
                String getPass = etPassword.getText().toString();

                String email = etEmail.getText().toString();
                String username = email.replaceAll("@.*", "").replaceAll("[^a-zA-Z]+", " ").trim();

                user = username;

                if (getEmail.equals("") || getPass.equals("")) {
                    if (getEmail.equals("") && getPass.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill up the field.", Toast.LENGTH_LONG).show();
                    } else if (getPass.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill up Password.", Toast.LENGTH_LONG).show();
                    } else if (getEmail.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill up Email.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    myDB.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(username)) {
                                String getDBName = snapshot.child(username).child("username").getValue(String.class);
                                String getDBEmail = snapshot.child(username).child("email").getValue(String.class);
                                String getDBPass = snapshot.child(username).child("password").getValue(String.class);

                                name = getDBName;
                                emailtest = getDBEmail;
                                pass = getDBPass;

                                if (getEmail.equals(getDBEmail) && getPass.equals(getDBPass)) {
                                    DisplayInfo();

                                    Toast.makeText(getApplicationContext(), getDBEmail + " " + getDBPass, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please fill up the field.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    public void Login() {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
        finish();
    }

    public void DisplayInfo() {
        Intent intent = new Intent(this, Display_Info.class);

        intent.putExtra("username", name);
        intent.putExtra("email", emailtest);
        intent.putExtra("pass", pass);

        startActivity(intent);
        finish();
    }

    public void ASignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
}