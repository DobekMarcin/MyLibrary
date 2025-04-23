package com.example.firstproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruki Murakami", 100, "https://readinggroupchoices.com/wp/wp-content/uploads/2016/08/1Q84-High-Res-718x1024.jpg", "Short description", "Long description"));
        books.add(new Book(2, "Harry Potter 2", "J.K.Rowling", 345, "https://addisber.com/wp-content/uploads/2021/03/photo_2021-03-18_14-17-42-2-600x600.jpg", "Short description", "Long description"));

        adapter.setBooks(books);
    }
}