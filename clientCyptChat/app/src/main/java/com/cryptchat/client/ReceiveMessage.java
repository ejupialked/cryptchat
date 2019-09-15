package com.cryptchat.client;

import android.app.Application;
import android.os.AsyncTask;
import java.io.ObjectInputStream;

class ReceiveMessage extends AsyncTask<Void, String, String> {

    private ClientApplication clientApplication;
    private boolean isConnected;
    private ObjectInputStream ois;
    private ReceiveMessageResponse response;
    private KeyExchange keyExchange;



    ReceiveMessage(ClientApplication clientApplication){
        this.clientApplication = clientApplication;
    }

    @Override
    protected String doInBackground(Void... voids) {
        while (isConnected){
            String data = null;
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
        String message = "error";

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

    public void setKeyExchange(KeyExchange keyExchange) {
        this.keyExchange = keyExchange;
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