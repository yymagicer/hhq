
package com.hhq.codegeneration.util;

import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.common.util.HumpUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 */
public class DBUtil {
	private static String url;
	private static String user;
	private static String password;
	private static String driver_name;

	/**
	 * 基本查询语句
	 */
	public static final String SQL="select * from information_schema.COLUMNS";
	
	private static Connection conn ;

	public DBUtil(String url, String user, String password, String driver_name) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver_name = driver_name;
	}

	public static DBUtil build(DataBaseConfigPO dataBaseConfigPO){
		return new DBUtil(dataBaseConfigPO.getUrl(),dataBaseConfigPO.getUserName(),dataBaseConfigPO.getPassword(),dataBaseConfigPO.getDriverName());
	}
	
	public  static Connection getDBConn() throws SQLException, ClassNotFoundException{
		if(null == conn || conn.isClosed()){
			Class.forName(driver_name);
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;
	}
	public static void close(){
		if(null != conn){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取sql查询的结果
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List getSqlData(String sql, Class clazz,boolean isTransferHump) throws SQLException, ClassNotFoundException{
		List populate = null;
		try{
			PreparedStatement pstate =  getDBConn().prepareStatement(sql);
			ResultSet results = pstate.executeQuery();
			populate = populate(results, clazz,isTransferHump);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return populate;
	}
	/**
	 * 将rs结果转换成对象列表
	 * @param rs jdbc结果集
	 * @param clazz 对象的映射类
	 * @return 封装了对象的结果列表
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static List populate(ResultSet rs , Class clazz,boolean isTransferHump) throws SQLException, InstantiationException, IllegalAccessException {
		//结果集的元素对象
		ResultSetMetaData rsmd = rs.getMetaData();
		//获取结果集的元素个数
		int colCount = rsmd.getColumnCount();
		//返回结果的列表集合
		List list = new ArrayList();
		//业务对象的属性数组
		Field[] fields = clazz.getDeclaredFields();
		while (rs.next()) {//对每一条记录进行操作
			Object obj = clazz.newInstance();//构造业务对象实体
			//将每一个字段取出进行赋值
			for (int i = 1; i <= colCount; i++) {
				Object value = rs.getObject(i);
				//寻找该列对应的对象属性
				for (int j = 0; j < fields.length; j++) {
					Field f = fields[j];
					//如果匹配进行赋值
					if (f.getName().equalsIgnoreCase(getColumnName(isTransferHump,rsmd.getColumnName(i)))) {
						boolean flag = f.isAccessible();
						f.setAccessible(true);
						f.set(obj, transferTOJavaType(value,f));
						f.setAccessible(flag);
					}
				}
			}
			list.add(obj);
		}
		return list;
	}

	public  static Object  transferTOJavaType(Object dataObject,Field field){
		if(dataObject instanceof  BigInteger){
			if(field.getType()==Integer.class){
				return ((BigInteger) dataObject).intValue();
			}
		}
		return dataObject;
	}
	public static String getColumnName(boolean isTransferHump,String name){
		if(isTransferHump){
		return	HumpUtils.lineToHump(name);
		}
		return name;
	}
}

