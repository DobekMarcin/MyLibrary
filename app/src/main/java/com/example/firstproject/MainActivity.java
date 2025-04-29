package com.example.firstproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.temporal.WeekFields;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWantToRead, btnFavorite, btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBooks.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });

        Utils.getInstance(this);

        btnAlreadyRead.setOnClickListener(e->{
            Intent intent = new Intent(this, AlreadyReadBookActivity.class);
            startActivity(intent);

        });

        btnCurrentlyReading.setOnClickListener(e->{
            Intent intent = new Intent(this, CurrentlyReadingBooks.class);
            startActivity(intent);

        });

        btnWantToRead.setOnClickListener(e->{
            Intent intent = new Intent(this, WantToReadActivity.class);
            startActivity(intent);

        });

        btnFavorite.setOnClickListener(e->{
            Intent intent = new Intent(this, FavouriteReadingBooks.class);
            startActivity(intent);

        });

        btnAbout.setOnClickListener(e->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Developed by Marcin Dobek. My first Android app.\n"+
                    "Check my website for more app!");
            builder.setNegativeButton("Dismiss", (dialog, which) -> {

            });
            builder.setPositiveButton("Visit", (dialog, which) -> {
                Intent intent = new Intent(this, WebsiteActivity.class);
                intent.putExtra("url", "https://www.google.com/");
                startActivity(intent);
            });
            builder.create().show();
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