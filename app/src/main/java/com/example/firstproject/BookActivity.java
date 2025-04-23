package com.example.firstproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtPages, txtLongDescription;
    private ImageView imgBook;
    private Button btnAddToCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAddToFavorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);

        initViews();

        //TODO: get data from recycler view
        Book book = new Book(1, "1Q84", "Haruki Murakami", 100, "https://readinggroupchoices.com/wp/wp-content/uploads/2016/08/1Q84-High-Res-718x1024.jpg", "Short description", "Long description");

        setData(book);
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtLongDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(imgBook);

    }

    private void initViews() {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtLongDescription = findViewById(R.id.txtLongDescription);
        imgBook = findViewById(R.id.imageBook);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
    }
}