package com.example.oldbookbuyorsell;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookListRecyclerAdapter extends RecyclerView.Adapter<BookListRecyclerAdapter.ViewHolder>{

    Context context;
    String user;
    private ArrayList<String> bookImage = new ArrayList<>();
    private ArrayList<String> bookId = new ArrayList<>();
    private ArrayList<String> bookName = new ArrayList<>();
    private ArrayList<String> bookPrice = new ArrayList<>();
    private ArrayList<String> bookDetails = new ArrayList<>();
    private ArrayList<String> bookOwnerPhone = new ArrayList<>();
    private ArrayList<String> bookOnerEmail = new ArrayList<>();


    public BookListRecyclerAdapter(Context context, String user, ArrayList<String> bookImage, ArrayList<String> bookId, ArrayList<String> bookName, ArrayList<String> bookPrice, ArrayList<String> bookDetails, ArrayList<String> bookOwnerPhone, ArrayList<String> bookOnerEmail) {
        this.context = context;
        this.user = user;
        this.bookImage = bookImage;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookDetails = bookDetails;
        this.bookOwnerPhone = bookOwnerPhone;
        this.bookOnerEmail = bookOnerEmail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        BackgroundWorker backgroundWorker = new BackgroundWorker(context);

        String subUrl = "http://"+backgroundWorker.ipAddress;

        //holder.bookImageView.setImageResource(R.drawable.book);
        holder.bookNameTv.setText(bookName.get(i));
        holder.bookPriceTv.setText("Price: "+bookPrice.get(i));
        holder.bookDetailsTv.setText("Details: "+bookDetails.get(i));
        holder.bookOwnerPhoneTv.setText("Phone: "+bookOwnerPhone.get(i));
        holder.bookOwnerEmailTv.setText("Email: "+bookOnerEmail.get(i));

        //Picasso.get().load("").fit().centerCrop().into(holder.bookImageView);

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });
        builder.build().load(subUrl+bookImage.get(i)).into(holder.bookImageView);


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "delete_book";

                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                backgroundWorker.execute(type, bookId.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton deleteBtn;
        ImageView bookImageView;
        TextView bookNameTv;
        TextView bookPriceTv;
        TextView bookDetailsTv;
        TextView bookOwnerPhoneTv;
        TextView bookOwnerEmailTv;

        public ViewHolder(View itemView) {
            super(itemView);

            deleteBtn = itemView.findViewById(R.id.bookDeleteIb);
            bookImageView = itemView.findViewById(R.id.bookImageView);
            bookNameTv = itemView.findViewById(R.id.bookNameTv);
            bookPriceTv = itemView.findViewById(R.id.bookPriceTv);
            bookDetailsTv = itemView.findViewById(R.id.bookDetailsTv);
            bookOwnerPhoneTv = itemView.findViewById(R.id.bookOwnerPhoneTv);
            bookOwnerEmailTv = itemView.findViewById(R.id.bookOwnerEmailTv);

            if (user.equals("User")){
                deleteBtn.setVisibility(View.GONE);
            }else if (user.equals("Admin")){
                deleteBtn.setVisibility(View.VISIBLE);
            }
            else {
                deleteBtn.setVisibility(View.VISIBLE);
            }

        }
    }
}
