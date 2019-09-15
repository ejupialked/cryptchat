import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server implements Runnable {

    private ServerResponse serverResponse;
    private ClientHandler clientHandler;

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private KeyExchange keyExchange;

    public void openConnection(int port) throws IOException{
        this.port = port;

        keyExchange = new KeyExchange();
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
        clientHandler = new ClientHandler(socket, serverResponse);
        clientHandler.setKeyExchange(keyExchange);
        clientHandler.start();
    }

    @Override
    public void run() {

        try {
            openConnection(port);
            keyExchange.init();
            System.out.println(keyExchange.getPublicKeyEncoded());

            acceptConnection();
            serverResponse.notifyConnectionEstablished();


        } catch (IOException e) {
            e.printStackTrace();
        }

        handleClient();

    }

    private void acceptConnection() throws IOException {
        socket = serverSocket.accept();
    }


    public void
    sendMessage(String message){
        clientHandler.sendMessage(CryptChatUtils.ENCRYPTED_MESSAGE,
                CryptChatUtils.PROTOCOL_SEPARATOR,
                message);
    }


    /**
     *
     * @param message the message to decrypt
     * @return the message decrypted
     */
    public String decryptMessage(String message) {
        String decrypted = null;
        try {
            decrypted = decrypt(message, clientHandler.getKeyExchange().getAESKey());
            serverResponse.showDecryptedMessage(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }


    public String encryptMessage(String message) {
        String encrypted = null;
        try {
            encrypted = CryptChatUtils.encrypt(message, clientHandler.getKeyExchange().getAESKey());
            serverResponse.showEncryptedMessage(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encrypted;    }



    public void generateNewKey() {
        clientHandler.getKeyExchange().init();
        clientHandler.sendPublicKey(clientHandler.getKeyExchange().getPublicKeyEncoded());
        serverResponse.showPrivateKey(clientHandler.getKeyExchange().getPrivateKeyEncoded());
    }

    public  String decrypt(String encryptedMessage, SecretKeySpec aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,aesKey);


        System.out.println("im decrypting : " + Base64.encode(aesKey.getEncoded()));

        System.out.println("Key encoded :" + Base64.encode(aesKey.getEncoded()));
        byte[] decodedMessage = Base64.decode(encryptedMessage);
        byte[] recovered = cipher.doFinal(decodedMessage);

        return new String(recovered);
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
