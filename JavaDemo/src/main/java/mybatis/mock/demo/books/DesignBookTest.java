package mybatis.mock.demo.books;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.cms.OtherRecipientInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.util.NumberUtils;

import java.io.Console;
import java.util.Objects;
import java.util.Scanner;

/**
 * 面向对象语言实现一个计算器控制台程序，要求输入两个数和运算符号，得到结果。
 *
 * @date: 2022/4/20
 * @author: yao
 */
@Slf4j
public class DesignBookTest {

    @Test
    public void simpleDemo() {
        log.info("输入一个数字");
        Scanner scanner = new Scanner(System.in);
        int strNumA = scanner.nextInt();
        log.info("输入运算符");
        String sign = scanner.next();
        log.info("输入第三个数字");
        int strNumB = scanner.nextInt();
        String result = "";
        if (Objects.equals(sign, "+")) {
            result = String.valueOf(strNumA + strNumB);
        }
        if (Objects.equals(sign, "-")) {
            result = String.valueOf(strNumA - strNumB);
        }
        if (Objects.equals(sign, "*")) {
            result = String.valueOf(strNumA * strNumB);
        }
        if (Objects.equals(sign, "/")) {
            result = String.valueOf(strNumA / strNumB);
        }
        log.info("{}", result);
    }

    @Test
    public void simpleCaseDemo() throws Exception {
        log.info("输入一个数字");
        Scanner scanner = new Scanner(System.in);
        int strNumA = scanner.nextInt();
        log.info("输入运算符");
        String sign = scanner.next();
        log.info("输入第三个数字");
        int strNumB = scanner.nextInt();
        String result = "";
        switch (sign) {
            case "+": {
                result = String.valueOf(strNumA + strNumB);
                break;
            }
            case "-": {
                result = String.valueOf(strNumA - strNumB);
                break;
            }
            case "*": {
                result = String.valueOf(strNumA * strNumB);
                break;
            }
            case "/": {
                if (strNumB != 0) {
                    result = String.valueOf(strNumA / strNumB);
                } else {
                    throw new Exception("除数不能为0");
                }
                break;
            }
        }
    }
}
