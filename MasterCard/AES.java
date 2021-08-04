//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ng.AES;

import com.ng.util.LoadPropertyFile;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

public class AES {
    static LoadPropertyFile loadPropertyFile = new LoadPropertyFile();
    private static final String ALGORITHM;
    private static final byte[] keyValue;

    public AES() {
    }

    public static byte[] encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(1, key);
        byte[] encVal = c.doFinal(value.getBytes());
        return encVal;
    }

    public static String decrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(2, key);
        byte[] decordedValue = (new BASE64Decoder()).decodeBuffer(value);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    static {
        ALGORITHM = loadPropertyFile.getPropertyValue("Encryption");
        keyValue = new byte[]{84, 104, 105, 115, 73, 115, 65, 83, 101, 99, 114, 101, 116, 75, 101, 121};
    }
}
