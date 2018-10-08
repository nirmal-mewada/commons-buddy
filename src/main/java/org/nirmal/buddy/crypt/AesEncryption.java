package org.nirmal.buddy.crypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES encryption utility to encryption and decryption which is compatible with Magento Front end
 * @author nirmal.s
 *
 */
public class AesEncryption {

    private static String CIPHER = "AES/CBC/PKCS5Padding";

    private static String CHAR_SET_UTF8 = "UTF-8";

    private static String AES = "AES";

    /**
     * PHP compatible iv
     */
    public static byte[] ivBytes = {
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };


    /**
     * Decrypts data for provided key. Encrypted data has to be encoded in Base64.
     * @param data to be decrypted
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(final String data, final String key)	throws InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
    NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(CHAR_SET_UTF8), AES);
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(bytes, CHAR_SET_UTF8);
    }

    /**
     * Encrypts data with provided key. Returns base 64 encoded string of encrypted data.
     * @param data to be encrypted
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(final String data, final String key)
            throws InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(CHAR_SET_UTF8), AES);
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        byte[] dataBytes = Base64.getEncoder().encode(cipher.doFinal(data.getBytes(CHAR_SET_UTF8)));
        return new String(dataBytes, CHAR_SET_UTF8);
    }

}