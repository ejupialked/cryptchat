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

    public ServerUI(Server server) {
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
            error(null, ex.getMessage());
            txt_port.setText("");
        }
    }

    private void encrypt(ActionEvent e) {
        String message = txt_message.getText();
        server.encryptMessage(message);
        notification("src/main/resources/lock_dialog.png","The message has been ecrypted!");
    }

    private void newKey(ActionEvent e) {
        server.generateNewKey();
        notification("src/main/resources/key_dialog.png", "Server is exchanging the keys...");
    }

    private void send(ActionEvent e) {
        String message = txt_messageEncrypted.getText();
        server.sendMessage(message);
        notification("src/main/resources/lock_dialog.png", "Your message has been sent");

    }

    private void decrypt(ActionEvent e) {
        String encrypted = txt_messageReceived.getText();
        server.decryptMessage(encrypted);
        notification("src/main/resources/unlock_dialog.png", "The message has been decrypted!");
    }

    private void clear(ActionEvent e) {
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
        notification("src/main/resources/android_logo.png", "You have just received a message" );
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
        error("Error", s);
    }

    @Override
    public void notifyConnectionEstablished() {
        txt_status.setText("server.Server connected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.green);
        notification("src/main/resources/android_logo.png", "Server is now connected with the client");
    }
}
