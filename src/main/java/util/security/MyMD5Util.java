package util.security;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/26 16:58
 * @File : myMd5
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/26 16:58
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMD5Util {

    private static Logger logger = LoggerFactory.getLogger("Sync");

    public static String MD5(String srcStr, int length) {
        String md5 = null;
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(srcStr.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            logger.error("exception",e);
        }
        if (length == 16) {
            md5 = buf.toString().substring(8, 24);
        } else if (length == 32) {
            md5 = buf.toString();
        }
        return md5;
    }

    public static void main(String[] args) {
        System.out.println(MyMD5Util.MD5("1234", 32).toUpperCase());
    }
}