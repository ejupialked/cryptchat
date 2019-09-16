package com.cryptchat.client.ui.activity;

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

import com.cryptchat.client.ClientApplication;
import com.cryptchat.client.asynctask.ReceiveMessage;
import com.cryptchat.client.asynctask.SendMessage;
import com.cryptchat.client.ui.fragments.GenerateKeyFragment;
import com.cryptchat.client.ui.fragments.LoadingKeyFragment;
import com.cryptchat.client.R;


public class ChatResponseActivity extends AppCompatActivity implements
        ReceiveMessage.ReceiveMessageResponse,
        SendMessage.SendMessageResponse,
        ClientApplication.ChatResponse {

    ClientApplication clientApplication;

    TextView txt_message;
    TextView txt_key;
    TextView txt_encrypted;
    TextView txt_received;

    ImageButton btn_send;
    Button btn_decrypt;
    Button btn_encrypt;

    DialogFragment generatePublicKeyDialog;
    DialogFragment generateKeyPairDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        clientApplication = ((ClientApplication)getApplication());

        txt_message = findViewById(R.id.txt_message);
        txt_key= findViewById(R.id.txt_key);
        txt_encrypted = findViewById(R.id.txt_encrypted);

        txt_received = findViewById(R.id.txt_received);
        txt_received.setMovementMethod(new ScrollingMovementMethod());

        btn_encrypt = findViewById(R.id.btnEncrypt);
        btn_send = findViewById(R.id.btnSend);
        btn_decrypt = findViewById(R.id.btnDecrypt);

        generatePublicKeyDialog = LoadingKeyFragment.getInstance();
        generateKeyPairDialog = GenerateKeyFragment.getInstance();

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
        String m = txt_encrypted.getText().toString();
        try {
            clientApplication.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrypt(View v){
        String m = txt_received.getText().toString();
        try {
            String decrypted = clientApplication.decrypt(m);
            txt_received.setText(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encrypt(View v){
        String m = txt_message.getText().toString();
        try {
            String encrypted = clientApplication.encrypt(m);
            txt_encrypted.setText(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showPrivateKey(String s) {
        txt_key.setText(s);
    }

    @Override
    public void showMessageReceived(String s) {
        txt_received.setText(s);
    }

    @Override
    public void openDialogKeyPair() {
        generateKeyPairDialog.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogKeyPair() {
        generateKeyPairDialog.dismiss();
    }

    @Override
    public void openDialogPublicKey() {
        generatePublicKeyDialog.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogPublicKey() {
        generatePublicKeyDialog.dismiss();
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
        txt_received.setText(message);
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

}
