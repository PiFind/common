package io.pifind.common.util;

import io.pifind.common.annotation.QueryObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * URI拼接类测试
 * <p>
 *     因为测试的是URI拼接，所以在测试类中定义了一个用于请求的实体类{@link MD5AppliedObject}
 *     和一个用于测试的该实体类的对象 {@link UriSplicedUtilsTest#defaultTestObject}
 * </p>
 */
@Slf4j
@SpringBootTest
public class UriSplicedUtilsTest {

    /**
     * 这里使用的API是MD5加密的API，详情参考:
     * <a href="https://api.aa1.cn/doc/api-md5.html"> 天气API </a>
     * 对应的参数请求对象是{@link MD5AppliedObject}
     * @see MD5AppliedObject
     */
    private static final String TEST_API = "https://v.api.aa1.cn/api/api-md5/go.php";

    /**
     * 这是测试使用的一个请求对象
     */
    private static final MD5AppliedObject defaultTestObject;
    static {
        defaultTestObject = new MD5AppliedObject();
        defaultTestObject.setAct("加密");
        defaultTestObject.setMd5("123456");
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试普通的拼装
     * @see UriSplicedUtils#spliceToString(String, Object)
     */
    @Test
    public void testSplice() {
        String uri = UriSplicedUtils.spliceToString(TEST_API, defaultTestObject);
        String result = restTemplate.getForObject(uri, String.class);
        if (result == null) {
            throw new RuntimeException("未能成功获取加密结果");
        } else {
            log.info("测试通过，{}",result);
        }
    }

    /**
     * 通过 QueryObject 注解的实体类进行自动拼接的测试
     * @see UriSplicedUtils#spliceToString(Object)
     */
    @Test
    public void testSpliceByModel() {
        String uri = UriSplicedUtils.spliceToString(defaultTestObject);
        String result = restTemplate.getForObject(uri, String.class);
        if (result == null) {
            throw new RuntimeException("未能成功获取加密结果");
        } else {
            log.info("测试通过，{}",result);
        }
    }

    @Data
    @QueryObject(TEST_API)
    public static class MD5AppliedObject {

        /**
         * 请求MD5加解密类型:
         * <ul>
         *     <li>加密</li>
         *     <li>解密</li>
         * </ul>
         */
        private String act;

        /**
         * 输文字或加密后的代码
         */
        private String md5;

    }

}