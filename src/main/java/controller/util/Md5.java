package controller.util;


import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class for decrypt from md5
 * used for hashing and check passwords from/to db
 */
public class Md5 {
    private final static Logger logger = Logger.getLogger(Md5.class);

    public static boolean matching(String orig, String compare){
        logger.info("compare:"+compare +" vs "+orig+" result: "+convertToMd5(compare).equals(orig));
            return convertToMd5(compare).equals(orig);
    }
    public static String convertToMd5(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        logger.info("password: "+password+" password in Md5:"+hashtext);
        return hashtext;
    }
}