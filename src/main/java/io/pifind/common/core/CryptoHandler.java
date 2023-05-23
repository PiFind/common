package io.pifind.common.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CryptoHandler {

    @Value("${spring.application.name}")
    private String applicationName;

    private Integer mst;

    private int initMst(String key) {
        if (mst == null) {
            mst = CryptoBlock.mst(key);
        }
        return mst;
    }

    public Integer standardize(Integer code) {

        String key =
                "74AA0906D8E632903105C956F62136C86EF98DAC6E3300A2963EF4D9A2810" +
                "0497496324EB1EE9DE363132A1B7F0604E6A68FD0F9C70FE805C5A1EB0C6C" +
                "EDE300CEF4FB16E9D31B8FE9DCA0787CA260BE1D2A8BFD2868CE7D8C02714" +
                "176158A9A48CE18FDC51A694A21B708F6D1D056CD2926E74EAC336E293B66" +
                "D39549A69A05";

        int s = initMst(key);
        int res = (code & s) | (~ s & code);
        return code & res;

    }

    private static byte[] getBytes(String s) {
        return getBytes(new byte[]{},s);
    }

    private static byte[] getBytes(byte[] prefixBytes ,String s) {

        char[] chars = s.toCharArray();
        byte[] bytes = new byte[prefixBytes.length + chars.length/2];

        // 复制前缀字节
        for (int i = 0; i < prefixBytes.length; i ++) {
            bytes[i] = prefixBytes[i];
        }

        // 计算后续字节
        int start = prefixBytes.length;
        for (int i = 0; i < chars.length/2 ; i ++) {
            byte bH = toByte(chars[2*i]);
            byte bL = toByte(chars[2*i + 1]);
            bytes[start + i] = (byte) ((bH << 4) | bL);
        }

        return bytes;
    }

    static byte toByte(char ch) {
        switch (ch){
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return  (byte) (ch - '0');
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                return (byte) (10 + ch - 'a');
        }
        return 0;
    }

}
