package com.cryptChat.clientCryptChat;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class secondaSchermata_mainActivity extends AppCompatActivity {
    DataInputStream dIS;
    DataOutputStream dOS;
    Socket s;
    AES aes;
    String newME = "";

    String AES = "AES";
    String encryptedMessage;
    public String decryptedMessage = "";
    String chiaveRicevuta = "", chiaveGenerata = "";
    String messaggioRicevuto = "", messaggioDaInviare = "";
    String data= " " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seconda_schermata_xml_);

        final TextView txt_msg, txt_key, txt_msgCriptato, txt_msgRicevuto;
        final Button btn_key, btn_cripta, btn_decripta;
        final ImageButton btn_invia;

        btn_invia = (ImageButton) findViewById(R.id.btn_invia);
        btn_cripta = (Button) findViewById(R.id.btn_cripta);
        btn_decripta = (Button) findViewById(R.id.btn_decripta1);
        btn_key = (Button) findViewById(R.id.btn_key);


        txt_msg = (TextView) findViewById(R.id.txt_msg);
        txt_key = (TextView) findViewById(R.id.txt_key);
        txt_msgCriptato = (TextView) findViewById(R.id.txt_msgCriptato);
        txt_msgRicevuto = (TextView) findViewById(R.id.txt_msgRicevuto2);





        final Handler handler = new Handler();
        final Handler handler7 = new Handler();
        final Handler handler8 = new Handler();

        final Handler handler5 = new Handler();
        final Handler handler6 = new Handler();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    handler5.post(new Runnable() {
                        @Override
                        public void run() {

                            AlertDialog.Builder builder = new AlertDialog.Builder(secondaSchermata_mainActivity.this);
                            builder.setTitle("Notifica");
                            builder.setMessage("La connessione con il server è avvenuta con successo");
                            builder.setNeutralButton("OK", null);
                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                    });


                    s = socketCondiviso.getSocket();

                    dIS = new DataInputStream(s.getInputStream());
                    dOS = new DataOutputStream(s.getOutputStream());

                    while (!data.equals("exit")) {
                        data =  dIS.readUTF();



                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ;



                                int fineChiave = data.indexOf("/#&#/");
                                int inizioChiave = 0;
                                int inizioMessaggio = data.indexOf("/#&#/") + 5;
                                int fineMessaggio = data.length();


                                chiaveRicevuta = data.substring(inizioChiave, fineChiave);
                                messaggioRicevuto = data.substring(inizioMessaggio, fineMessaggio);


                                txt_msgRicevuto.setMovementMethod(ScrollingMovementMethod.getInstance());
                                txt_msgRicevuto.setText(messaggioRicevuto);
                                handler6.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(secondaSchermata_mainActivity.this);
                                        builder.setTitle("Notifica");
                                        builder.setMessage("Hai ricevuto un messaggio criptato dal server!");
                                        builder.setNeutralButton("OK", null);
                                        AlertDialog alert = builder.create();
                                        alert.show();

                                    }
                                });
                            }


                        });

                    }


                } catch (Exception e) {
                }

            }
        });

        t.start();



        final Handler handler2 = new Handler();
        final Handler handler1 = new Handler();
        final Handler handler3 = new Handler();
        final Handler handler4 = new Handler();
        btn_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                chiaveGenerata = generateKey();
                Toast.makeText(getApplicationContext(), "Chiave Generata!",Toast.LENGTH_SHORT).show();

                handler3.post(new Runnable() {
                    @Override
                    public void run() {
                        txt_key.setText(chiaveGenerata);
                    }
                });

            }
        });



        btn_invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void  run() {
                        messaggioDaInviare = txt_msgCriptato.getText().toString();

                        try {

                            if (messaggioDaInviare == "")
                            {
                                handler7.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Compilare tutti i campi!",Toast.LENGTH_SHORT).show();


                                    }
                                });
                            }



                            String data = chiaveGenerata + "/#&#/" + messaggioDaInviare;
                            dOS.writeUTF(data);
                            dOS.flush();
                            handler8.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Messaggio inviato con successo!!",Toast.LENGTH_SHORT).show();


                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                    }
                    }
                });

                t.start();
            }
        });

        btn_cripta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    messaggioDaInviare = txt_msg.getText().toString();
                    chiaveGenerata = txt_key.getText().toString();

                    boolean procedi = true;

                    if(chiaveGenerata.length() != 16 && chiaveGenerata.length() != 0)
                    {
                        Toast.makeText(getApplicationContext(), "La chiave deve essere di 16 caratteri!",Toast.LENGTH_SHORT).show();


                        procedi = false;
                    }

                    else if (chiaveGenerata.length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Non hai inserito la chiave!",Toast.LENGTH_SHORT).show();

                        procedi = false;
                    }

                    if(procedi)
                    {
                        encryptedMessage = encrypt(messaggioDaInviare, chiaveGenerata);
                    }
                    encryptedMessage = encrypt(messaggioDaInviare, chiaveGenerata);
                    Toast.makeText(getApplicationContext(), "Messaggio criptato!",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        txt_msgCriptato.setMovementMethod(ScrollingMovementMethod.getInstance());

                        txt_msgCriptato.setText(encryptedMessage);
                    }
                });

            }
        });


        btn_decripta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    decryptedMessage = decrypt(messaggioRicevuto, chiaveRicevuta);
                    Toast.makeText(getApplicationContext(), "Messaggio Decriptato!",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


                handler1.post(new Runnable() {
                        @Override
                        public void run() {

                            txt_msgRicevuto.setText(decryptedMessage);
                        }
                    });

                }
        });
    }

    public String encrypt(String message, String key) throws Exception {
        byte[] byteMessage = message.getBytes();
        byte[] byteKey = key.getBytes();


        Key secretKey = new SecretKeySpec(byteKey, "AES");


        Cipher c = Cipher.getInstance(AES);

        c.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipher = c.doFinal(byteMessage);

        String encryptedValue = com.example.alki.clientcrip.Base64.encode(cipher);
        return encryptedValue;

    }


    public String decrypt(String encryptedMessage, String key) throws Exception
    {
        byte[] byteKey = key.getBytes();

        Key secretKey = new SecretKeySpec(byteKey, AES);

        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedValue = com.example.alki.clientcrip.Base64.decode(encryptedMessage);
        byte[] decValue =c.doFinal(decodedValue);

        String decryptedValue =new String (decValue);
        return decryptedValue;

    }

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

}
