import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable{

    private InetAddress ipHost;
    private ServerUI serverUI;
    private int port;


    private String ip = "172.16.5.124" +
            "";

    private ServerSocket serverSocket;
    private Socket socket;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private boolean isConnected;

    Server(){

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            this.ipHost = localhost;
            System.err.println(ipHost);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public InetAddress getIpHost() {
        return ipHost;
    }

    public void setPort (String port) throws Exception {
        int intPort;

        try {
            intPort =Integer.parseInt(port);
        }catch (Exception e){
            throw new Exception("Port not valid");
        }

        this.port = intPort;



    }


    public void openConnection(int port){
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);

            serverUI.getTxtStatus().setText("Listening for any devices...");
            serverUI.getTxtStatus().setForeground(Color.WHITE);
            serverUI.getTxtStatus().setBackground(Color.decode("#003366"));

        } catch (IOException e) {
            serverUI.showCustomMessage(serverUI, "Invalid port number", "Error", 0, null);
            Thread.currentThread().interrupt();
    }

        try {
            socket = serverSocket.accept();
            serverUI.showConnectionEstablished(); //UI

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            serverUI.showCustomMessage(serverUI, "Invalid port number", "Error", 0, null);
            Thread.currentThread().interrupt();
        }

        isConnected = true;
    }


    @Override
    public void run() {

        String keyReceived = null;
        String messageReceived = null;

        openConnection(port);

        while(isConnected){

            String messsage = null;

            try {
                messsage = (String) objectInputStream.readUTF();



                int fineChiave = messsage.indexOf("/#&#/");
                int inizioChiave = 0;
                int inizioMessaggio = messsage.indexOf("/#&#/") + 5;
                int fineMessaggio = messsage.length();

                keyReceived = messsage.substring(inizioChiave, fineChiave);
                serverUI.setKeyReceived(keyReceived);
                messageReceived = messsage.substring(inizioMessaggio, fineMessaggio);

                serverUI.setMessageReceived(messsage);

                new Thread(() ->{
                    serverUI.showCustomMessage(serverUI, "You have just received a message",
                            "Notification",
                            -1,
                            "src/main/resources/androidDialogo.png" );
                }).start();

            }catch (Exception e){

            }
        }

    }



    public void sendMessage(String message, String key) throws Exception{

        if(message.isEmpty()){
            throw new Exception("You must enter a message.");
        }
        try {

            String data = key + "/#&#/" + message;
            objectOutputStream.writeUTF(data);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void setUI(ServerUI serverUI) {
        this.serverUI = serverUI;
    }
}
