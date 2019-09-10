package com.cryptchat.client;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientApplication extends Application {

    private boolean isConnected;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;



    private ReceiveMessage receive;
    private SendMessage send;

    private ClientConnectionResponse clientConnectionResponse;


    ClientApplication(){
        receive = new ReceiveMessage();
        send = new SendMessage();

    }


    public void receive(ReceiveMessage.ReceiveMessageResponse response){
        receive.setResponse(response);
        receive.setIsConnected(isConnected());
        receive.setOis(ois);
        receive.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }




    public void sendMessage(String message, String key, SendMessage.SendMessageResponse response) throws Exception{

        if(message.isEmpty()){
            throw new Exception("You must enter a message.");
        }

        String data = key + "/#&#/" + message;

        send.setResponse(response);
        send.setOos(oos);
        send.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data);

    }

    public boolean isConnected() {
        return isConnected;
    }

    public void connect(String ip, String port){
        Connect connect = new Connect();
        connect.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ip, port);
    }


    public void setClientConnectionResponse(ClientConnectionResponse clientConnectionResponse) {
        this.clientConnectionResponse = clientConnectionResponse;
    }

    @SuppressLint("StaticFieldLeak")
    private class Connect extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... networkData) {

            String ip = networkData[0];
            int port = Integer.parseInt(networkData[1]);

            try {
                socket = new Socket(ip, port);
                isConnected = true;
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                clientConnectionResponse.showErrorMessage(e.getMessage());
                isConnected= false;
                return false;
            }

            return true;
        }


        @Override
        protected void onPostExecute(Boolean connected) {
            super.onPostExecute(connected);
            if(connected){
                clientConnectionResponse.openChat();
            }
        }
    }



    public interface ClientConnectionResponse {
        void openChat();
        void showErrorMessage(String message);
    }
}