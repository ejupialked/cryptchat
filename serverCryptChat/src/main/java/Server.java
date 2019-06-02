import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable{

    private ServerUI serverUI;

    private String ipHost;
    private int port;

    private ServerSocket serverSocket;
    private Socket socket;

    private boolean isConnected;

    Server(){

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            this.ipHost = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


    public void openConnection(int port){
        this.port = port;



    }


    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            isConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(isConnected){


        }

    }
}
