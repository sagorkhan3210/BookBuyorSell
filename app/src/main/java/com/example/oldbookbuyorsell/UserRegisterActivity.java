package com.example.oldbookbuyorsell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserRegisterActivity extends AppCompatActivity {

    private EditText regIdEt, regNameEt, regEmailEt, regPhoneEt, regPasswordEt;
    private Button buttonRegister;
    private TextView textViewLogin;

    private String id, name, email, phone, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        regIdEt = (EditText) findViewById(R.id.regIdEt);
        regNameEt = (EditText) findViewById(R.id.regNameEt);
        regEmailEt = (EditText) findViewById(R.id.regEmailEt);
        regPhoneEt = (EditText) findViewById(R.id.regPhoneEt);
        regPasswordEt = (EditText) findViewById(R.id.regPasswordEt);
        buttonRegister = (Button) findViewById(R.id.regRegisterBtn);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        id = regIdEt.getText().toString();
        name = regNameEt.getText().toString();
        email = regEmailEt.getText().toString();
        phone = regPhoneEt.getText().toString();
        pass = regPasswordEt.getText().toString();

        String type = "user_register";

        finish();

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, id, name, email, phone, pass);
    }
}
