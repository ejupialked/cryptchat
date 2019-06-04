package com.cryptchat.client;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection implements Runnable, Parcelable {

    private Socket socket;
    private ChatActivity chatActivity;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    boolean isConnected;




    ClientConnection(String ip, int port) throws IOException{
        socket = new Socket(ip, port);
        isConnected = true;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    protected ClientConnection(Parcel in) {
    }

    public static final Creator<ClientConnection> CREATOR = new Creator<ClientConnection>() {
        @Override
        public ClientConnection createFromParcel(Parcel in) {
            return new ClientConnection(in);
        }

        @Override
        public ClientConnection[] newArray(int size) {
            return new ClientConnection[size];
        }
    };

    public void setChatActivity(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
    }

    @Override
    public void run() {
        receiveMessage();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(socket);
        dest.writeValue(objectInputStream);
        dest.writeValue(objectOutputStream);
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

            String messsage = null;

            try {
                messsage = (String) objectInputStream.readObject();

                int fineChiave = messsage.indexOf("/#&#/");
                int inizioChiave = 0;
                int inizioMessaggio = messsage.indexOf("/#&#/") + 5;
                int fineMessaggio = messsage.length();

                keyReceived = messsage.substring(inizioChiave, fineChiave);
                chatActivity.setKeyReceived(keyReceived);
                messageReceived = messsage.substring(inizioMessaggio, fineMessaggio);
                chatActivity.setKeyReceived(messageReceived);

            }catch (Exception e){

            }
        }

    }



    public String decryptMessage(String message, String key) {

        try {
            return CryptMessage.decrypt(message, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "not possible";

    }


    public String encryptMessage(String message, String key) throws Exception{

        if(key.length() != 16 && key.length() != 0){
            throw new Exception("The key must be 16 characters.");
        }else if(key.length() == 0){
            throw new Exception("Key not inserted");
        }

        return CryptMessage.encrypt(message, key);

    }

}