package org.smart4j.chapter3.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.helper.DatabaseHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.smart_framework.annotation.Service;



/**
 * 提供客户数据服务
 * @author hp
 *
 */
@Service
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	
	/**
	 * 获取客户列表
	 * @param keyword
	 * @return
	 */
	public List<Customer> getCustomerList(){
			String sql = "select * from customer";
			List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class,sql);
			return customerList;
		
	}
	
	/**
	 * 获取客户
	 * @param id
	 * @return
	 */
	public Customer getCustomer(long id) {
		
		return null;
	}
	
	/**
	 * 更新客户
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public boolean updateCustomer(long id,Map<String,Object> fieldMap) {
		
		return DatabaseHelper.updateEnty(Customer.class,id,fieldMap);
	}
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(long id) {
		
		return DatabaseHelper.deleteEntity(Customer.class,id);
	}
	
	/**
	 * 创建客户
	 * @param fieldMap
	 * @return
	 */
	public boolean createCustomer(Map<String,Object> fieldMap) {
							  
		return DatabaseHelper.insertEntity(Customer.class,fieldMap);
	}
}
