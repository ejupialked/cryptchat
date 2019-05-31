package com.cryptChat.clientCryptChat;



import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class primaSchermata_mainActivity extends AppCompatActivity {

    public Socket s;

    String msgout = "";
    DataInputStream dIS;
    DataOutputStream dOS;

    String msgin = " ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prima_schermata_xml_);

        final EditText txt_ip, txt_port;
        Button btn_connetti;

        btn_connetti = (Button) findViewById(R.id.btn_connetti);
        txt_ip = (EditText) findViewById(R.id.txt_ip);
        txt_port = (EditText) findViewById(R.id.txt_porta);


        btn_connetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final int port;
                final String ip ;

                port = Integer.parseInt(txt_port.getText().toString());
                ip = txt_ip.getText().toString();
                final Handler handler = new Handler();
                final Handler handler2 = new Handler();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            s = new Socket(ip, port);




                            if(s.isConnected()) {
                                socketCondiviso.setSocket(s);


                                Intent intent = new Intent(primaSchermata_mainActivity.this, secondaSchermata_mainActivity.class);
                                startActivity(intent);


                            }



                        }catch(Exception e)
                        {
                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(primaSchermata_mainActivity.this);
                                    builder.setTitle("Notifica");
                                    builder.setMessage("Connessione server fallita");
                                    builder.setNeutralButton("OK", null);
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }
                            });

                        }

                    }
                });

                t.start();







            }
        });

    }
}
