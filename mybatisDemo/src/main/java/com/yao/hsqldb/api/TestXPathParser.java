package com.yao.hsqldb.api;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

/**
 * @author xiaoK
 * @date 2021/11/26
 */
public class TestXPathParser {
    @Test
    public void testXPath() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        InputStream in = Resources.getResourceAsStream("user.xml");
        Document parse = documentBuilder.parse(in);
        XPath xPath = XPathFactory.newInstance().newXPath();

    }
}
