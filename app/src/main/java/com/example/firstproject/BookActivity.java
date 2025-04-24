package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtLongDescription;
    private ImageView imgBook;
    private Button btnAddToCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAddToFavorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);

        initViews();

//        String longDes = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.";
//
//        //TODO: get data from recycler view
//        Book book = new Book(1, "1Q84", "Haruki Murakami", 100, "https://readinggroupchoices.com/wp/wp-content/uploads/2016/08/1Q84-High-Res-718x1024.jpg", "Short description", longDes);


        Intent intent = getIntent();
        if (null != intent) {
            int id = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (id != -1) {

                Book incomingBook = Utils.getInstance().getBookById(id);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleFavoriteBook(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBook(Book incomingBook) {
    }

    private void handleCurrentlyReadingBook(Book incomingBook) {
        
    }

    private void handleWantToReadBook(Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for(Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }
        if(existInWantToReadBooks){
            btnWantToRead.setEnabled(false);
        } else{
            btnWantToRead.setOnClickListener(e-> {
                if(Utils.getInstance().addBookToWantToReadList(book)){
                    Toast.makeText(this, "Book added!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for(Book b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAlreadyRead.setEnabled(false);
        } else{
            btnAlreadyRead.setOnClickListener(e-> {
                if(Utils.getInstance().addBookToAlreadyRead(book)){
                    Toast.makeText(this, "Book added!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        }
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