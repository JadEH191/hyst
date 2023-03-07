package com.example.hyst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    DatabaseReference myDB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hyst-f90db-default-rtdb.firebaseio.com/");
    Button btnConfirm;
    EditText etEmail, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnConfirm = findViewById(R.id.btnConfirm);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = etEmail.getText().toString();
                String getPass = etPassword.getText().toString();

                String email = getEmail.toString();
                String username = email.replaceAll("@.*","").replaceAll("[^a-zA-Z]+", " ").trim();

                if(getEmail.equals("") && getPass.equals("")) {
                    Toast.makeText(getApplicationContext(), username + "registered successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}