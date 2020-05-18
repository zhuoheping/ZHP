package test.test1.a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author hp
 *
 */
public class DynamicProxy implements InvocationHandler {

	Object obj;

	public DynamicProxy(Object obj) {
		this.obj = obj; 
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(obj, args);
		after();
		
		return result;
	}
	
	private void before() {
		System.out.println("之前");
	}
	
	private void after() {
		System.out.println("之后");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

}
