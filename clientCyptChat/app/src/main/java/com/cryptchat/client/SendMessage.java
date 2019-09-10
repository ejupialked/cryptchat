package com.cryptchat.client;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectOutputStream;

class SendMessage extends AsyncTask<String, String, Void> {

    private ObjectOutputStream oos;
    private SendMessageResponse response;


    public SendMessage() {

    }

    @Override
    protected Void doInBackground(String... data) {
        String message = data[0];

        try {
            oos.writeUTF(message);
            oos.reset();
        } catch (IOException e) {
            response.showErrorSendingMessage();
            e.printStackTrace();
        }

        return null;
    }


    public void setResponse(SendMessageResponse response) {
        this.response = response;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        response.showMessageSentConfirmation();

    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }


    public interface SendMessageResponse{
        void showMessageSentConfirmation();
        void showErrorSendingMessage();
    }

}