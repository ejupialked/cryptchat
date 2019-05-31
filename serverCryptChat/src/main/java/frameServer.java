
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.security.Key;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alked Ejupi
 */
public class frameServer extends javax.swing.JFrame {
    static String AES = "AES";

    String output;
    static String result = "";
    static Thread t;
    static Socket s;
    static ServerSocket ss;
    String msgout = " ";
    static DataOutputStream dOS;
    static DataInputStream dIS;
    static String encryptedMessage;
    public String decryptedMessage = "";
    static String chiaveRicevuta = "", chiaveGenerata = "";
    static String messaggioRicevuto = "", messaggioDaInviare = "";
    static String data= " " ;

    public frameServer() {
        initComponents();
        mettiIcona();

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_connetti = new javax.swing.JButton();
        txt_porta = new javax.swing.JTextField();
        txt_stato = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btn_decripta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        txt_msgRicevuto = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txt_msgCriptato = new javax.swing.JLabel();
        txt_msg = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btn_cripta = new javax.swing.JButton();
        btn_invia = new javax.swing.JButton();
        btn_cancella = new javax.swing.JButton();
        btn_chiave = new javax.swing.JButton();
        txt_chiave = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptChat by Ejupi Alked");
        setBackground(new java.awt.Color(255, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/resources/lockk.png")); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 190, 140));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PORTA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 50, 20));

        jSeparator1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 70, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon("src/main/resources/Webp.net-resizeimage.png")); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 40));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("I.I.S.S E. Mattei A.S 2016-2017");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 170, 20));

        btn_connetti.setBackground(new java.awt.Color(45, 92, 107));
        btn_connetti.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_connetti.setForeground(new java.awt.Color(255, 255, 255));
        btn_connetti.setIcon(new javax.swing.ImageIcon("src/main/resources/server.png")); // NOI18N
        btn_connetti.setText("Avvia Server");
        btn_connetti.setToolTipText("");
        btn_connetti.setActionCommand("btn_connetti");
        btn_connetti.setBorder(null);
        btn_connetti.setBorderPainted(false);
        btn_connetti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connettiActionPerformed(evt);
            }
        });
        jPanel2.add(btn_connetti, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 170, 40));

        txt_porta.setBackground(new java.awt.Color(0, 51, 102));
        txt_porta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txt_porta.setForeground(new java.awt.Color(255, 255, 255));
        txt_porta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_porta.setBorder(null);
        txt_porta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_portaActionPerformed(evt);
            }
        });
        jPanel2.add(txt_porta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 50, -1));

        txt_stato.setBackground(new java.awt.Color(0, 51, 102));
        txt_stato.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_stato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_stato.setBorder(null);
        txt_stato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_statoActionPerformed(evt);
            }
        });
        jPanel2.add(txt_stato, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 190, 30));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 190, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 470));

        jButton2.setBackground(new java.awt.Color(45, 92, 107));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Avvia Server");
        jButton2.setToolTipText("");
        jButton2.setActionCommand("btn_connetti");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 100, 40));

        jButton3.setBackground(new java.awt.Color(45, 92, 107));
        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Avvia Server");
        jButton3.setToolTipText("");
        jButton3.setActionCommand("btn_connetti");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 100, 40));

        jButton4.setBackground(new java.awt.Color(45, 92, 107));
        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Avvia Server");
        jButton4.setToolTipText("");
        jButton4.setActionCommand("btn_connetti");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 100, 40));

        jButton5.setBackground(new java.awt.Color(45, 92, 107));
        jButton5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Avvia Server");
        jButton5.setToolTipText("");
        jButton5.setActionCommand("btn_connetti");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 100, 40));

        jButton6.setBackground(new java.awt.Color(45, 92, 107));
        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Avvia Server");
        jButton6.setToolTipText("");
        jButton6.setActionCommand("btn_connetti");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 100, 40));

        jButton7.setBackground(new java.awt.Color(45, 92, 107));
        jButton7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Avvia Server");
        jButton7.setToolTipText("");
        jButton7.setActionCommand("btn_connetti");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 100, 40));

        jButton8.setBackground(new java.awt.Color(45, 92, 107));
        jButton8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Avvia Server");
        jButton8.setToolTipText("");
        jButton8.setActionCommand("btn_connetti");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 100, 40));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("MESSAGGIO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 510, 20));

        jPanel3.setBackground(new java.awt.Color(0, 79, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 3));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(0, 122, 153));
        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(0, 114, 150));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 240, -1));

        btn_decripta.setBackground(new java.awt.Color(45, 92, 107));
        btn_decripta.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_decripta.setForeground(new java.awt.Color(255, 255, 255));
        btn_decripta.setIcon(new javax.swing.ImageIcon("src/main/resources/unlock1.png")); // NOI18N
        btn_decripta.setText(" Decripta ");
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
        jLabel7.setText("MESSAGGIO RICEVUTO");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 330, 20));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 580, 20));

        txt_msgRicevuto.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        txt_msgRicevuto.setForeground(new java.awt.Color(255, 153, 51));
        txt_msgRicevuto.setText("messaggio ricevuto");
        jPanel3.add(txt_msgRicevuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 71, 580, 40));

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
        txt_msg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_msgActionPerformed(evt);
            }
        });
        getContentPane().add(txt_msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 490, -1));

        jPanel1.setBackground(new java.awt.Color(32, 32, 95));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_cripta.setBackground(new java.awt.Color(45, 92, 107));
        btn_cripta.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cripta.setForeground(new java.awt.Color(255, 255, 255));
        btn_cripta.setIcon(new javax.swing.ImageIcon("src/main/resources/lucchetto.png")); // NOI18N
        btn_cripta.setText("Cripta  Messaggio");
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
        btn_invia.setText("Invia Messaggio");
        btn_invia.setToolTipText("");
        btn_invia.setActionCommand("btn_connetti");
        btn_invia.setBorder(null);
        btn_invia.setBorderPainted(false);
        btn_invia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inviaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_invia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 190, 40));

        btn_cancella.setBackground(new java.awt.Color(32, 32, 95));
        btn_cancella.setIcon(new javax.swing.ImageIcon("src/main/resources/cancel.png")); // NOI18N
        btn_cancella.setBorder(null);
        btn_cancella.setBorderPainted(false);
        btn_cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancellaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancella, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, -1, -1));

        btn_chiave.setBackground(new java.awt.Color(45, 92, 107));
        btn_chiave.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_chiave.setForeground(new java.awt.Color(255, 255, 255));
        btn_chiave.setIcon(new javax.swing.ImageIcon("src/main/resources/chiave.png")); // NOI18N
        btn_chiave.setText("Genera Chiave");
        btn_chiave.setToolTipText("");
        btn_chiave.setActionCommand("btn_connetti");
        btn_chiave.setBorder(null);
        btn_chiave.setBorderPainted(false);
        btn_chiave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chiaveActionPerformed(evt);
            }
        });
        jPanel1.add(btn_chiave, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 180, 40));

        txt_chiave.setBackground(new java.awt.Color(32, 32, 95));
        txt_chiave.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_chiave.setForeground(new java.awt.Color(255, 153, 51));
        txt_chiave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_chiave.setBorder(null);
        txt_chiave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chiaveActionPerformed(evt);
            }
        });
        jPanel1.add(txt_chiave, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 160, 30));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CHIAVE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 20));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 160, 20));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("MESSAGGIO CRIPTATO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 200, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 800, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public String generateKey() {
        String key = "";

        Random random = new Random();
        for (int x = 0; x < 16; x++) {
            int c = random.nextInt(122 - 48) + 48;
            if ((c >= 50 && c <= 64) | (c >= 91 && c <= 96)) {
                x--;
                continue;
            }
            key += ((char) c);


        }
        return key;

    }
    public String encrypt(String message, String key) throws Exception {

        byte[] byteMessage = message.getBytes();
        byte[] byteKey = key.getBytes();


        Key secretKey = new SecretKeySpec(byteKey, "AES");


        Cipher c = Cipher.getInstance(AES);

        c.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipher = c.doFinal(byteMessage);

        String encryptedValue = Base64.encode(cipher);
        return encryptedValue;

    }
    public String decrypt(String encryptedMessage, String key) throws Exception
    {
        byte[] byteKey = key.getBytes();

        Key secretKey = new SecretKeySpec(byteKey, AES);

        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedValue = Base64.decode(encryptedMessage);
        byte[] decValue =c.doFinal(decodedValue);

        String decryptedValue =new String (decValue);
        return decryptedValue;

    }
    private void btn_connettiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connettiActionPerformed

        Thread t = new Thread(new Runnable() {
            public void run() {

                try {

                    try {
                        ss =new ServerSocket(Integer.parseInt(txt_porta.getText()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La chiave deve essere di 16 caratteri", "Errore",
                                JOptionPane.ERROR_MESSAGE);

                    }

                    txt_stato.setText("In ricerca di connessioni...");
                    txt_stato.setForeground(Color.WHITE);
                    txt_stato.setBackground(Color.decode("#003366"));
                    s= ss.accept();

                    txt_stato.setText("SERVER CONNESSO");
                    txt_stato.setForeground(Color.BLACK);

                    txt_stato.setBackground(Color.green);
                    JLabel label = new JLabel("Connessione stabilita con Dispositivo Mobile.");
                    label.setFont(new Font("Century Gothic", Font.BOLD, 15));
                    ImageIcon icon = new ImageIcon(getClass().getResource("src/main/resources/androidDialogo.png"));
                    JOptionPane.showMessageDialog(null,label,"Notifica",JOptionPane.DEFAULT_OPTION,icon);

                    dIS = new DataInputStream(s.getInputStream());
                    dOS = new DataOutputStream(s.getOutputStream());

                    while(!data.equals("exit"))
                    {
                        data = dIS.readUTF();

                        int fineChiave = data.indexOf("/#&#/");
                        int inizioChiave = 0;
                        int inizioMessaggio = data.indexOf("/#&#/") + 5;
                        int fineMessaggio = data.length();

                        chiaveRicevuta = data.substring(inizioChiave, fineChiave);
                        messaggioRicevuto = data.substring(inizioMessaggio, fineMessaggio);

                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                JLabel label = new JLabel("Hai appena ricevuto un messaggio!");
                                label.setFont(new Font("Century Gothic", Font.BOLD, 15));
                                ImageIcon icon = new ImageIcon(getClass().getResource("src/main/resources/androidDialogo.png"));
                                JOptionPane.showMessageDialog(null,label,"Notifica",JOptionPane.DEFAULT_OPTION,icon);

                            }
                        });
                        t.start();

                        txt_msgRicevuto.setText(messaggioRicevuto);

                    }

                } catch (IOException ex) {

                }

            }
        });
        t.start();

    }//GEN-LAST:event_btn_connettiActionPerformed

    private void txt_portaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_portaActionPerformed

    }//GEN-LAST:event_txt_portaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_criptaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_criptaActionPerformed
        try {

            messaggioDaInviare = txt_msg.getText();
            chiaveGenerata = txt_chiave.getText();

            boolean procedi = true;

            if(chiaveGenerata.length() != 16 && chiaveGenerata.length() != 0)
            {
                JOptionPane.showMessageDialog(null, "La chiave deve essere di 16 caratteri", "Errore",
                        JOptionPane.ERROR_MESSAGE);
                procedi = false;
            }

            else if (chiaveGenerata.length() == 0)
            {
                JOptionPane.showMessageDialog(null, "Non hai inserito nessuna chiave", "Errore",
                        JOptionPane.ERROR_MESSAGE);
                procedi = false;
            }

            if(procedi)
            {
                encryptedMessage = encrypt(messaggioDaInviare, chiaveGenerata);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        txt_msgCriptato.setText(encryptedMessage);
        JLabel label = new JLabel("Il messaggio รจ stato criptato!");
        label.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ImageIcon icon = new ImageIcon("src/main/resources/lockDialogo.png");
        JOptionPane.showMessageDialog(null,label,"Notifica",JOptionPane.DEFAULT_OPTION,icon);


    }//GEN-LAST:event_btn_criptaActionPerformed

    private void btn_chiaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chiaveActionPerformed
        chiaveGenerata = generateKey();
        txt_chiave.setText(chiaveGenerata);
        JLabel label = new JLabel("Chiave generata con successo!");
        label.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ImageIcon icon = new ImageIcon(("src/main/resources/chiaveDialogo.png"));
        JOptionPane.showMessageDialog(null,label,"Notifica",JOptionPane.DEFAULT_OPTION,icon);

    }//GEN-LAST:event_btn_chiaveActionPerformed

    private void btn_inviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inviaActionPerformed

        Thread t = new Thread(new Runnable() {
            public void run() {


                try {
                    messaggioDaInviare = txt_msgCriptato.getText();

                    if(messaggioDaInviare == "")
                    {
                        JOptionPane.showMessageDialog(null, "Compilare tutti i campi!", "Errore",
                                JOptionPane.ERROR_MESSAGE);

                    }
                    String data = chiaveGenerata + "/#&#/" + messaggioDaInviare;
                    dOS.writeUTF(data);
                    dOS.flush();
                    JLabel label = new JLabel("Messaggio inviato con successo!");
                    label.setFont(new Font("Century Gothic", Font.BOLD, 15));
                    ImageIcon icon = new ImageIcon(("src/main/resources/lockDialogo.png"));
                    JOptionPane.showMessageDialog(null,label,"Notifica",JOptionPane.DEFAULT_OPTION,icon);

                } catch (IOException ex) {
                    Logger.getLogger(frameServer.class.getName()).log(Level.SEVERE, null, ex);
                }




            }
        });

        t.start();


    }//GEN-LAST:event_btn_inviaActionPerformed

    private void btn_decriptaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_decriptaActionPerformed

        try {
            decryptedMessage = decrypt(messaggioRicevuto, chiaveRicevuta);
            txt_msgRicevuto.setText(decryptedMessage);
            JLabel label = new JLabel("Messaggio Decriptato!");
            label.setFont(new Font("Century Gothic", Font.BOLD, 15));
            ImageIcon icon = new ImageIcon(("src/main/resources/unlock.png"));
            JOptionPane.showMessageDialog(null,label,"Notifica",JOptionPane.DEFAULT_OPTION,icon);

        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btn_decriptaActionPerformed

    private void txt_chiaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chiaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chiaveActionPerformed

    private void txt_msgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_msgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_msgActionPerformed

    private void btn_cancellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancellaActionPerformed
        txt_msg.setText("");
    }//GEN-LAST:event_btn_cancellaActionPerformed

    private void txt_statoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_statoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_statoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {

        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frameServer().setVisible(true);
                txt_stato.setText("SERVER DISCONESSO");
                txt_stato.setForeground(Color.BLACK);

                txt_stato.setBackground(Color.red);

            }





        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancella;
    private javax.swing.JButton btn_chiave;
    private javax.swing.JButton btn_connetti;
    private javax.swing.JButton btn_cripta;
    private javax.swing.JButton btn_decripta;
    private javax.swing.JButton btn_invia;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txt_chiave;
    private javax.swing.JTextField txt_msg;
    private javax.swing.JLabel txt_msgCriptato;
    private static javax.swing.JLabel txt_msgRicevuto;
    public javax.swing.JTextField txt_porta;
    private static javax.swing.JTextField txt_stato;
    // End of variables declaration//GEN-END:variables

    private void mettiIcona() {



            ImageIcon icon = new ImageIcon("src/main/resources/lock.png");
            setIconImage(icon.getImage());



    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}
