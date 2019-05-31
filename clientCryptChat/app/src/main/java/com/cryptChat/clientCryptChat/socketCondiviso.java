package com.cryptChat.clientCryptChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class socketCondiviso {
    private static Socket socket;
    private static DataInputStream dIS;
    private static DataOutputStream dOS;

    public static synchronized void setdIS(DataInputStream dIS2){
        dIS = dIS2;
    }


    public static synchronized DataInputStream getInputStream()
    {
        return dIS;
    }


    public static synchronized void setdOS(DataOutputStream dOS2){
       dOS = dOS2;
    }


    public static synchronized DataOutputStream getOutputStream()
    {
        return dOS;
    }


    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket pSocket){
        socket = pSocket;
    }
}