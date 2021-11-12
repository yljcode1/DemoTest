package mybatis.mock.demo.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * @author xiaoK
 * @date 2021/11/12
 */
public class ClassLoaderWrapper {
    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    public ClassLoaderWrapper() {
        try {
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ignored) {

        }
    }

    public URL getResourceAsURL(String resource) {
        return getResourceAsURL(resource, getClassLoaders(null));
    }


    public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
        return getResourceAsInputStream(resource, getClassLoaders(classLoader));
    }

    public InputStream getResourceAsInputStream(String resource){
    return     getResourceAsInputStream(resource,getClassLoaders(null));
    }
    public InputStream getResourceAsInputStream(String resource, ClassLoader[] classLoaders) {
        for (ClassLoader cl : classLoaders) {
            if (null != cl) {
                InputStream returnValue = cl.getResourceAsStream(resource);
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }
                if (null != returnValue) {
                    return returnValue;
                }
            }
        }
        return null;
    }

    public URL getResourceAsURL(String resource, ClassLoader[] classLoader) {
        URL url;
        for (ClassLoader cl : classLoader) {
            if (null!=cl){
                url = cl.getResource(resource);
                if (null == url) {
                    url = cl.getResource("/" + resource);
                }
                if (null != url) {
                    return url;
                }
            }

        }
        return null;
    }


    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader, defaultClassLoader, Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(), systemClassLoader
        };
    }

    public static void main(String[] args) throws IOException {
        ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();
        URL resourceAsURL = classLoaderWrapper.getResourceAsURL("hello.xml");
        InputStream resourceAsInputStream = classLoaderWrapper.getResourceAsInputStream("hello.xml");
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String collect = bufferedReader.lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(collect);
        System.out.println(resourceAsInputStream);
        System.out.println(resourceAsURL);
    }
}
