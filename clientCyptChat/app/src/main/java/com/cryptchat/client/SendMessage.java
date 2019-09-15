package com.cryptchat.client;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectOutputStream;

class SendMessage extends AsyncTask<String, String, Void>  {

    private ObjectOutputStream oos;
    private SendMessageResponse response;

    @Override
    protected Void doInBackground(String... data) {
        String message = data[0];
        try {
            oos.writeUTF(message);
            oos.flush();
            publishProgress(message);
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
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        System.err.println("Sent " + values[0]);
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