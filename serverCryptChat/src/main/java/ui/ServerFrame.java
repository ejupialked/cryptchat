package ui;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {


    public ServerFrame(){
        this.setVisible(true);
        initComponents();

        putIcon();

        txt_port.setText("5000");
        txt_message.setText("test");

        txt_status.setText("server.Server Disconnected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.red);
    }


    private void putIcon() {
        ImageIcon icon = new ImageIcon("src/main/resources/lock_icon.png");
        setIconImage(icon.getImage());
    }

    protected void initComponents() {
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jSeparator1 = new JSeparator();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        btn_connect = new JButton();
        txt_port = new JTextField();
        txt_status = new JTextField();
        jSeparator3 = new JSeparator();
        jButton2 = new JButton();
        jLabel8 = new JLabel();
        jSeparator4 = new JSeparator();
        jPanel3 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        btn_decrypt = new JButton();
        jLabel4 = new JLabel();
        jLabel7 = new JLabel();
        jSeparator6 = new JSeparator();
        jSeparator5 = new JSeparator();
        txt_messageReceived = new JLabel();
        jSeparator7 = new JSeparator();
        txt_messageEncrypted = new JLabel();
        txt_message = new JTextField();
        jPanel1 = new JPanel();
        btn_encrypt = new JButton();
        btn_send = new JButton();
        btnCancel = new  JButton();
        btnKey = new JButton();
        txt_key = new JTextField();
        jLabel6 = new JLabel();
        jSeparator2 = new JSeparator();
        jLabel10 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CryptChat by Ejupi Alked");
        setBackground(new Color(255, 0, 0));
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new AbsoluteLayout());

        jPanel2.setBackground(new Color(0, 51, 102));
        jPanel2.setLayout(new AbsoluteLayout());

        jLabel1.setIcon(new ImageIcon("src/main/resources/locker_background.png"));
        jPanel2.add(jLabel1, new AbsoluteConstraints(0, 80, 190, 140));

        jLabel2.setFont(new Font("Century Gothic", 1, 14));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("PORT");
        jPanel2.add(jLabel2, new AbsoluteConstraints(20, 230, 50, 20));


        //port
        jSeparator1.setFont(new Font("sansserif", 1, 12));
        jPanel2.add(jSeparator1, new AbsoluteConstraints(80, 250, 70, 10));

        jLabel3.setIcon(new ImageIcon("src/main/resources/cryptchat_logo.png"));
        jPanel2.add(jLabel3, new AbsoluteConstraints(20, 20, 160, 40));

        jLabel5.setFont(new Font("Century Gothic", 2, 12));
        jLabel5.setForeground(new Color(255, 255, 255));
        jLabel5.setText("© 2017 All Rights Reserved");
        jPanel2.add(jLabel5, new AbsoluteConstraints(10, 420, 170, 20));

        btn_connect.setBackground(new Color(45, 92, 107));
        btn_connect.setFont(new Font("Century Gothic", 1, 14));
        btn_connect.setForeground(new Color(255, 255, 255));
        btn_connect.setIcon(new ImageIcon("src/main/resources/server_button.png"));
        btn_connect.setText("Run server.Server");
        btn_connect.setToolTipText("");
        btn_connect.setActionCommand("btn_connect");
        btn_connect.setBorder(null);
        btn_connect.setBorderPainted(false);


        jPanel2.add(btn_connect, new AbsoluteConstraints(10, 310, 170, 40));

        txt_port.setBackground(new Color(0, 51, 102));
        txt_port.setFont(new Font("Century Gothic", 1, 16));
        txt_port.setForeground(new Color(255, 255, 255));
        txt_port.setHorizontalAlignment(JTextField.CENTER);
        txt_port.setBorder(null);;
        jPanel2.add(txt_port, new AbsoluteConstraints(90, 230, 50, -1));

        txt_status.setBackground(new Color(0, 51, 102));
        txt_status.setFont(new Font("Century Gothic", 1, 14));
        txt_status.setHorizontalAlignment(JTextField.CENTER);
        txt_status.setBorder(null);
        jPanel2.add(txt_status, new AbsoluteConstraints(0, 370, 190, 30));

        //server status
        jSeparator3.setForeground(new Color(255, 255, 255));
        jSeparator3.setFont(new Font("sansserif", 1, 12));
        jPanel2.add(jSeparator3, new AbsoluteConstraints(0, 400, 190, 20));

        getContentPane().add(jPanel2, new AbsoluteConstraints(0, 0, 190, 470));

        jLabel8.setFont(new Font("Century Gothic", 1, 16));
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel8.setText("Message");
        getContentPane().add(jLabel8, new AbsoluteConstraints(230, 130, -1, -1));


        //message
        jSeparator4.setForeground(new Color(255, 255, 255));
        jSeparator4.setFont(new Font("sansserif", 1, 12));
        getContentPane().add(jSeparator4, new AbsoluteConstraints(230, 180, 510, 20));

        jPanel3.setBackground(new Color(0, 79, 153));
        jPanel3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 51), 3));
        jPanel3.setLayout(new AbsoluteLayout());

        jScrollPane1.setBackground(new Color(0, 122, 153));
        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new Color(0, 114, 150));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new AbsoluteConstraints(470, 310, 240, -1));

        btn_decrypt.setBackground(new Color(45, 92, 107));
        btn_decrypt.setFont(new Font("Century Gothic", 1, 14));
        btn_decrypt.setForeground(new Color(255, 255, 255));
        btn_decrypt.setIcon(new ImageIcon("src/main/resources/unlock_button.png"));
        btn_decrypt.setText(" Decrypt ");
        btn_decrypt.setToolTipText("");
        btn_decrypt.setActionCommand("btn_connect");
        btn_decrypt.setBorder(null);
        btn_decrypt.setBorderPainted(false);

        jPanel3.add(btn_decrypt, new AbsoluteConstraints(630, 40, 150, 50));

        jLabel4.setIcon(new ImageIcon("src/main/resources/author_logo.png"));
        jPanel3.add(jLabel4, new AbsoluteConstraints(610, 110, -1, -1));

        jLabel7.setFont(new Font("Century Gothic", 1, 14));
        jLabel7.setForeground(new Color(255, 255, 255));
        jLabel7.setText("MESSAGE RECEIVED");
        jPanel3.add(jLabel7, new AbsoluteConstraints(20, 30, -1, 20));


        //message received
        jSeparator5.setForeground(new Color(255, 255, 255));
        jSeparator5.setFont(new Font("sansserif", 1, 12));
        jPanel3.add(jSeparator5, new AbsoluteConstraints(20, 110, 580, 20));

        txt_messageReceived.setFont(new Font("Century Gothic", 1, 17));
        txt_messageReceived.setForeground(new Color(255, 153, 51));
        txt_messageReceived.setText("");
        jPanel3.add(txt_messageReceived, new AbsoluteConstraints(20, 71, 580, 40));

        getContentPane().add(jPanel3, new AbsoluteConstraints(190, 330, 800, 140));

        jSeparator7.setForeground(new Color(255, 255, 255));
        jSeparator7.setFont(new Font("sansserif", 1, 12));
        getContentPane().add(jSeparator7, new AbsoluteConstraints(230, 280, 510, 20));

        txt_messageEncrypted.setFont(new Font("Century Gothic", 1, 14));
        txt_messageEncrypted.setForeground(new Color(255, 153, 51));
        getContentPane().add(txt_messageEncrypted, new AbsoluteConstraints(230, 260, 510, 20));

        txt_message.setBackground(new Color(32, 32, 95));
        txt_message.setFont(new Font("Century Gothic", 1, 14));
        txt_message.setForeground(new Color(255, 153, 51));
        txt_message.setBorder(null);
        getContentPane().add(txt_message, new AbsoluteConstraints(230, 160, 490, -1));

        jPanel1.setBackground(new Color(32, 32, 95));
        jPanel1.setLayout(new AbsoluteLayout());

        btn_encrypt.setBackground(new Color(45, 92, 107));
        btn_encrypt.setFont(new Font("Century Gothic", 1, 14));
        btn_encrypt.setForeground(new Color(255, 255, 255));
        btn_encrypt.setIcon(new ImageIcon("src/main/resources/lock_button.png"));
        btn_encrypt.setText("Encrypt Message");
        btn_encrypt.setToolTipText("");
        btn_encrypt.setActionCommand("btn_connect");
        btn_encrypt.setBorder(null);
        btn_encrypt.setBorderPainted(false);

        jPanel1.add(btn_encrypt, new AbsoluteConstraints(590, 150, 190, 40));

        btn_send.setBackground(new Color(45, 92, 107));
        btn_send.setFont(new Font("Century Gothic", 1, 14));
        btn_send.setForeground(new Color(255, 255, 255));
        btn_send.setIcon(new ImageIcon("src/main/resources/send_dialog.png"));
        btn_send.setText("Send Message");
        btn_send.setToolTipText("");
        btn_send.setActionCommand("btn_connect");
        btn_send.setBorder(null);
        btn_send.setBorderPainted(false);


        jPanel1.add(btn_send, new AbsoluteConstraints(590, 240, 190, 40));

        btnCancel.setBackground(new Color(32, 32, 95));
        btnCancel.setIcon(new ImageIcon("src/main/resources/clear_button.png"));
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        jPanel1.add(btnCancel, new AbsoluteConstraints(530, 150, -1, -1));

        btnKey.setBackground(new Color(45, 92, 107));
        btnKey.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        btnKey.setForeground(new Color(255, 255, 255));
        btnKey.setIcon(new ImageIcon("src/main/resources/key_icon.png"));
        btnKey.setText("Make a new exchange");
        btnKey.setToolTipText("");
        btnKey.setActionCommand("btn_connect");
        btnKey.setBorder(null);
        btnKey.setBorderPainted(false);
        jPanel1.add(btnKey, new AbsoluteConstraints(470, 40, 220, 40));

        txt_key.setBackground(new Color(32, 32, 95));
        txt_key.setFont(new Font("Century Gothic", 1, 14));
        txt_key.setForeground(new Color(255, 153, 51));
        txt_key.setHorizontalAlignment(JTextField.CENTER);
        txt_key.setBorder(null);
        txt_key.setEditable(false);
        jPanel1.add(txt_key, new AbsoluteConstraints(250, 40, 200, 30));

        jLabel6.setFont(new Font("Century Gothic", 1, 18));
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel6.setText("Common Secret");
        jPanel1.add(jLabel6, new AbsoluteConstraints(80, 50, -1, 20));

        //key
        jSeparator2.setForeground(new Color(255, 255, 255));
        jSeparator2.setFont(new Font("sansserif", 1, 12)); // NOI18N
        jPanel1.add(jSeparator2, new AbsoluteConstraints(255, 70, 170, 20));

        jLabel10.setFont(new Font("Century Gothic", 1, 16)); // NOI18N
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("ENCRYPTED MESSAGE");
        jPanel1.add(jLabel10, new AbsoluteConstraints(40, 220, 200, -1));

        getContentPane().add(jPanel1, new AbsoluteConstraints(190, 0, 800, 330));

        setResizable(false);

        pack();
    }
    protected JButton btnCancel;
    protected JButton btnKey;
    protected JButton btn_connect;
    protected JButton btn_encrypt;
    protected JButton btn_decrypt;
    protected JButton btn_send;
    protected JButton jButton2;
    protected JLabel jLabel1;
    protected JLabel jLabel10;
    protected JLabel jLabel2;
    protected JLabel jLabel3;
    protected JLabel jLabel4;
    protected JLabel jLabel5;
    protected JLabel jLabel6;
    protected JLabel jLabel7;
    protected JLabel jLabel8;
    protected JPanel jPanel1;
    protected JPanel jPanel2;
    protected JPanel jPanel3;
    protected JScrollPane jScrollPane1;
    protected JSeparator jSeparator1;
    protected JSeparator jSeparator2;
    protected JSeparator jSeparator3;
    protected JSeparator jSeparator4;
    protected JSeparator jSeparator5;
    protected JSeparator jSeparator6;
    protected JSeparator jSeparator7;
    protected JTextArea jTextArea1;
    protected JTextField txt_key;
    protected JTextField txt_message;
    protected JLabel txt_messageEncrypted;
    protected JLabel txt_messageReceived;
    public JTextField txt_port;
    protected JTextField txt_status;


    public JTextField getTxt_status(){
        return txt_status;
    }
}
