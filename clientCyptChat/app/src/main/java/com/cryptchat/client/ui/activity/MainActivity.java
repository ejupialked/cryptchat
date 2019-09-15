package com.cryptchat.client.ui.activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cryptchat.client.ClientApplication;
import com.cryptchat.client.ui.fragments.ConnectionFragment;
import com.cryptchat.client.R;

public class MainActivity extends AppCompatActivity implements ClientApplication.ClientConnectionResponse {

    Button btnConnect;
    TextView txtIP;
    TextView txtPort;

    String port;
    String ip;

    DialogFragment loadConnection;

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

        loadConnection = ConnectionFragment.getInstance();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = txtIP.getText().toString();
                port = txtPort.getText().toString();

                try {
                    clientApplication.connect(ip, port);
                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorMessage("Couldn't connect, check ip and port");
                }
            }
        });
    }

    @Override
    public void openChat() {
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void openDialogConnection() {
        loadConnection.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogConnection() {
        loadConnection.dismiss();
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
