package util;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;
import java.util.UUID;

public class AppUtils {

    private static PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence charSequence) {
            return null;
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return false;
        }
    };

    private final static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"
    };

    /**
     * 随机生成appkey
     */
    public static String getAppKey() {

        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    /**
     * 将appkey加密后当做秘钥
     */
    public static String getAppSecret(String appId) {
        return passwordEncoder.encode(appId + System.currentTimeMillis());
    }

    /**
     * 生成8位数的 字母 大小随机 当做授权码
     */
    public static String getCode() {
        Random r = new Random();
        String code = "";
        for (int i = 0; i < 8; ++i) {
            int temp = r.nextInt(52);
            char x = (char) (temp < 26 ? temp + 97 : (temp % 26) + 65);
            code += x;
        }
        return code;
    }
    
    
    /*public static void main(String[] args) {
        String appId = getAppKey();
        String appSecret = getAppSecret(appId);
        System.out.println("appId: "+appId);
        System.out.println("appSecret: "+appSecret);
    }*/
}