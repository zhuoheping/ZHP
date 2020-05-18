package org.smart4j.chapter3.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.util.CollectionUtil;
import org.smart4j.chapter3.util.PropsUtil;


/**
 * 数据库操作助手类
 * @author hp
 *
 */
public class DatabaseHelper {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner QUERY_RUNNER;
	/**
	 * 为了确保一个线程中只有一个connction  我们恶意使用ThreadLocal 来存放本地线程变量 。
	 */
	private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	private static final BasicDataSource DATA_SOURCE;
	
	

	
	/**
	 * 获取配置
	 */
	static {
		CONNECTION_HOLDER = new ThreadLocal<Connection>();
		QUERY_RUNNER = new QueryRunner();
		
		Properties conf = PropsUtil.loadProps("config.properties");
		String driver=conf.getProperty("jdbc.driver");
		System.out.println(driver);
		String username=conf.getProperty("jdbc.username");
		String url=conf.getProperty("jdbc.url");
		String password=conf.getProperty("jdbc.password");
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}
	

	
	/**
	 * 获取数据库的连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn= CONNECTION_HOLDER.get();
		
		if(conn == null ) {
			try {
				conn=DATA_SOURCE.getConnection();
				
			} catch (SQLException e) {
				LOGGER.error("can not load jdbc driver",e);
				throw new RuntimeException(e);
			}finally {
				CONNECTION_HOLDER.set(conn);
			}
			
		}
		
		
		
		return conn;
	}
	
	/**
	 * 关闭数据库连接     ----------使用连接池技术后就不用管它关闭的问题
	 * @param conn
	 */
/*	public static void closeConnection() {
		Connection conn= CONNECTION_HOLDER.get();
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure",e);
				throw new RuntimeException(e);
			}finally {
				CONNECTION_HOLDER.remove();
			}
	}
	*/
	/**
	 * 查询列表实体类封装
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object...params){
		Connection conn = getConnection();
		List<T> entityList;
		try {
			entityList=QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass),params);
		}catch(SQLException e) {
			LOGGER.error("query entity list failure",e);
			throw new RuntimeException(e);
		}
		
		
		return entityList;
	}
	
	/**
	 * 查询单个实体
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass,String sql,Object...params){
		T entity = null;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn,sql,new BeanHandler<T> (entityClass),params);
		}catch(SQLException e) {
			LOGGER.error("query entity list failure",e);
			throw new RuntimeException(e);
		}
		
		return entity;
	}
	
	/**
	 * 多表查询返回listMap
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String,Object>> executeQuery(String sql,Object...params){
		List<Map<String,Object>> result;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER.query(conn, sql, new MapListHandler(),params);
		}catch(Exception e) {
			LOGGER.error("query entity list failure",e);
			throw new RuntimeException(e);
		}
		
		
		return result;
	}
	
	/**
	 * 执行更新语句（包括update, insert,delete） 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql,Object...params) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows=QUERY_RUNNER.update(conn,sql,params);
		}catch(SQLException e) {
			LOGGER.error("execute update failure", e);
			throw new RuntimeException(e);
		}
		return rows;
	}
	
	
	/**
	 * 插入实体
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap) {
		if(CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity: fieldMap id empty");
			return false;
		}
		
		String sql = "insert into "+ getTableName(entityClass);
		StringBuilder columns=new StringBuilder("(");
		StringBuilder values=new StringBuilder("(");
		for(String fieldName:fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(),")");
		sql +=columns+" values " + values;
		
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql,params) == 1;
	}
	
	
	/**
	 * 根据id 删除实体
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass,long id) {
		String sql = "delete from "+ getTableName(entityClass) + " where id=?";
		return executeUpdate(sql,id) == 1;
	}
	
	/**
	 * 更新实体
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEnty(Class<T> entityClass,long id, Map<String,Object> fieldMap) {
		if(CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity : fieldMap id empty");
			return false;
		}
		
		String sql = "update "+ getTableName(entityClass) + " set ";
		StringBuilder columns = new StringBuilder();
		for(String fieldName: fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		
		sql +=columns.substring(0, columns.lastIndexOf(", ")) + " where id=?";
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		
		return executeUpdate(sql,params) ==1 ;
	}
	
	
	
	
	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}
	
	
	
	
	public static void main(String[] args) {
		
		String sql="";
		StringBuilder sb = new StringBuilder();
		sb.append("12346789");
		sql=sb.substring(2);
		
	
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list = new ArrayList<Object>(); 
		
		map.put("name", "张三");
		map.put("age", 12);
		list.addAll(map.values());
		Object[] params = list.toArray();
		System.out.println(map.values());
		for(Object obj:params) {
			System.out.println(obj);
		}
		
		
		//updateEnty(String.class,1,map);
	}
}
