package com.example.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerUpdated extends AppCompatActivity {
    TextView textview;
    EditText etEmail, etPass;
    FirebaseAuth mAuth;
    Button btnRegister;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Homepage.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.password);
        btnRegister = findViewById(R.id.register);
        CheckBox cbAgree = findViewById(R.id.checkBoxAgree);

        btnRegister.setOnClickListener(view -> {
            String username, email, password;
            email = String.valueOf(etEmail.getText());
            password = String.valueOf(etPass.getText());

            StringBuilder errorMessage = new StringBuilder();

            if (TextUtils.isEmpty(email)) {
                errorMessage.append("Enter Email\n");
            }

            if (TextUtils.isEmpty(password)) {
                errorMessage.append("Enter Password\n");
            }

            if (!cbAgree.isChecked()) {
                errorMessage.append("Agree to Terms & Conditions");

            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(registerUpdated.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(registerUpdated.this, Login.class));
                        } else {
                            Toast.makeText(registerUpdated.this, "Account creation failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
        textview = (TextView) findViewById(R.id.login);
        textview.setPaintFlags(textview.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerUpdated.this, Login.class);
                startActivity(intent);
            }
        });
    }
}