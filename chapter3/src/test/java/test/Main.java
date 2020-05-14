package test;

import java.lang.reflect.Field;

public class Main {

	public static void main(String[] args) throws Exception {

		Student stu = new Student();
		
		 Class clazz = Class.forName("test.Student");
		
		
		 Field f = clazz.getDeclaredField("name");
         
         
         System.out.println("×Ö¶Î:"+f);
         System.out.println("¶ÔÏó:"+stu);
         System.out.println("Öµ:"+"zhangsan");
	}

}
