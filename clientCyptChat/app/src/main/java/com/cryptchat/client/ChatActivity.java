package com.cryptchat.client;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity implements ReceiveMessage.ReceiveMessageResponse, SendMessage.SendMessageResponse {


    ClientApplication clientApplication;

    TextView txtMsg;
    TextView txtKey;
    TextView txtEncryptedMsg;
    TextView txtReceivedMsg;


    ImageButton btnKey;
    ImageButton btnSend;
    Button btnDecrypt;
    Button btnEncrypt;

    private String lastEncryptedMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        clientApplication = ((ClientApplication)getApplication());

        txtMsg = findViewById(R.id.txtMsg);
        txtKey= findViewById(R.id.txtKey);
        txtEncryptedMsg = findViewById(R.id.txtEncryptedMsg);
        txtReceivedMsg = findViewById(R.id.txtReceivedMsg);

        btnKey = findViewById(R.id.btnKey);
        btnEncrypt = findViewById(R.id.btnEncrypt);
        btnSend = findViewById(R.id.btnSend);
        btnDecrypt= findViewById(R.id.btnDecrypt);

        clientApplication.receive(this);
    }



    public void send(View v){
        try {
            String message = txtMsg.getText().toString();
            String key = txtEncryptedMsg.getText().toString();
            clientApplication.sendMessage(message, key, this);
        } catch (Exception e) {
            showError(e.getMessage());
        }

    }

    public void generateKey(View v){
        txtKey.setText(CryptMessage.generateKey());
    }

    public void decrypt(View v){
        String messageDecrypted = null;

        try {
             messageDecrypted = CryptMessage.decrypt(lastEncryptedMessage);
             txtReceivedMsg.setText(messageDecrypted);

        }catch (Exception e){
            showError("Couldn't decrypt message");
        }

    }

    public void encrypt(View v){
        String message = txtReceivedMsg.getText().toString();

        String key = "error";

        try {
         key = validateKey(txtKey.getText().toString());
        }catch (Exception e){
            showError(e.getMessage());
        }

        String messageEncrypted = null;

        try {
            messageEncrypted = CryptMessage.encrypt(message,key);
            txtEncryptedMsg.setText(messageEncrypted);

        }catch (Exception e){
            showError("Couldn't encrypt message");
        }

    }

    @Override
    public void showMessageSentConfirmation(){
        showToast(this,"Message sent!");


    }

    @Override
    public void showErrorSendingMessage() {
        showError("Couldn't send the message");
    }


    private String validateKey(String key) throws Exception{
        if(key.length() != 16){
            throw new Exception("Key must be of 16 characters");
        }

        return key;
    }


    @Override
    public void notifyUserMessageReceived() {
        showToast(this, "You have just received a messsage!");
    }

    @Override
    public void showEncryptedMessageReceived(String message) {
        lastEncryptedMessage = message;
        txtReceivedMsg.setText(CryptMessage.getEncryptedMessage(message));
    }

    @Override
    public void showError(final String error) {
        showToast(this, error);
    }


    public void showToast(final Context context, final String message){
        new Thread()
        {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }.start();

    }

}
