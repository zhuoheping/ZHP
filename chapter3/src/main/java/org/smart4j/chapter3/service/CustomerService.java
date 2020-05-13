package org.smart4j.chapter3.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.helper.DatabaseHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.smart_framework.annotation.Service;



/**
 * �ṩ�ͻ����ݷ���
 * @author hp
 *
 */
@Service
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	
	/**
	 * ��ȡ�ͻ��б�
	 * @param keyword
	 * @return
	 */
	public List<Customer> getCustomerList(){
			String sql = "select * from customer";
			List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class,sql);
			return customerList;
		
	}
	
	/**
	 * ��ȡ�ͻ�
	 * @param id
	 * @return
	 */
	public Customer getCustomer(long id) {
		
		return null;
	}
	
	/**
	 * ���¿ͻ�
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public boolean updateCustomer(long id,Map<String,Object> fieldMap) {
		
		return DatabaseHelper.updateEnty(Customer.class,id,fieldMap);
	}
	
	/**
	 * ɾ���ͻ�
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(long id) {
		
		return DatabaseHelper.deleteEntity(Customer.class,id);
	}
	
	/**
	 * �����ͻ�
	 * @param fieldMap
	 * @return
	 */
	public boolean createCustomer(Map<String,Object> fieldMap) {
							  
		return DatabaseHelper.insertEntity(Customer.class,fieldMap);
	}
}
