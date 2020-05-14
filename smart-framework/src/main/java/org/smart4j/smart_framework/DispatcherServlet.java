package org.smart4j.smart_framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart4j.smart_framework.bean.Data;
import org.smart4j.smart_framework.bean.Handler;
import org.smart4j.smart_framework.bean.Param;
import org.smart4j.smart_framework.bean.View;
import org.smart4j.smart_framework.helper.BeanHelper;
import org.smart4j.smart_framework.helper.ClassHelper;
import org.smart4j.smart_framework.helper.ConfigHelper;
import org.smart4j.smart_framework.helper.ControllerHelper;
import org.smart4j.smart_framework.util.ArrayUtil;
import org.smart4j.smart_framework.util.CodecUtil;
import org.smart4j.smart_framework.util.JsonUtil;
import org.smart4j.smart_framework.util.ReflectionUtil;
import org.smart4j.smart_framework.util.StreamUtil;
import org.smart4j.smart_framework.util.StringUtil;

/**
 * 请求转发控制器
 * @author admin
 *
 */
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		//初始化相关Helper 类
		HelperLoader.init();   //当前类  ， 加载class 和管理bean 和ioc 注入 ，映射关系。
		//获取ServletContext 对象（用于注册Servlet）
		ServletContext servletContext = servletConfig.getServletContext();
		//注册处理JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		//注册处理静态资源的默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Set<Class<?>> clazz = ClassHelper.getClassSet();
		int len = clazz.size();
		
		//获取请求方法与请求路径
		String requestPath = request.getPathInfo();
		String requestMethod = request.getMethod().toLowerCase();
		
		//获取Action 处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath); 
		
		if(handler != null){
			//获取Controller 类及其Bean 实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			//创建请求参数对象
			Map<String,Object> paramMap = new HashMap<String,Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			
			while(paramNames.hasMoreElements()){
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtil.isNotEmpty(body)){
				String[] params = StringUtil.splitString(body, "&");
				if(ArrayUtil.isNotEmpty(params)){
					for(String param:params){
						String[] array = StringUtil.splitString(param, "=");
						if(ArrayUtil.isNotEmpty(array) && array.length == 2){
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			
			Param param = new Param(paramMap);
			//调用Action 方法
			Method actionMethod = handler.getActionMethod();
			Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			//处理Action 方法返回值
			if(result instanceof View){
				//返回JSP 页面
				View view = (View) result;
				String path = view.getPath();
				if(StringUtil.isNotEmpty(path)){
					if(path.startsWith("/")){
						response.sendRedirect(request.getContextPath()+path);
					}else{
						Map<String,Object> model = view.getModel();
						for(Map.Entry<String, Object> entry:model.entrySet()){
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request,response);
					}
				}
				
			}else if(result instanceof Data){
				//返回JSON 数据
				Data data =(Data) result;
				Object model = data.getModel();
				if(model != null){
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
			
		}
		
		
	}

	

	
	
}
