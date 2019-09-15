import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 * @author Alked Ejupi
 */
public class ServerUI extends JFrame implements Server.ServerResponse{

    private Server server;

    public ServerUI(Server server) {
        this.server = server;
        server.setServerResponse(this);
        initUI();
    }


    public void initUI(){
        this.setVisible(true);
        initComponents();
        mettiIcona();

        txt_port.setText("5000");
        txt_message.setText("test");

        txt_status.setText("Server Disconnected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.red);
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


    private void mettiIcona() {
        ImageIcon icon = new ImageIcon("src/main/resources/lock_icon.png");
        setIconImage(icon.getImage());
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

        txt_status.setText("Server connected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.green);

        showCustomMessage(this,
                "Server",
                "Notification", -1,
                "src/main/resources/android_logo.png"
        );
    }


    private void initComponents() {
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jSeparator1 = new JSeparator();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        btn_connetti = new JButton();
        txt_port = new JTextField();
        txt_status = new JTextField();
        jSeparator3 = new JSeparator();
        jButton2 = new JButton();
        jLabel8 = new JLabel();
        jSeparator4 = new JSeparator();
        jPanel3 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        btn_decripta = new JButton();
        jLabel4 = new JLabel();
        jLabel7 = new JLabel();
        jSeparator6 = new JSeparator();
        jSeparator5 = new JSeparator();
        txt_messageReceived = new JLabel();
        jSeparator7 = new JSeparator();
        txt_messageEncrypted = new JLabel();
        txt_message = new JTextField();
        jPanel1 = new JPanel();
        btn_cripta = new JButton();
        btn_invia = new JButton();
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
        jLabel5.setText("BALBLA");
        jPanel2.add(jLabel5, new AbsoluteConstraints(10, 420, 170, 20));

        btn_connetti.setBackground(new Color(45, 92, 107));
        btn_connetti.setFont(new Font("Century Gothic", 1, 14));
        btn_connetti.setForeground(new Color(255, 255, 255));
        btn_connetti.setIcon(new ImageIcon("src/main/resources/server_button.png"));
        btn_connetti.setText("Run Server");
        btn_connetti.setToolTipText("");
        btn_connetti.setActionCommand("btn_connetti");
        btn_connetti.setBorder(null);
        btn_connetti.setBorderPainted(false);
        btn_connetti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                connect(evt);
            }
        });



        jPanel2.add(btn_connetti, new AbsoluteConstraints(10, 310, 170, 40));

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

        btn_decripta.setBackground(new Color(45, 92, 107));
        btn_decripta.setFont(new Font("Century Gothic", 1, 14));
        btn_decripta.setForeground(new Color(255, 255, 255));
        btn_decripta.setIcon(new ImageIcon("src/main/resources/unlock_button.png"));
        btn_decripta.setText(" Decrypt ");
        btn_decripta.setToolTipText("");
        btn_decripta.setActionCommand("btn_connetti");
        btn_decripta.setBorder(null);
        btn_decripta.setBorderPainted(false);
        btn_decripta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                decrypt(evt);
            }
        });
        jPanel3.add(btn_decripta, new AbsoluteConstraints(630, 40, 150, 50));

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
        txt_messageReceived.setText("message received");
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

        btn_cripta.setBackground(new Color(45, 92, 107));
        btn_cripta.setFont(new Font("Century Gothic", 1, 14));
        btn_cripta.setForeground(new Color(255, 255, 255));
        btn_cripta.setIcon(new ImageIcon("src/main/resources/lock_button.png"));
        btn_cripta.setText("Encrypt Message");
        btn_cripta.setToolTipText("");
        btn_cripta.setActionCommand("btn_connetti");
        btn_cripta.setBorder(null);
        btn_cripta.setBorderPainted(false);
        btn_cripta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                encrypt(evt);
            }
        });
        jPanel1.add(btn_cripta, new AbsoluteConstraints(590, 150, 190, 40));

        btn_invia.setBackground(new Color(45, 92, 107));
        btn_invia.setFont(new Font("Century Gothic", 1, 14));
        btn_invia.setForeground(new Color(255, 255, 255));
        btn_invia.setIcon(new ImageIcon("src/main/resources/send_dialog.png"));
        btn_invia.setText("Send Message");
        btn_invia.setToolTipText("");
        btn_invia.setActionCommand("btn_connetti");
        btn_invia.setBorder(null);
        btn_invia.setBorderPainted(false);

        btn_invia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                send(evt);
            }
        });


        jPanel1.add(btn_invia, new AbsoluteConstraints(590, 240, 190, 40));

        btnCancel.setBackground(new Color(32, 32, 95));
        btnCancel.setIcon(new ImageIcon("src/main/resources/clear_button.png"));
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clear(evt);
            }
        });
        jPanel1.add(btnCancel, new AbsoluteConstraints(530, 150, -1, -1));

        btnKey.setBackground(new Color(45, 92, 107));
        btnKey.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        btnKey.setForeground(new Color(255, 255, 255));
        btnKey.setIcon(new ImageIcon("src/main/resources/key_icon.png"));
        btnKey.setText("Make a new exchange");
        btnKey.setToolTipText("");
        btnKey.setActionCommand("btn_connetti");
        btnKey.setBorder(null);
        btnKey.setBorderPainted(false);
        btnKey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newKey(evt);
            }
        });
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


    private JButton btnCancel;
    private JButton btnKey;
    private JButton btn_connetti;
    private JButton btn_cripta;
    private JButton btn_decripta;
    private JButton btn_invia;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JSeparator jSeparator5;
    private JSeparator jSeparator6;
    private JSeparator jSeparator7;
    private JTextArea jTextArea1;
    private JTextField txt_key;
    private JTextField txt_message;
    private JLabel txt_messageEncrypted;
    private JLabel txt_messageReceived;
    public JTextField txt_port;
    private JTextField txt_status;


    public JTextField getTxt_status(){
        return txt_status;
    }

}
