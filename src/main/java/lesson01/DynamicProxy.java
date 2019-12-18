package lesson01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态代理。
 * 我不会动态代理，那就补知识点
 * classloader是啥？
 * invocationHandler是干嘛的？
 * invoke里面的参数又是干嘛的？
 * <p>
 * 为mybatis打基础
 */
public class DynamicProxy {
    public static void main(String[] args) {
        Student student = (Student) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class<?>[]{Student.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Map<String, Object> nameAndValue = argNameAndValue(method, args);
                System.out.println(nameAndValue);
                return 0;
            }
        });

        student.exam(1);
    }

    public static Map<String, Object> argNameAndValue(Method method, Object[] args) {
        HashMap<String, Object> nameAndValue = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        int[] index = {0};
        Arrays.asList(parameters).forEach(parameter -> {
            // 这注意看 parameter.getName() 源码注释 为什么得到的名字是 argN 呢。看源码注释结合百度
            nameAndValue.put(parameter.getName(), args[index[0]]);
            index[0]++;
        });
        return nameAndValue;
    }
}

interface Student {
    int exam(Integer id);
}