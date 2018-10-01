/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.converter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ioliveira
 */
public abstract class MD5 {

    private static final int HEXADECIMAL = 16;
    private static final int POSITIVE = 1;
    private static final int POSICAO_INICIO = 0;

    public MD5() {
    }

    public static String generateMD5Hashing(String input) {

        String md5 = null;

        if (input != null) {
            try {

                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(input.getBytes(), POSICAO_INICIO, input.length());
                md5 = new BigInteger(POSITIVE, digest.digest()).toString(HEXADECIMAL);

            } catch (NoSuchAlgorithmException e) {
            }
        }
        return md5;
    }
}
