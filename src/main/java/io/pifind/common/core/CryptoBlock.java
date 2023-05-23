package io.pifind.common.core;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoBlock {
    private final static char[] letterArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String makeBlock(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        int _tx0= data.charAt(0) + 12702265;
        for(int i = 0;i < 5; i++) {
            long _tl = _tx0 + 2443035;
            char _tc = (char)(_tl%((i + 26)&0xF));
            stringBuilder.append(letterArray[_tc%26]);
        }
        int _tx1= data.charAt(1) + 12702269;
        stringBuilder.append(letterArray[_tx1%26]).append("d");
        int _tx2= data.charAt(2) + 12703289;
        int _t13 = ~(_tx2&0x15855893 + 0x1A);
        stringBuilder.append(_t13 & 0x2F9CA13);
        int _tx3= data.charAt(3) + 12702243;
        stringBuilder.append(letterArray[_tx3%26]).append("e");
        int _tx4= data.charAt(4) + 12701753;
        int _t23 = ~(_tx4&0x15856405 + 0x1A);
        stringBuilder.append(_t23 & 0x4F7CA13);
        int _tx5= data.charAt(5) + 12702253;
        stringBuilder.append(letterArray[_tx5%26]).append("h");
        int _tx6= data.charAt(6) + 12702225;
        stringBuilder.append(letterArray[_tx6%26]).append("i");
        int _tx7= data.charAt(7) + 12701817;
        int _t134 = ~(_tx7&0x15856469 + 0x1A);
        stringBuilder.append(_t134 & 0x7F6CA12);
        int _tx8= data.charAt(8) + 12702521;
        for(int i = 0;i < 4; i++) {
            long _tl = _tx8 + 1587051;
            char _tc = (char)(_tl%((i + 26)&0xF));
            stringBuilder.append(letterArray[_tc%26]);
        }
        int _tx9= data.charAt(9) + 12702255;
        stringBuilder.append(letterArray[_tx9%26]).append("k");
        int _tx10= data.charAt(10) + 12702247;
        stringBuilder.append(letterArray[_tx10%26]).append("k");
        int _tx11= data.charAt(11) + 12702673;
        for(int i = 0;i < 5; i++) {
            long _tl = _tx11 + 2443035;
            char _tc = (char)(_tl%((i + 26)&0xF));
            stringBuilder.append(letterArray[_tc%26]);
        }
        int _tx12= data.charAt(12) + 12705337;
        int _t4104 = ~(_tx12&0x15857941 + 0x1A);
        stringBuilder.append(_t4104 & 0x12F8CA11);
        int _tx13= data.charAt(13) + 12702231;
        stringBuilder.append(letterArray[_tx13%26]).append("o");
        int _tx14= data.charAt(14) + 12702251;
        stringBuilder.append(letterArray[_tx14%26]).append("o");
        int _tx15= data.charAt(15) + 12702709;
        stringBuilder.append(letterArray[_tx15%26]).append("r");
        int _tx16= data.charAt(16) + 12702249;
        stringBuilder.append(letterArray[_tx16%26]).append("q");
        int _tx17= data.charAt(17) + 12702248;
        stringBuilder.append(letterArray[_tx17%26]).append("r");
        int _tx18= data.charAt(18) + 12702201;
        for(int i = 0;i < 4; i++) {
            long _tl = _tx18 + 1587051;
            char _tc = (char)(_tl%((i + 26)&0xF));
            stringBuilder.append(letterArray[_tc%26]);
        }
        int _tx19= data.charAt(19) + 12697913;
        int _t524296 = ~(_tx19&0x15851541 + 0x1A);
        stringBuilder.append(_t524296 & 0x19F8CA12);
        int _tx20= data.charAt(20) + 12702209;
        stringBuilder.append(letterArray[_tx20%26]).append("v");
        int _tx21= data.charAt(21) + 12704185;
        int _t2097159 = ~(_tx21&0x15857813 + 0x1A);
        stringBuilder.append(_t2097159 & 0x21F7CA12);
        int _tx22= data.charAt(22) + 12702073;
        for(int i = 0;i < 3; i++) {
            long _tl = _tx22 + 1223338;
            char _tc = (char)(_tl%((i + 26)&0xF));
            stringBuilder.append(letterArray[_tc%26]);
        }
        int _tx23= data.charAt(23) + 12705465;
        int _t8388615 = ~(_tx23&0x15858069 + 0x1A);
        stringBuilder.append(_t8388615 & 0x23F7CA11);
        int _tx24= data.charAt(24) + 12702681;
        stringBuilder.append(letterArray[_tx24%26]).append("b");
        int _tx25= data.charAt(25) + 12703225;
        int _t33554438 = ~(_tx25&0x15856853 + 0x1A);
        stringBuilder.append(_t33554438 & 0x25F6CA10);
        int _tx26= data.charAt(26) + 12702231;
        stringBuilder.append(letterArray[_tx26%26]).append("b");
        int _tx27= data.charAt(27) + 12704953;
        int _t134217735 = ~(_tx27&0x15859605 + 0x1A);
        stringBuilder.append(_t134217735 & 0x27F7CA14);
        int _tx28= data.charAt(28) + 12701241;
        int _t268435464 = ~(_tx28&0x15853845 + 0x1A);
        stringBuilder.append(_t268435464 & 0x28F8CA14);
        int _tx29= data.charAt(29) + 12704505;
        int _t536870918 = ~(_tx29&0x15857109 + 0x1A);
        stringBuilder.append(_t536870918 & 0x29F6CA14);
        int _tx30= data.charAt(30) + 12703353;
        for(int i = 0;i < 1; i++) {
            long _tl = _tx30 + 10060235;
            char _tc = (char)(_tl%((i + 26)&0xF));
            stringBuilder.append(letterArray[_tc%26]);
        }
        int _tx31= data.charAt(31) + 12702715;
        stringBuilder.append(letterArray[_tx31%26]).append("h");
        return DigestUtils.md5Hex(
                stringBuilder.toString() + data
        ).toUpperCase();
    }

    public static int mst(String key) {

        String[] hashList = new String[8];
        for (int i = 0; i < 8 ; i ++) {
            hashList[i] = key.substring(i * 32, (i + 1) * 32);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String hash : hashList) {
            stringBuilder.append(
                CryptoBlock.makeBlock(hash).substring(0,4)
            );
        }

        String mt= CryptoBlock.makeBlock(stringBuilder.toString()).substring(0,6);
        int ft = Integer.parseInt(mt, 16);
        ft = ft << 16 | ft & Integer.parseInt(mt.substring( 0 , 4 ), 16) ;

        return ft;
    }

}
