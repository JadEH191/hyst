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

public class SignUp extends AppCompatActivity {

    DatabaseReference myDB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hyst-f90db-default-rtdb.firebaseio.com/");
    Button btnConfirm;
    TextView aLogin;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnConfirm = findViewById(R.id.btnConfirm);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        aLogin = findViewById(R.id.aLogin);
        aLogin.setPaintFlags(aLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        aLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ALogin();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = etEmail.getText().toString();
                String getPass = etPassword.getText().toString();

                String email = etEmail.getText().toString();
                String username = email.replaceAll("@.*", "").replaceAll("[^a-zA-Z]+", " ").trim();

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
                            myDB.child("users").child(username).child("username").setValue(username);
                            myDB.child("users").child(username).child("email").setValue(getEmail);
                            myDB.child("users").child(username).child("password").setValue(getPass);

                            Toast.makeText(getApplicationContext(), username + " registered successfully", Toast.LENGTH_LONG).show();
                            MainActivity();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
    public void MainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void ALogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}