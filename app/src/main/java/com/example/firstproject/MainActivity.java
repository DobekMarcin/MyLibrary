package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWantToRead, btnFavorite, btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBooks.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });

    }

    private void initViews() {
    btnAllBooks = findViewById(R.id.btnAllBooks);
    btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
    btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
    btnWantToRead = findViewById(R.id.btnWantToRead);
    btnFavorite = findViewById(R.id.btnFavorite);
    btnAbout = findViewById(R.id.btnAbout);
    }
}