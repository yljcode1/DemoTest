package com.yao.hsqldb.util;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public class IOUtils {
    public static void closeQuietly(AutoCloseable ... con) {
        for (AutoCloseable autoCloseable : con) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
