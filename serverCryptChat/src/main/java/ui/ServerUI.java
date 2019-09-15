package ui;

import server.Server;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

/**
 * @author Alked Ejupi
 */
public class ServerUI extends ServerFrame implements Server.ServerResponse{

    private Server server;

    ServerUI(Server server) {
        super();
        initListeners();
        this.server = server;
        server.setServerResponse(this);

    }


    private void initListeners() {

        btn_connect.addActionListener(this::connect);
        btn_encrypt.addActionListener(this::encrypt);
        btn_decrypt.addActionListener(this::decrypt);
        btn_send.addActionListener(this::send);
        btnCancel.addActionListener(this::clear);
        btnKey.addActionListener(this::newKey);
    }

    private void connect(ActionEvent e) {
        try {

            server.setPort(Integer.parseInt(txt_port.getText()));
            new Thread(server).start();
        } catch (Exception ex) {
            showCustomMessage(this, ex.getMessage(), "Error", 0, null);
            txt_port.setText("");
        }
    }



    private void encrypt(ActionEvent e) {
        String message = txt_message.getText();
        server.encryptMessage(message);

        showCustomMessage(this,
                "The message has been ecrypted!",
                "Notification", -1,
                "src/main/resources/lock_dialog.png" );

    }

    private void newKey(ActionEvent e) {


        server.generateNewKey();

        showCustomMessage(this,
                "Key generated!",
                "Notification" ,
                -1,
                "src/main/resources/key_dialog.png");
    }



    public void showCustomMessage(JFrame parent, String text, String title, int messageType, String pathImg){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ImageIcon icon = new ImageIcon(pathImg);

        new Thread(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(parent,label,title,messageType,icon);
            }
        }).start();

    }

    private void send(ActionEvent e) {
        String message = txt_messageEncrypted.getText();
        server.sendMessage(message);

        showCustomMessage(this,
                "Your message has been sent",
                "Notification", -1,
                "src/main/resources/lock_dialog.png");

    }




    private void decrypt(ActionEvent e) {

            String encrypted = txt_messageReceived.getText();
            server.decryptMessage(encrypted);


            showCustomMessage(this, "Messaggio Decriptato!", "Notification",
                    -1, "src/main/resources/unlock_dialog.png" );

    }


    private void clear(ActionEvent evt) {
        txt_message.setText("");
    }



    @Override
    public void showEncryptedMessage(String s) {
        txt_messageEncrypted.setText(s);

    }

    @Override
    public void showDecryptedMessage(String s) {
        txt_messageReceived.setText(s);
    }

    @Override
    public void notifyConnectionOpen() {
        getTxt_status().setText("Waiting the client...");
        getTxt_status().setForeground(Color.WHITE);
        getTxt_status().setBackground(Color.decode("#003366"));

    }

    @Override
    public void notifyMessageReceived() {
        new Thread(() ->{
            showCustomMessage(this, "You have just received a message",
                    "Notification",
                    -1,
                    "src/main/resources/android_logo.png" );
        }).start();
    }

    @Override
    public void showMessageReceived(String s) {
        txt_messageReceived.setText(s);
    }


    @Override
    public void showPrivateKey(String s) {
        txt_key.setText(s);
    }

    @Override
    public void showErrorMessage(String s) {
        showCustomMessage(this, s, "Error", Dialog.ERROR, null);

    }

    @Override
    public void notifyConnectionEstablished() {

        txt_status.setText("server.Server connected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.green);

        showCustomMessage(this,
                "server.Server",
                "Notification", -1,
                "src/main/resources/android_logo.png"
        );
    }




}
