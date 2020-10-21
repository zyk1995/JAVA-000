package week01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * 类加载器
 * 解开被处理过的类，并正确加载它
 *
 * @author wangjingru
 * @date 2020-10-21 22:02
 * @since 1.0.0
 */
public class HelloClassLoad extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoad().findClass("Hello");
            Object obj = helloClass.newInstance();
            Method method = helloClass.getMethod("hello");
            method.invoke(obj);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String xlassBase64Str  = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
        byte[] bytes = decode(xlassBase64Str);

        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] decode(String base64Str) {
        byte[] source = Base64.getDecoder().decode(base64Str);
        for (int i = 0; i < source.length; i++) {
            source[i] = (byte)(255 - source[i]);
        }
        return source;
    }

}
