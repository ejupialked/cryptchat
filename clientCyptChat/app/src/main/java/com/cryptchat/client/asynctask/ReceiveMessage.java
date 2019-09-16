package com.cryptchat.client.asynctask;

import android.os.AsyncTask;

import com.cryptchat.client.utils.Base64;
import com.cryptchat.client.ClientApplication;
import com.cryptchat.client.utils.CryptChatUtils;

import java.io.ObjectInputStream;

public class ReceiveMessage extends AsyncTask<Void, String, String> {

    private ClientApplication clientApplication;
    private boolean isConnected;
    private ObjectInputStream ois;
    private ReceiveMessageResponse response;

    public ReceiveMessage(ClientApplication clientApplication){
        this.clientApplication = clientApplication;
    }

    @Override
    protected String doInBackground(Void... voids) {
        while (isConnected){
            String data;
            CryptChatUtils.delay(100);

            data = readMessage();

            if(data != null){
                int command = CryptChatUtils.retrieveCommand(data);
                String message = CryptChatUtils.retrieveMessage(data);
                performOperation(command, message);
            }
        }

        return null;
    }

    private void performOperation(int c, String message) {
        if(c == CryptChatUtils.REQUEST_PUBLIC_KEY){
            byte[] decodedPublicKey = Base64.decode(message);
            clientApplication.executeKeyExchange(decodedPublicKey);
        }else if(c == CryptChatUtils.ENCRYPTED_MESSAGE){
            publishProgress(message);
        }else if(c == CryptChatUtils.REQUEST_NEW_KEY){
            byte[] decodedPublicKey = Base64.decode(message);
            clientApplication.executeKeyExchange(decodedPublicKey);
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        String message = values[0];
        response.notifyUserMessageReceived();
        response.showEncryptedMessageReceived(message);
    }

    private String readMessage(){
        String message = null;

        try {
            message = ois.readUTF();
            System.err.println(message);
        }catch (Exception e){
            response.showError(e.getLocalizedMessage());
        }

        return message;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void setResponse(ReceiveMessageResponse response) {
        this.response = response;
    }

    public interface ReceiveMessageResponse {
        void notifyUserMessageReceived();
        void showEncryptedMessageReceived(String message);
        void showError(String error);
    }
}