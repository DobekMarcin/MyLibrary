package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.Util;

public class AlreadyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     EdgeToEdge.enable(this);
        setContentView(R.layout.activity_already_read_book);


        RecyclerView recyclerView = findViewById(R.id.bookRecView);

        BookRecViewAdapter bookRecViewAdapter = new BookRecViewAdapter(this,"alreadyRead");
        recyclerView.setAdapter(bookRecViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookRecViewAdapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}