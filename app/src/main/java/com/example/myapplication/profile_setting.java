package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile_setting extends AppCompatActivity {
    ImageButton btnPSback;
    ImageButton btnLogout;

    FirebaseAuth mAuth;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_setting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnPSback = findViewById(R.id.btnPSback);
        btnPSback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile_setting.this, Settings.class);
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnLogout);
        user = mAuth.getCurrentUser();
        if(user == null){
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
            finish();
        }
    }
}