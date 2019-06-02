import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable{

    private String ipHost;
    private ServerUI serverUI;
    private int port;

    private ServerSocket serverSocket;
    private Socket socket;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private boolean isConnected;

    Server(){

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            this.ipHost = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void setPort(int port) {
        this.port = port;
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
            serverUI.getTxtStatus().setText("Server connected");
            serverUI.getTxtStatus().setForeground(Color.BLACK);
            serverUI.getTxtStatus().setBackground(Color.green);

            serverUI.showCustomMessage(serverUI,
                    "Connection established with Android device",
                    "Notification", -1,
                    "src/main/resources/androidDialogo.png"
                    );


            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

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
                messsage = (String) objectInputStream.readObject();

                int fineChiave = messsage.indexOf("/#&#/");
                int inizioChiave = 0;
                int inizioMessaggio = messsage.indexOf("/#&#/") + 5;
                int fineMessaggio = messsage.length();

                keyReceived = messsage.substring(inizioChiave, fineChiave);
                messageReceived = messsage.substring(inizioMessaggio, fineMessaggio);
                serverUI.getTxtReceivedMessage().setText(messageReceived);

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

    public void setUI(ServerUI serverUI) {
        this.serverUI = serverUI;
    }
}
