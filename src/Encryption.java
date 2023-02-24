import java.math.BigInteger;
import java.security.MessageDigest;

public class Encryption {
    public static String encrypt(String source) {
        String md5;
        try {
            MessageDigest md5Encryption = MessageDigest.getInstance("MD5");
            md5Encryption.update(source.getBytes(), 0, source.length());
            md5 = new BigInteger(1, md5Encryption.digest()).toString(16);
        } catch (Exception ex) {
            return null;
        }
        return md5;
    }
}
