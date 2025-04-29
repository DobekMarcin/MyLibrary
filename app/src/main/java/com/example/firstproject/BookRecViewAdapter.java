package com.example.firstproject;

import static com.example.firstproject.BookActivity.BOOK_ID_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import androidx.transition.Visibility;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.txtBookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageURL())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(e -> {
            // Toast.makeText(mContext, books.get(position).getName() + "Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, BookActivity.class);
            intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
            mContext.startActivity(intent);

        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.bntDelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyRead")) {
                holder.bntDelete.setVisibility(View.VISIBLE);
                holder.bntDelete.setOnClickListener(e -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + " book?");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if (Utils.getInstance().removeBookFromAlreadyRead(books.get(position))) {
                            Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("No", (dialogInterface, i) -> {

                    });
                    builder.create().show();
                });
            } else if (parentActivity.equals("wantToRead")) {
                holder.bntDelete.setVisibility(View.VISIBLE);
                holder.bntDelete.setOnClickListener(e -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + " book?");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if (Utils.getInstance().removeFromWantToRead(books.get(position))) {
                            Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("No", (dialogInterface, i) -> {

                    });
                    builder.create().show();
                });
            } else if (parentActivity.equals("currentlyReading")) {
                holder.bntDelete.setVisibility(View.VISIBLE);
                holder.bntDelete.setOnClickListener(e -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + " book?");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if (Utils.getInstance().removeFromCurrentlyReading(books.get(position))) {
                            Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("No", (dialogInterface, i) -> {

                    });
                    builder.create().show();
                });
            } else if (parentActivity.equals("favouriteBooks")) {
                holder.bntDelete.setVisibility(View.VISIBLE);
                holder.bntDelete.setOnClickListener(e -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + " book?");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if (Utils.getInstance().removeFromFavouriteBooks(books.get(position))) {
                            Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("No", (dialogInterface, i) -> {

                    });
                    builder.create().show();
                });
            }
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgBook;
        private TextView txtBookName;
        private ImageView btnDownArrow, btnUpArrow;
        private RelativeLayout collapsedRelLayout, expandedRelLayout;
        private TextView txtAuthor, txtShortDesc;
        private TextView bntDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            btnDownArrow = itemView.findViewById(R.id.btnDownArrow);
            btnUpArrow = itemView.findViewById(R.id.btnUpArrow);
            collapsedRelLayout = itemView.findViewById(R.id.collapsedRelLayout);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            bntDelete = itemView.findViewById(R.id.bntDelete);

            btnDownArrow.setOnClickListener(e -> {
                Book selectedBook = books.get(getAdapterPosition());
                selectedBook.setExpanded(!selectedBook.isExpanded());
                notifyItemChanged(getAdapterPosition());

            });

            btnUpArrow.setOnClickListener(e -> {
                Book selectedBook = books.get(getAdapterPosition());
                selectedBook.setExpanded(!selectedBook.isExpanded());
                notifyItemChanged(getAdapterPosition());

            });
        }
    }
}
