package org.smart4j.chapter3;

 
public class Test {

	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
		stringLocal.set("主线程");
	}
	
	public long getLong() {
		return longLocal.get();
	}
	
	public String getString() {
		return stringLocal.get();
	}
	
	public static void main(String[] args) throws InterruptedException {

		
		final Test test = new Test();
		test.set();
		System.out.println(test.getString());	//线程名字
	}

}






















