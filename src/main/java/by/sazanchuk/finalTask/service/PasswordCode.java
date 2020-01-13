package by.sazanchuk.finalTask.service;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * The type Password code.
 */
public class PasswordCode {
    /**
     * Code md 5 string.
     *
     * @param st the st
     * @return the string
     */
    public static String CodeMD5(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }


}
