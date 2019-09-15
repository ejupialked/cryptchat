package com.cryptchat.client;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;

import static com.cryptchat.client.CryptChatUtils.PROTOCOL_SEPARATOR;
import static com.cryptchat.client.CryptChatUtils.PUBLIC_KEY;

public class ClientApplication extends Application implements KeyExchange.SendKey {

    private boolean isConnected;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private KeyExchange keyExchange;

    private ReceiveMessage receive;
    private SendMessage send;


    private ClientApplication.ClientChat chatClient;

    private ClientConnectionResponse clientConnectionResponse;
    private ReceiveMessage.ReceiveMessageResponse receiveMessageResponse;
    private SendMessage.SendMessageResponse sendMessageResponse;


    ClientApplication(){
        receive = new ReceiveMessage();
        keyExchange = new KeyExchange(this);
    }



    public void executeReceive(){
        receive.setKeyExchange(keyExchange);
        receive.setResponse(receiveMessageResponse);
        receive.setIsConnected(isConnected());
        receive.setOis(ois);
        receive.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    public void executeSend(String message) throws Exception{

        if(message.isEmpty()){
            throw new Exception("You must enter a message.");
        }

        send = new SendMessage();
        send.setResponse(sendMessageResponse);
        send.setOos(oos);
        send.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message);

    }


    public String decrypt(String message) throws Exception {
        return CryptChatUtils.decrypt(message, keyExchange.getAESKey());
    }

    public String encrypt(String message) throws Exception {
        return CryptChatUtils.encrypt(message, keyExchange.getAESKey());
    }


    public void sendMessage(String message) throws Exception {
        executeSend(CryptChatUtils.ENCRYPTED_MESSAGE + CryptChatUtils.PROTOCOL_SEPARATOR + message);
    }

    public void ready() throws Exception {
        executeSend(CryptChatUtils.READY + CryptChatUtils.PROTOCOL_SEPARATOR + "ready");
    }


    public void connect(String ip, String port){
        Connect connect = new Connect();
        connect.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ip, port);
    }


    @Override
    public void sendPublicKey(PublicKey publicKey) {

        byte[] encodedPublicKey = publicKey.getEncoded();

        String base64PublicKey = Base64.encode(encodedPublicKey);

        String formatPBK = PUBLIC_KEY + PROTOCOL_SEPARATOR + base64PublicKey;

        try {
            executeSend(formatPBK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendError(String error) {

    }

    @Override
    public void showPrivateKey(String s) {
        chatClient.showPrivateKey(s);
    }

    @Override
    public void notifyCretingKeyPair() {
        clientConnectionResponse.showErrorMessage("Creating key pair");
    }


    private void initStreams(Socket server) {
        try {
            oos = new ObjectOutputStream(server.getOutputStream());
            ois = new ObjectInputStream(server.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeConnection(){
        try {
            oos.close();
            ois.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @SuppressLint("StaticFieldLeak")
    private class Connect extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... networkData) {
            String ip = networkData[0];
            int port = Integer.parseInt(networkData[1]);

            try {
                System.err.println("connecting");
                socket = new Socket(ip, port);
                isConnected = true;
                initStreams(socket);
            } catch (IOException e) {
                e.printStackTrace();
                clientConnectionResponse.showErrorMessage(e.getMessage());
                isConnected = false;
                closeConnection();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean connected) {
            super.onPostExecute(connected);
            if (connected) {
                clientConnectionResponse.openChat();
            }
        }

    }


    public boolean isConnected() {
        return isConnected;
    }


    public void setClientConnectionResponse(ClientConnectionResponse clientConnectionResponse) {
        this.clientConnectionResponse = clientConnectionResponse;
    }

    public void setChatClient(ClientApplication.ClientChat chatClient) {
        this.chatClient = chatClient;
    }

    public void setReceiveMessageResponse(ReceiveMessage.ReceiveMessageResponse receiveMessageResponse) {
        this.receiveMessageResponse = receiveMessageResponse;
    }

    public void setSendMessageResponse(SendMessage.SendMessageResponse sendMessageResponse) {
        this.sendMessageResponse = sendMessageResponse;
    }


    public interface ClientChat{
        void showPrivateKey(String s);
        void showMessageReceived(String s);
    }

        public interface ClientConnectionResponse {
        void openChat();
        void showErrorMessage(String message);
    }
}