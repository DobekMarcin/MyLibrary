package com.example.firstproject;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static  ArrayList<Book> alreadyReadBooks;
    private static  ArrayList<Book> wantToReadBooks;
    private static  ArrayList<Book> currentlyReadingBooks;
    private static  ArrayList<Book> favoriteBooks;




    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if(null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {


        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 100, "https://readinggroupchoices.com/wp/wp-content/uploads/2016/08/1Q84-High-Res-718x1024.jpg", "Short description", "Long description"));
        allBooks.add(new Book(2, "Harry Potter 2", "J.K.Rowling", 345, "https://addisber.com/wp-content/uploads/2021/03/photo_2021-03-18_14-17-42-2-600x600.jpg", "Short description", "Long description"));


        //TODO: add books to list
    }


    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
            return instance;

        } else {
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id){
        for(Book book : allBooks){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }
}
