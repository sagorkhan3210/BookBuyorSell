package com.example.oldbookbuyorsell;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookListFragment extends Fragment {
    String user;
    private ArrayList<String> bookImage = new ArrayList<>();
    private ArrayList<String> bookId = new ArrayList<>();
    private ArrayList<String> bookName = new ArrayList<>();
    private ArrayList<String> bookPrice = new ArrayList<>();
    private ArrayList<String> bookDetails = new ArrayList<>();
    private ArrayList<String> bookOwnerPhone = new ArrayList<>();
    private ArrayList<String> bookOnerEmail = new ArrayList<>();

    JSONObject jsonObject;
    JSONArray jsonArray;


    static String  email, productJsonString;
    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_book_list, container, false);

        user = backgroundWorker.userType;
        email = backgroundWorker.email;

        //Toast.makeText(getActivity(), user_email, Toast.LENGTH_SHORT).show();

        getDataFromDb();
        getValueFromBw();

        return v;
    }

    private void getDataFromDb() {
        String type = "book_list";

        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute(type, email);

    }
    private void getValueFromBw() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressDialog.show();
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();

                productJsonString = backgroundWorker.productJsonString;

                /*AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Buyer Order");
                alertDialog.setMessage("Actor: "+user+"json"+productJsonString);
                alertDialog.show();*/

                convertJsonToArray();
                initRecyclerView();

            }
        }.start();


    }
    private void convertJsonToArray() {
        bookImage.clear();
        bookId.clear();
        bookName.clear();
        bookPrice.clear();
        bookDetails.clear();
        bookOwnerPhone.clear();
        bookOnerEmail.clear();

        try {
            jsonObject = new JSONObject(productJsonString);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String b_id, b_name, b_details, b_price, b_image, b_o_email, b_o_phone;

            while (count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                b_id = jo.getString("bookId");
                b_name = jo.getString("bookName");
                b_details = jo.getString("bookDetails");
                b_price = jo.getString("bookPrice");
                b_image = jo.getString("bookImage");
                b_o_email = jo.getString("ownerEmail");
                b_o_phone = jo.getString("ownerPhone");


                //Toast.makeText(getActivity(),b_id,Toast.LENGTH_SHORT).show();

                bookImage.add(b_image);
                bookId.add(b_id);
                bookName.add(b_name);
                bookPrice.add(b_price);
                bookDetails.add(b_details);
                bookOwnerPhone.add(b_o_phone);
                bookOnerEmail.add(b_o_email);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initRecyclerView() {
        if (user.equals("User")){
            user = user+"MyList";
        }
        RecyclerView recyclerView = v.findViewById(R.id.myBookRecyclerView);
        BookListRecyclerAdapter adapter = new BookListRecyclerAdapter(getActivity(), user, bookImage, bookId, bookName, bookPrice, bookDetails, bookOwnerPhone, bookOnerEmail);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
