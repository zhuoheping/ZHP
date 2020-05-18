package org.smart4j.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter3.helper.DatabaseHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.chapter3.service.CustomerService;

import junit.framework.Assert;

/**
 * 单元测试类
 * @author hp
 *
 */
public class CustomerServiceTest {
	private final CustomerService customerService;
	
	public CustomerServiceTest() {
		customerService = new CustomerService();
	}
	
	
	@Before
	public void init() throws IOException {
		//初始化数据库
		String file = "sql/customer_202004160859.sql";
		System.out.println("1");
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
		System.out.println("2");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String sql;
		System.out.println("3");
		StringBuffer sb = new StringBuffer();
		while((sql = reader.readLine()) != null) {
			sb.append(sql);
			System.out.println("40 = "+sb.toString());
		}
		
		
		DatabaseHelper.executeUpdate(sb.toString());
	}
	
	
	@Test
	public void getCustomerListTest() throws Exception{
		List<Customer> customerList = customerService.getCustomerList();
		System.out.println(customerList);
		Assert.assertEquals(2, customerList.size());
	}
	
	@Test 
	public void getCustomerTest() throws Exception{
		long id =1;
		Customer customer = customerService.getCustomer(id);
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void createCustomerTest() throws Exception{
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("name", "customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "13557997727");
		boolean result =customerService.createCustomer(fieldMap);
		System.out.println(result);
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void updateCustomerTest() throws Exception{
		long id = 1;
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("contact","Eric");
		boolean result = customerService.updateCustomer(id, fieldMap);
		System.out.println(result);
		Assert.assertTrue(result);
	}
	
	
	@Test
	public void deleteCustomerTest() throws Exception{
		long id = 1;
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
	
	
	
	
	
	
	
	
}
