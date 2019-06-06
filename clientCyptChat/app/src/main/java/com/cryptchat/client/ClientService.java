package com.cryptchat.client;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

public class ClientService extends AsyncTask<Object, String, String> {

    private WeakReference<ChatActivity> chatActivityWeakReference;


    ClientService(ChatActivity activity) {
        chatActivityWeakReference = new WeakReference<ChatActivity>(activity);
    }

    private Socket socket;


    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    boolean isConnected = false;

    String ip;
    int port;



    public void run() {
        try {

           // Toast.makeText(mainActivity,"connecting...", Toast.LENGTH_SHORT).show();

            socket = new Socket(ip, port);
            isConnected = true;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
          //  Toast.makeText(mainActivity,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        receiveMessage();
    }


    public void sendMessage(String message, String key) throws Exception{
        if(message.isEmpty()){
            throw new Exception("You must enter a message.");
        }

        try {
            objectOutputStream.flush();
            String data = key + "/#&#/" + message;
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void receiveMessage(){

        String keyReceived = null;
        String messageReceived = null;


        while(isConnected){


        }

    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(Object... objects) {


            String ip = (String) objects[0];
            Integer port = (Integer) objects[1];

            try {
                socket = new Socket(ip, port);
                isConnected = true;
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }


            while(isConnected){

                String messsage = null;
                try {
                    messsage = (String) objectInputStream.readObject();
                    publishProgress(messsage);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                int fineChiave = messsage.indexOf("/#&#/");
                int inizioChiave = 0;
                int inizioMessaggio = messsage.indexOf("/#&#/") + 5;
                int fineMessaggio = messsage.length();

                keyReceived = messsage.substring(inizioChiave, fineChiave);
                // chatActivity.setKeyReceived(keyReceived);
                messageReceived = messsage.substring(inizioMessaggio, fineMessaggio);
                //chatActivity.setMessageReceived(messageReceived);


            }

        return null;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        ChatActivity chatActivity = chatActivityWeakReference.get();
        if (chatActivity == null || chatActivity.isFinishing()) {
            return;
        }

        chatActivity.txtReceivedMsg.setText(values[0]);
    }
}