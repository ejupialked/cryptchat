package com.cryptchat.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ChatActivity extends AppCompatActivity {

    TextView txtMsg;
    TextView txtKey;
    TextView txtEncryptedMsg;
    TextView txtReceivedMsg;

    ImageButton btnKey;
    ImageButton btnSend;
    Button btnDecrypt;
    Button btnEncrypt;


    String messageReceived;
    String keyReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        txtMsg = findViewById(R.id.txtMsg);
        txtKey= findViewById(R.id.txtKey);
        txtEncryptedMsg = findViewById(R.id.txtEncryptedMsg);
        txtReceivedMsg = findViewById(R.id.txtReceivedMsg);

        btnKey = findViewById(R.id.btnKey);
        btnEncrypt = findViewById(R.id.btnEncrypt);
        btnSend = findViewById(R.id.btnSend);
        btnDecrypt= findViewById(R.id.btnDecrypt);


        Intent intent = getIntent();
        final ClientConnection clientConnection = intent.getParcelableExtra("clientConnection");
        clientConnection.setChatActivity(this);


        btnKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtKey.setText(CryptMessage.generateKey());
            }
        });


        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = txtMsg.getText().toString();
                String key =   txtKey.getText().toString();
                try {
                    txtEncryptedMsg.setText(CryptMessage.encrypt(message, key));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtReceivedMsg.setText(
                        clientConnection.decryptMessage(messageReceived, keyReceived));
                Toast.makeText(getApplicationContext(), "Message decrypted!", Toast.LENGTH_SHORT).show();

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String message = txtEncryptedMsg.getText().toString();
                    String key = txtKey.getText().toString();

                    clientConnection.sendMessage(message, key);
                    Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public TextView getTxtReceivedMsg() {
        return txtReceivedMsg;
    }

    public void setKeyReceived(String keyReceived) {
        this.keyReceived = keyReceived;
    }

    public void setMessageReceived(String messageReceived) {
        this.messageReceived = messageReceived;
        this.txtReceivedMsg.setText(messageReceived);
        Toast.makeText(this, "You have just received a message!",
                        Toast.LENGTH_SHORT).show();

    }
}
