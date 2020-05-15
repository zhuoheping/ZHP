package test.test1.a;


public class Main {

	public static void main(String[] args) throws Exception {

		
	Hello  hello = new HelloImpl();
	
	DynamicProxy dynamidProxy = new DynamicProxy(hello);
	
	Hello helloProxy = dynamidProxy.getProxy();
		
	helloProxy.say2("´úÀí");
	
	
	
	}

	

	
}
