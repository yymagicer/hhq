package com.hhq.common.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码工具类
 */
public class SecretUtils {

    private static final int HASH_ITERATION_TIMES = 1024;

    public static String generateStoredPwd(String salt, String pwd) {
        return new Sha256Hash(pwd, salt, HASH_ITERATION_TIMES).toHex();
    }
    public static String getSalt(){
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        ByteSource byteSource = secureRandomNumberGenerator.nextBytes();
        return byteSource.toHex();
    }

    public static void main(String[] args) {
        String salt = SecretUtils.getSalt();
        System.out.println("salt="+salt);
        String secret = SecretUtils.generateStoredPwd("d45962101cbe925571b69d3521839a09", "123456");
        System.out.println("secret="+secret);
    }
}
