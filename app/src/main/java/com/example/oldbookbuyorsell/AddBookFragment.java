package com.example.oldbookbuyorsell;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends Fragment {
    EditText bookNameEt, bookDetailsEt, bookPriceEt;
    Button addBtn;

    String email, phone;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_book, container, false);


        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        email = backgroundWorker.email;
        phone = backgroundWorker.phone;

        bookNameEt = v.findViewById(R.id.addBookName);
        bookDetailsEt = v.findViewById(R.id.addBookDetails);
        bookPriceEt = v.findViewById(R.id.addBookPrice);
        addBtn = v. findViewById(R.id.addBookBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });

        return v;
    }

    private void addBook() {
        String type = "add_book";
        String bookName = bookNameEt.getText().toString().trim();
        String bookDetails = bookDetailsEt.getText().toString().trim();
        String bookPrice = bookPriceEt.getText().toString().trim();

        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute(type, bookName, bookDetails, bookPrice, email, phone);
    }

}
