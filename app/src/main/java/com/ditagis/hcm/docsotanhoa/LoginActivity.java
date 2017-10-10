package com.ditagis.hcm.docsotanhoa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ditagis.hcm.docsotanhoa.localdb.LocalDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LayLoTrinhActivity.class);
                if (isOnline()) {
                    if (checkInfo())
                        startActivity(intent);
                } else {
                    LocalDatabase databaseHelper = new LocalDatabase(LoginActivity.this);
                   HashMap<String, Integer> mltArr = databaseHelper.getAllMLT();

                    Intent intent1 = new Intent(LoginActivity.this, XemLoTrinhDaTai.class);
                    intent1.putExtra("data", mltArr);
                    startActivity(intent1);
                }
            }
        });
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    private boolean checkInfo() {

        String username = ((EditText) findViewById(R.id.txtUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();

        if (username.equals("") && password.equals("")) {
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
