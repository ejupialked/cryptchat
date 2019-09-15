package com.cryptchat.client;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity
        implements ReceiveMessage.ReceiveMessageResponse, SendMessage.SendMessageResponse, ClientApplication.ClientChat {


    ClientApplication clientApplication;

    TextView txtMsg;
    TextView txtKey;
    TextView txtEncryptedMsg;
    TextView txtReceivedMsg;

    ImageButton btnSend;
    Button btnDecrypt;
    Button btnEncrypt;



    DialogFragment pbk;
    DialogFragment keypair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        clientApplication = ((ClientApplication)getApplication());

        txtMsg = findViewById(R.id.txtMsg);
        txtKey= findViewById(R.id.txtKey);
        txtEncryptedMsg = findViewById(R.id.txtEncryptedMsg);
        txtReceivedMsg = findViewById(R.id.txtReceivedMsg);
        txtReceivedMsg.setMovementMethod(new ScrollingMovementMethod());

        btnEncrypt = findViewById(R.id.btnEncrypt);
        btnSend = findViewById(R.id.btnSend);
        btnDecrypt= findViewById(R.id.btnDecrypt);



        pbk = LoadingKeyFragment.getInstance();
        keypair = GenerateKeyFragment.getInstance();

        clientApplication.setReceiveMessageResponse(this);
        clientApplication.setSendMessageResponse(this);
        clientApplication.setChatClient(this);
        clientApplication.executeReceive();
        try {
            clientApplication.ready();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void send(View v){
        String m = txtEncryptedMsg.getText().toString();

        try {
            clientApplication.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrypt(View v){
        String m = txtReceivedMsg.getText().toString();
        try {
            String decrypted = clientApplication.decrypt(m);
            txtReceivedMsg.setText(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void encrypt(View v){
        String m = txtMsg.getText().toString();
        try {
            String encrypted = clientApplication.encrypt(m);
            txtEncryptedMsg.setText(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showPrivateKey(String s) {
        txtKey.setText(s);
    }

    @Override
    public void showMessageReceived(String s) {
        txtReceivedMsg.setText(s);
    }

    @Override
    public void openDialogKeyPair() {
        keypair.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogKeyPair() {
        keypair.dismiss();
    }

    @Override
    public void openDialogPublicKey() {
        pbk.show(getSupportFragmentManager(), "Loading key");

    }

    @Override
    public void closeDialogPublicKey() {
        pbk.dismiss();
    }

    @Override
    public void showMessageSentConfirmation(){
        showToast(this,"Message sent!");
    }

    @Override
    public void showErrorSendingMessage() {
        showError("Couldn't send the message");
    }

    @Override
    public void notifyUserMessageReceived() {
        showToast(this, "You have just received a messsage!");
    }

    @Override
    public void showEncryptedMessageReceived(String message) {
        txtReceivedMsg.setText(message);
    }

    @Override
    public void showError(final String error) {
        showToast(this, error);
    }


    public void showToast(final Context context, final String message){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }.start();
    }


    public ClientApplication getClientApplication() {
        return clientApplication;
    }



}
