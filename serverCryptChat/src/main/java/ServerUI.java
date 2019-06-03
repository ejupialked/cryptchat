import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

/**
 *
 * @author Alked Ejupi
 */
public class ServerUI extends javax.swing.JFrame {

    private Server server;


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
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jButton8 = new JButton();
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
        btn_cancella = new  JButton();
        btn_chiave = new JButton();
        txt_chiave = new JTextField();
        jLabel6 = new JLabel();
        jSeparator2 = new JSeparator();
        jLabel10 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CriptChat by Ejupi Alked");
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
        jLabel5.setText("I.I.S.S E. Mattei A.S 2016-2017");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 170, 20));

        btn_connetti.setBackground(new Color(45, 92, 107));
        btn_connetti.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        btn_connetti.setForeground(new Color(255, 255, 255));
        btn_connetti.setIcon(new ImageIcon("src/main/resources/server.png")); // NOI18N
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

        txtPort.setBackground(new java.awt.Color(0, 51, 102));
        txtPort.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtPort.setForeground(new java.awt.Color(255, 255, 255));
        txtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPort.setBorder(null);
        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_portaActionPerformed(evt);
            }
        });
        jPanel2.add(txtPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 50, -1));

        txtStatus.setBackground(new java.awt.Color(0, 51, 102));
        txtStatus.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStatus.setBorder(null);
        txtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_statoActionPerformed(evt);
            }
        });
        jPanel2.add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 190, 30));

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

        txtReceivedMessage.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        txtReceivedMessage.setForeground(new java.awt.Color(255, 153, 51));
        txtReceivedMessage.setText("messaggio ricevuto");
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

        jLabel10.setFont(new Font("Century Gothic", 1, 16)); // NOI18N
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("MESSAGGIO CRIPTATO");
        jPanel1.add(jLabel10, new AbsoluteConstraints(40, 220, 200, -1));

        getContentPane().add(jPanel1, new AbsoluteConstraints(190, 0, 800, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents



    private void btn_connettiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btn_connettiActionPerformed
        int port;

        port = Integer.parseInt(txtPort.getText());
        server.setPort(port);

        Thread connection = new Thread(server);
        connection.start();
    }

    private void txt_portaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txt_portaActionPerformed

    }//GEN-LAST:event_txt_portaActionPerformed

    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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

        chiaveGenerata = CryptMessage.generateKey();
        txt_chiave.setText(chiaveGenerata);

        showCustomMessage(this,
                "Chiave generata con successo!",
                "Notifica" ,
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
                }




            }
        });

        t.start();


    }//GEN-LAST:event_btn_inviaActionPerformed

    private void btn_decriptaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_decriptaActionPerformed

        try {
            decryptedMessage = CryptMessage.decrypt(messaggioRicevuto, chiaveRicevuta);
            txtReceivedMessage.setText(decryptedMessage);


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





    private JButton btn_cancella;
    private JButton btn_chiave;
    private JButton btn_connetti;
    private JButton btn_cripta;
    private JButton btn_decripta;
    private JButton btn_invia;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
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
