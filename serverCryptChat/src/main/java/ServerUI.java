import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author Alked Ejupi
 */
public class ServerUI extends javax.swing.JFrame {

    private Server server;
    private String messageReceived;
    private String keyReceived;


    public ServerUI(Server server) {

        this.server = server;
        this.server.setUI(this);
        this.setVisible(true);
        initComponents();
        mettiIcona();

        txtStatus.setText("Server Disconnected");
        txtStatus.setForeground(Color.BLACK);
        txtStatus.setBackground(Color.red);
    }


    private void initComponents() {
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jSeparator1 = new JSeparator();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        btn_connetti = new JButton();
        txtPort = new JTextField();
        txtStatus = new JTextField();
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
        txtReceivedMessage = new JLabel();
        jSeparator7 = new JSeparator();
        txt_msgCriptato = new JLabel();
        txt_msg = new JTextField();
        jPanel1 = new JPanel();
        btn_cripta = new JButton();
        btn_invia = new JButton();
        btnCancel = new  JButton();
        btnKey = new JButton();
        txt_chiave = new JTextField();
        jLabel6 = new JLabel();
        jSeparator2 = new JSeparator();
        jLabel10 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CryptChat by Ejupi Alked");
        setBackground(new Color(255, 0, 0));
        setCursor(new java.awt.Cursor(Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new AbsoluteLayout());

        jPanel2.setBackground(new Color(0, 51, 102));
        jPanel2.setLayout(new AbsoluteLayout());

        jLabel1.setIcon(new ImageIcon("src/main/resources/lockk.png"));
        jPanel2.add(jLabel1, new AbsoluteConstraints(0, 80, 190, 140));

        jLabel2.setFont(new Font("Century Gothic", 1, 14));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("PORT");
        jPanel2.add(jLabel2, new AbsoluteConstraints(20, 230, 50, 20));

        jSeparator1.setFont(new Font("sansserif", 1, 12));
        jPanel2.add(jSeparator1, new AbsoluteConstraints(80, 250, 70, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon("src/main/resources/Webp.net-resizeimage.png")); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 40));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 170, 20));

        btn_connetti.setBackground(new Color(45, 92, 107));
        btn_connetti.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        btn_connetti.setForeground(new Color(255, 255, 255));
        btn_connetti.setIcon(new ImageIcon("src/main/resources/server.png")); // NOI18N
        btn_connetti.setText("Run Server");
        btn_connetti.setToolTipText("");
        btn_connetti.setActionCommand("btn_connetti");
        btn_connetti.setBorder(null);
        btn_connetti.setBorderPainted(false);
        btn_connetti.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connettiActionPerformed(evt);
            }
        });
        jPanel2.add(btn_connetti, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 170, 40));

        txtPort.setBackground(new java.awt.Color(0, 51, 102));
        txtPort.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtPort.setForeground(new java.awt.Color(255, 255, 255));
        txtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPort.setBorder(null);;
        jPanel2.add(txtPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 50, -1));

        txtStatus.setBackground(new java.awt.Color(0, 51, 102));
        txtStatus.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStatus.setBorder(null);
        jPanel2.add(txtStatus, new AbsoluteConstraints(0, 370, 190, 30));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 190, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 470));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Message");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 510, 20));

        jPanel3.setBackground(new java.awt.Color(0, 79, 153));
        jPanel3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 51), 3));
        jPanel3.setLayout(new AbsoluteLayout());

        jScrollPane1.setBackground(new Color(0, 122, 153));
        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new Color(0, 114, 150));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new AbsoluteConstraints(470, 310, 240, -1));

        btn_decripta.setBackground(new java.awt.Color(45, 92, 107));
        btn_decripta.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_decripta.setForeground(new java.awt.Color(255, 255, 255));
        btn_decripta.setIcon(new javax.swing.ImageIcon("src/main/resources/unlock1.png")); // NOI18N
        btn_decripta.setText(" Decrypt ");
        btn_decripta.setToolTipText("");
        btn_decripta.setActionCommand("btn_connetti");
        btn_decripta.setBorder(null);
        btn_decripta.setBorderPainted(false);
        btn_decripta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_decriptaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_decripta, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 150, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon("src/main/resources/transparent_text_effect--1-.png")); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MESSAGE RECEIVED");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 330, 20));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 580, 20));

        txtReceivedMessage.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        txtReceivedMessage.setForeground(new java.awt.Color(255, 153, 51));
        txtReceivedMessage.setText("message received");
        jPanel3.add(txtReceivedMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 71, 580, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 800, 140));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 510, 20));

        txt_msgCriptato.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_msgCriptato.setForeground(new java.awt.Color(255, 153, 51));
        getContentPane().add(txt_msgCriptato, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 510, 20));

        txt_msg.setBackground(new java.awt.Color(32, 32, 95));
        txt_msg.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_msg.setForeground(new java.awt.Color(255, 153, 51));
        txt_msg.setBorder(null);
        getContentPane().add(txt_msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 490, -1));

        jPanel1.setBackground(new java.awt.Color(32, 32, 95));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_cripta.setBackground(new java.awt.Color(45, 92, 107));
        btn_cripta.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cripta.setForeground(new java.awt.Color(255, 255, 255));
        btn_cripta.setIcon(new javax.swing.ImageIcon("src/main/resources/lucchetto.png")); // NOI18N
        btn_cripta.setText("Encrypt Message");
        btn_cripta.setToolTipText("");
        btn_cripta.setActionCommand("btn_connetti");
        btn_cripta.setBorder(null);
        btn_cripta.setBorderPainted(false);
        btn_cripta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_criptaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cripta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 190, 40));

        btn_invia.setBackground(new java.awt.Color(45, 92, 107));
        btn_invia.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_invia.setForeground(new java.awt.Color(255, 255, 255));
        btn_invia.setIcon(new javax.swing.ImageIcon("src/main/resources/send.png")); // NOI18N
        btn_invia.setText("Send Message");
        btn_invia.setToolTipText("");
        btn_invia.setActionCommand("btn_connetti");
        btn_invia.setBorder(null);
        btn_invia.setBorderPainted(false);
        btn_invia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inviaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_invia, new AbsoluteConstraints(590, 240, 190, 40));

        btnCancel.setBackground(new java.awt.Color(32, 32, 95));
        btnCancel.setIcon(new javax.swing.ImageIcon("src/main/resources/cancel.png")); // NOI18N
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancellaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, -1, -1));

        btnKey.setBackground(new java.awt.Color(45, 92, 107));
        btnKey.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnKey.setForeground(new java.awt.Color(255, 255, 255));
        btnKey.setIcon(new javax.swing.ImageIcon("src/main/resources/chiave.png")); // NOI18N
        btnKey.setText("Generate Key");
        btnKey.setToolTipText("");
        btnKey.setActionCommand("btn_connetti");
        btnKey.setBorder(null);
        btnKey.setBorderPainted(false);
        btnKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chiaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 180, 40));

        txt_chiave.setBackground(new java.awt.Color(32, 32, 95));
        txt_chiave.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_chiave.setForeground(new java.awt.Color(255, 153, 51));
        txt_chiave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_chiave.setBorder(null);
        jPanel1.add(txt_chiave, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 160, 30));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("KEY");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 20));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 160, 20));

        jLabel10.setFont(new Font("Century Gothic", 1, 16)); // NOI18N
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("ENCRYPTED MESSAGE");
        jPanel1.add(jLabel10, new AbsoluteConstraints(40, 220, 200, -1));

        getContentPane().add(jPanel1, new AbsoluteConstraints(190, 0, 800, 330));

        pack();
    }



    private void btn_connettiActionPerformed(ActionEvent evt) {
        try {
            server.setPort(txtPort.getText());
            Thread connection = new Thread(server);
            connection.start();
        } catch (Exception e) {
            showCustomMessage(this, e.getMessage(), "Error", 0, null);
            txtPort.setText("");
        }
    }



    private void btn_criptaActionPerformed(ActionEvent evt) {
        String message = txt_msg.getText();
        String key = txt_chiave.getText();

        try {
            String keyEncrypted = server.encryptMessage(message, key);
            txt_msgCriptato.setText(keyEncrypted);
            showCustomMessage(this, "The message has been ecrypted!",
                    "Notification", -1,"src/main/resources/lockDialogo.png" );
        } catch (Exception e) {
            showCustomMessage(this, e.getMessage(), "Error", 0, null);
        }
    }

    private void btn_chiaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chiaveActionPerformed
        String key = CryptMessage.generateKey();
        txt_chiave.setText(key);

        showCustomMessage(this,
                "Key generated!",
                "Notification" ,
                -1,
                "src/main/resources/chiaveDialogo.png");
    }



    public void showCustomMessage(JFrame parent, String text, String title, int messageType, String pathImg){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ImageIcon icon = new ImageIcon(pathImg);
        JOptionPane.showMessageDialog(parent,label,title,messageType,icon);

    }

    private void btn_inviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inviaActionPerformed
        String message = txt_msgCriptato.getText();
        String key = txt_chiave.getText();

        try {
            server.sendMessage(message, key);
            showCustomMessage(this, "Your message has been sent",
                    "Notification", -1,
                    "src/main/resources/lockDialogo.png");
        } catch (Exception e) {
            showCustomMessage(this, e.getMessage(),"Error", 0, null);
        }

    }

    private void btn_decriptaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String decryptedMessage = CryptMessage.decrypt(messageReceived, keyReceived);
            txtReceivedMessage.setText(decryptedMessage);
            showCustomMessage(this, "Messaggio Decriptato!", "Notification",
                    -1, "src/main/resources/unlock.png" );
        } catch (Exception e) {
            showCustomMessage(this, "Error decryption", "error", -1,null);
        }
    }


    public void setMessageReceived(String messageReceived) {
        this.messageReceived = messageReceived;
        txtReceivedMessage.setText(messageReceived);
    }

    public void setKeyReceived(String keyReceived) {
        this.keyReceived = keyReceived;
    }

    private void btn_cancellaActionPerformed(java.awt.event.ActionEvent evt) {
        txt_msg.setText("");
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txt_chiave;
    private javax.swing.JTextField txt_msg;
    private javax.swing.JLabel txt_msgCriptato;
    private static JLabel txtReceivedMessage;
    public JTextField txtPort;
    private static JTextField txtStatus;



    public void showConnectionEstablished(){
        txtStatus.setText("Server connected");
        txtStatus.setForeground(Color.BLACK);
        txtStatus.setBackground(Color.green);

        showCustomMessage(this,
                "Connection established with Android device",
                "Notification", -1,
                "src/main/resources/androidDialogo.png"
        );
    }

    public JTextField getTxtPort(){
        return txtPort;
    }

    public JTextField getTxtStatus(){
        return txtStatus;
    }

    public JLabel getTxtReceivedMessage(){
        return txtReceivedMessage;
    }
    private void mettiIcona() {
       ImageIcon icon = new ImageIcon("src/main/resources/lock.png");
            setIconImage(icon.getImage());
    }

}
