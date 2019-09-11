import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

public class CryptMessage {

    private static String AES = "AES";

    private X509EncodedKeySpec x509KeySpec;

    private KeyPairGenerator     serverKeyPair;
    private KeyPair                 serverPair;
    private KeyAgreement    serverKeyAgreement;
    private KeyFactory              keyFactory;

    private PrivateKey privateKey;
    private PublicKey   publicKey;

    private byte[] secretKey;



    public void generateDHKeyPair() throws NoSuchAlgorithmException {
        serverKeyPair = KeyPairGenerator.getInstance("DH");
        serverKeyPair.initialize(2048);

        serverPair = serverKeyPair.generateKeyPair();
        privateKey = serverPair.getPrivate();
        publicKey  = serverPair.getPublic();
    }

    public void initDHAgreement() throws NoSuchAlgorithmException, InvalidKeyException {
        serverKeyAgreement = KeyAgreement.getInstance("DH");
        serverKeyAgreement.init(privateKey);
    }


    public String getPublicKeyEncoded(){
        return encodeBase64(publicKey.getEncoded());
    }


    public String encodeBase64(byte[] data) {
        return Base64.encode(data);
    }

    public byte[] decodeBase64(String data) {
        return Base64.decode(data);
    }



    public void receivePublicKeyFromClient(String publicKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        byte[] pubKeyDecoded = decodeBase64(publicKeyBase64);

        keyFactory = KeyFactory.getInstance("DH");
        x509KeySpec = new X509EncodedKeySpec(pubKeyDecoded);

        PublicKey publicKey1 = keyFactory.generatePublic(x509KeySpec);

        serverKeyAgreement.doPhase(publicKey, true);

    }

    public byte[] generateSecretKey() {
        return serverKeyAgreement.generateSecret();
    }

    public SecretKeySpec generateAESKey(byte[] secretKey){
        return new SecretKeySpec(secretKey, 0, 16, AES);
    }




    public String encrypt(String message) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, generateAESKey(secretKey));

        byte[] plainText = message.getBytes();
        byte[] cipherText = cipher.doFinal(plainText);

        return encodeBase64(cipherText);
    }



    public String decrypt(String encryptedMessage) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, generateAESKey(secretKey));

        byte[] decodedMessage = Base64.decode(encryptedMessage);
        byte[] recovered = cipher.doFinal(decodedMessage);

        return new String(recovered);
    }

}
