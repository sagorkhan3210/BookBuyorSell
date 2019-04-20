package com.example.oldbookbuyorsell;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText userId, userPass;
    private Button loginBtn;
    private TextView registerTv;

    private RadioButton adminBtn, userBtn;

    private String id, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = findViewById(R.id.loginUserIdEt);
        userPass = findViewById(R.id.loginUserPassEt);
        loginBtn = findViewById(R.id.loginBtn);
        registerTv = findViewById(R.id.loginRegisterTv);

        adminBtn = findViewById(R.id.loginAsAdminRd);
        userBtn = findViewById(R.id.loginAsUserRd);

        userBtn.setChecked(true);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adminBtn.isChecked()){

                    //Toast.makeText(MainActivity.this, "Admin Select", Toast.LENGTH_SHORT).show();
                    id = userId.getText().toString();
                    pass = userPass.getText().toString();

                    String type = "admin_login";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(MainActivity.this);
                    backgroundWorker.execute(type, id, pass);

                }
                else if (userBtn.isChecked()){
                    id = userId.getText().toString();
                    pass = userPass.getText().toString();

                    String type = "user_login";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(MainActivity.this);
                    backgroundWorker.execute(type, id, pass);

                }


            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
