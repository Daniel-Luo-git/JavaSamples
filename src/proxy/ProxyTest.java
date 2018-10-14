package proxy;

import java.lang.reflect.*;
import java.util.*;

/**
 * This program demonstrates the use of proxies.
 */
public class ProxyTest
{
   public static void main(String[] args)
   {
      Object[] elements = new Object[1000];

      // 生成1000个Integer的代理类加入数组中
      for (int i = 0; i < elements.length; i++)
      {
         Integer value = i + 1;
         InvocationHandler handler = new TraceHandler(value);
         Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class } , handler);
         elements[i] = proxy;
      }

      // 构造一个随机整数key
      Integer key = new Random().nextInt(elements.length) + 1;

      // 使用binarySearch查找该key
      int result = Arrays.binarySearch(elements, key);

      // 打印查找到的key值（如果找到）
      if (result >= 0) System.out.println(elements[result]);
   }
}

/**
 * 该调用处理器打印方法的参数（包含隐含参数）以及方法名
 * 并调用被代理对象的方法
 */
class TraceHandler implements InvocationHandler
{
   private Object target;

   /**
    * 构造器方法
    * @param target 是被调用方法的隐含参数
    */
   public TraceHandler(Object t)
   {
      target = t;
   }

   public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
   {
      // 打印隐含参数
      System.out.print(target);
      // 打印方法名
      System.out.print("." + m.getName() + "(");
      // 打印显式参数
      if (args != null)
      {
         for (int i = 0; i < args.length; i++)
         {
            System.out.print(args[i]);
            if (i < args.length - 1) System.out.print(", ");
         }
      }
      System.out.println(")");

      // 执行实际的方法
      return m.invoke(target, args);
   }
}
