package io.pifind.common.util;


import org.junit.jupiter.api.Test;

public class StringFormatUtilsTest {

    @Test
    public void testCreateRegularExpressionByFormatString() {

        String strFs = "测试%7.6S字符串";
        System.out.printf(strFs,"ab+AD-12号");
        System.out.println();
        System.out.println(StringFormatUtils.createRegularExpressionByFormatString(strFs));

        String boolFs = "测试%5.6b布尔";
        System.out.printf(boolFs,true);
        System.out.println();
        System.out.println(StringFormatUtils.createRegularExpressionByFormatString(boolFs));

        String intFs = "测试%(, 35d整数";
        System.out.printf(intFs,-1225564983);
        System.out.println();
        System.out.println(StringFormatUtils.createRegularExpressionByFormatString(intFs));

        String hexFs = "测试%#012x十六进制整数";
        System.out.printf(hexFs,-1250);
        System.out.println();
        System.out.println(StringFormatUtils.createRegularExpressionByFormatString(hexFs));

        String octFs = "测试%#12o八进制整数";
        System.out.printf(octFs,0123451234);
        System.out.println();
        System.out.println(StringFormatUtils.createRegularExpressionByFormatString(octFs));

//        String floatFs = "测试%8.10f浮点数";
//        System.out.printf(floatFs,-2023.1415926);
//        System.out.println();
//        System.out.println(StringFormatUtils.createRegularExpressionByFormatString(floatFs));

    }

}
