package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new UserHandler(userService);
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHandler);/*生成一个代理对象，参数：一个类加载器、
                class数组（每个元素就是需要实现的接口）以及调用处理器
*/
        System.out.println("userName:"+userServiceProxy.getName());
        System.out.println("age:"+userServiceProxy.getAge());

	}

}

class UserHandler implements InvocationHandler
{
	private Object target;
	
	

	public UserHandler(Object target) {
		super();
		this.target = target;
	}
	
	

	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {

		Object result=null;
		if("getName".equals(method.getName())){
            System.out.println("++++++before " + method.getName() + "++++++");//方法执行前
            System.out.println(result);
            result = method.invoke(target, args);//执行实际方法
            System.out.println("++++++after " + method.getName() + "++++++");//方法执行后
            return result;
        }else{
            result = method.invoke(target, args);
            return result;
        }

	}
	
}
