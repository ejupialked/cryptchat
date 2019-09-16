package server;

import dh.KeyExchange;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static utils.CryptChatUtils.*;

public class Server implements Runnable {

    private ServerResponse serverResponse;
    private ClientHandler clientHandler;

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private KeyExchange keyExchange;

    public void openConnection(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        serverResponse.notifyConnectionOpen();
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public void handleClient(){
        initKeyExchange();
        clientHandler = new ClientHandler(socket, serverResponse);
        clientHandler.setKeyExchange(keyExchange);
        clientHandler.start();
    }

    public void generateNewKey() {
        initKeyExchange();
        clientHandler.requestNewKey();
    }



    @Override
    public void run() {

        try {
            openConnection(port);
            acceptConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverResponse.notifyConnectionEstablished();
        handleClient();

    }

    private void acceptConnection() throws IOException {
        this.socket = serverSocket.accept();
    }


    public void sendMessage(String message){
        clientHandler.sendMessage(ENCRYPTED_MESSAGE, PROTOCOL_SEPARATOR, message);
    }

    public void initKeyExchange(){
        this.keyExchange = new KeyExchange();
        keyExchange.init();

    }


    /**
     *
     * @param message the message to decrypt
     */
    public void decryptMessage(String message) {
        String decrypted;
        try {
            decrypted = decrypt(message, clientHandler.getKeyExchange().getAESKey());
            serverResponse.showDecryptedMessage(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void encryptMessage(String message) {
        String encrypted;
        try {
            encrypted = encrypt(message, clientHandler.getKeyExchange().getAESKey());
            serverResponse.showEncryptedMessage(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public interface ServerResponse{
        void notifyConnectionOpen();
        void notifyMessageReceived();
        void showEncryptedMessage(String s);
        void showDecryptedMessage(String s);
        void showMessageReceived(String s);
        void showPrivateKey(String s);
        void showErrorMessage(String s);
        void notifyConnectionEstablished();
    }
}
