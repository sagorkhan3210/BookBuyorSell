package com.example.oldbookbuyorsell;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;

    AlertDialog alertDialog;
    String type;
    static String result = "";
    String line = "";
    String flag = "";

    //for getting user info
    static String productJsonString = "";
    static String userType = "";
    static String id = "";
    static String name = "";
    static String email = "";
    static String phone = "";
    static String ipAddress = "";

    public BackgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        type = strings[0];

        ipAddress = "192.168.0.106";

        String admin_login_url = "http://" + ipAddress + "/old_book_buy_sell/admin_login.php";
        String user_login_url = "http://" + ipAddress + "/old_book_buy_sell/user_login.php";
        String user_reg_url = "http://" + ipAddress + "/old_book_buy_sell/user_register.php";
        String book_list_url = "http://" + ipAddress + "/old_book_buy_sell/get_book_list.php";
        String add_book_url = "http://" + ipAddress + "/old_book_buy_sell/add_book.php";
        String delete_book_url = "http://" + ipAddress + "/old_book_buy_sell/delete_book.php";

        result = "";
        line = "";

        if (type.equals("admin_login")) {
            try {
                String user_id = strings[1];
                String user_pass = strings[2];
                URL url = new URL(admin_login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("user_login")) {
            try {
                String user_id = strings[1];
                String user_pass = strings[2];
                URL url = new URL(user_login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("user_register")) {
            try {
                String user_id = strings[1];
                String user_name = strings[2];
                String user_email = strings[3];
                String user_phone = strings[4];
                String user_pass = strings[5];

                URL url = new URL(user_reg_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(user_email, "UTF-8") + "&" +
                        URLEncoder.encode("user_phone", "UTF-8") + "=" + URLEncoder.encode(user_phone, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("book_list")) {
            try {

                String user_email = strings[1];

                String JSON_STRING;

                URL url = new URL(book_list_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(user_email, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("add_book")) {
            try {
                String book_name = strings[1];
                String book_details = strings[2];
                String book_price = strings[3];
                String owner_email = strings[4];
                String owner_phone = strings[5];


                String book_image = "/images/placeholder.jpg";

                URL url = new URL(add_book_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("book_name", "UTF-8") + "=" + URLEncoder.encode(book_name, "UTF-8") + "&" +
                        URLEncoder.encode("book_details", "UTF-8") + "=" + URLEncoder.encode(book_details, "UTF-8") + "&" +
                        URLEncoder.encode("book_price", "UTF-8") + "=" + URLEncoder.encode(book_price, "UTF-8") + "&" +
                        URLEncoder.encode("book_image", "UTF-8") + "=" + URLEncoder.encode(book_image, "UTF-8") + "&" +
                        URLEncoder.encode("owner_email", "UTF-8") + "=" + URLEncoder.encode(owner_email, "UTF-8") + "&" +
                        URLEncoder.encode("owner_phone", "UTF-8") + "=" + URLEncoder.encode(owner_phone, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("delete_book")) {
            try {
                String book_id = strings[1];

                URL url = new URL(delete_book_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("book_id", "UTF-8") + "=" + URLEncoder.encode(book_id, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

        if (type.equals("admin_login") && !result.equals("Wrong email or password")) {

            Toast.makeText(context, "Successfully login " + result, Toast.LENGTH_SHORT).show();
            /*Intent in = new Intent(context, BuyerActivity.class);
            context.startActivity(in);*/

            int count = 0;
            String space = " ";
            String[] words = result.split(space);
            for (String word : words) {
                if (word.trim().length() > 0) {
                    if (count == 0) {
                        id = word;
                    } else if (count == 1) {
                        name = word;
                    } else if (count == 2) {
                        email = word;
                    } else if (count == 3) {
                        phone = word;
                    }
                    count++;
                }

            }

            /*alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login status");
            alertDialog.setMessage(id + name + email + phone);
            alertDialog.show();*/

            userType = "Admin";

            Intent intent = new Intent(context, AdminActivity.class);
            context.startActivity(intent);

        } else if (type.equals("user_login") && !result.equals("Wrong email or password")) {

            Toast.makeText(context, "Successfully login " + result, Toast.LENGTH_SHORT).show();
            /*Intent in = new Intent(context, BuyerActivity.class);
            context.startActivity(in);*/

            int count = 0;
            String space = " ";
            String[] words = result.split(space);
            for (String word : words) {
                if (word.trim().length() > 0) {
                    if (count == 0) {
                        id = word;
                    } else if (count == 1) {
                        name = word;
                    } else if (count == 2) {
                        email = word;
                    } else if (count == 3) {
                        phone = word;
                    }
                    count++;
                }

            }

            /*alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login status");
            alertDialog.setMessage(id + name + email + phone);
            alertDialog.show();*/

            userType = "User";


            Intent intent = new Intent(context, UserActivity.class);
            context.startActivity(intent);


        } else if (type.equals("user_register")) {

            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Register status");
            alertDialog.setMessage(result);
            alertDialog.show();

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        } else if (type.equals("book_list")) {

            productJsonString = result;
        }
        else if (type.equals("add_book")) {

            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setMessage(result);
            alertDialog.show();
        }

        else if (type.equals("delete_book")) {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}
