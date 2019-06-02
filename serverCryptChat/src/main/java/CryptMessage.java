import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Random;

public class CryptMessage {

    static String AES = "AES";


    public static String generateKey() {
        String key = null;
        for (int x = 0; x < 16; x++) {
            int c = new Random().nextInt(122 - 48) + 48;

            if ((c >= 50 && c <= 64) | (c >= 91 && c <= 96)) {
                x--;
                continue;
            }

            key += ((char) c);

        }
        return key;
    }

    public static String encrypt(String message, String key) throws Exception {

        byte[] byteMessage = message.getBytes();
        byte[] byteKey = key.getBytes();


        Key secretKey = new SecretKeySpec(byteKey, "AES");


        Cipher c = Cipher.getInstance(AES);

        c.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipher = c.doFinal(byteMessage);

        String encryptedValue = Base64.encode(cipher);
        return encryptedValue;

    }
    public static String decrypt(String encryptedMessage, String key) throws Exception
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

}
