package com.cryptchat.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ClientApplication.ClientConnectionResponse {
    Button btnConnect;
    TextView txtIP;
    TextView txtPort;

    String port;
    String ip;

    ClientApplication clientApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientApplication = ((ClientApplication)getApplication());
        clientApplication.setClientConnectionResponse(this);


        btnConnect = findViewById(R.id.btnConnect);
        txtIP = findViewById(R.id.txt_ip);
        txtPort = findViewById(R.id.txtPort);


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = txtIP.getText().toString();
                port = txtPort.getText().toString();

                ((ClientApplication)getApplication()).connect(ip, port);
            }
        });

    }

    @Override
    public void openChat() {
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(final String message) {
        new Thread()
        {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getBaseContext(),message, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }.start();
    }
}
