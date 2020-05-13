package org.smart4j.chapter3.comtroller;

import java.util.List;
import java.util.Map;

import org.smart4j.chapter3.model.Customer;
import org.smart4j.chapter3.service.CustomerService;
import org.smart4j.smart_framework.annotation.Action;
import org.smart4j.smart_framework.annotation.Controller;
import org.smart4j.smart_framework.annotation.Inject;
import org.smart4j.smart_framework.bean.Data;
import org.smart4j.smart_framework.bean.Param;
import org.smart4j.smart_framework.bean.View;


/**
 * ����ͻ������������
 * @author hp
 *
 */
@Controller
public class CustomerController {

	
	@Inject
	private CustomerService customerService;
	
	
	/**
	 * ����ͻ��б����
	 */
	@Action("get:/customer")
	public View index(Param param) {
		List<Customer> customerList = customerService.getCustomerList();
		return new View("customer.jsp").addModel("customerList", customerList);
	}
	
	
	/**
	 * ��ʾ�ͻ�������Ϣ
	 */
	@Action("get:/customer_show")
	public View show(Param param) {
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_show.jsp").addModel("customer", customer);
	}
	
	/**
	 *���봴���ͻ�����
	 */
	@Action("get:/customer_create")
	public View create(Param param) {
		return new View("customer_create.jsp");
	}
	
	/**
	 * �������ͻ����� 
	 */
	@Action("post:/customer_create")
	public Data createSubmit(Param param) {
		System.out.println("63="+param.getMap());
		Map<String,Object> fieldMap = param.getMap();
		boolean result = customerService.createCustomer(fieldMap);
		return new Data(result);
	}
	
	
	/**
	 * ����༭�ͻ�����
	 */
	@Action("get:/customer_edit")
	public View edit(Param param) {
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("costomer_edit.jsp").addModel("customer", customer);
	}
	
	/**
	 * ����༭�ͻ�����
	 */
	@Action("put:/customer_edit")
	public Data editSubmit(Param param) {
		long id = param.getLong("id");
		Map<String,Object> fieldMap = param.getMap();
		boolean result = customerService.updateCustomer(id, fieldMap);
		return new Data(result);
	}
	
	/**
	 * ����ɾ���ͻ�����
	 */
	@Action("delete:/customer_edit")
	public Data delete(Param param) {
		long id = param.getLong("id");
		boolean result = customerService.deleteCustomer(id);
		return new Data(result);
	}
	
	
}
