package com.cryptchat.client;


import android.os.AsyncTask;
import java.io.ObjectInputStream;

class ReceiveMessage extends AsyncTask<Void, String, String> {

    private boolean isConnected;
    private ObjectInputStream ois;
    private ReceiveMessageResponse receiveMessageResponse;

    ReceiveMessage(ReceiveMessageResponse response){
        this.receiveMessageResponse = response;
    }

    @Override
    protected String doInBackground(Void... voids) {

        while (isConnected){
            delay(100);
            String message = readMessage();
            publishProgress(message);
        }

        return null;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        String message = values[0];

        receiveMessageResponse.notifyUserMessageReceived();
        receiveMessageResponse.showMessageReceived(message);
    }

    public String readMessage(){
        String message = "error";

        try {
            message = ois.readUTF();
            message = CryptMessage.decrypt(message);
        }catch (Exception e){
            receiveMessageResponse.showError(e.getMessage());
        }

        return message;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }


    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }


    public void delay(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            receiveMessageResponse.showError("System error");
        }
    }

    public interface ReceiveMessageResponse {
        void notifyUserMessageReceived();
        void showMessageReceived(String message);
        void showError(String error);
    }
}