package com.nciae.empsys.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	// 连接数据库
	private static Connection getConn() {
		try {
			// 使用properties文件存储四个参数
			Properties properties = new Properties();
			InputStream in = JDBCUtils.class.getClassLoader()
					.getResourceAsStream("com/nciae/empsys/res/sqldb.properties");
			properties.load(in);
			in.close();
			Class.forName(properties.getProperty("driver"));
			return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("pass"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 执行数据库的添加、删除和修改
	public static boolean executeDDL(String sql) {
		try {
			Statement stm = getConn().createStatement();
			int count = stm.executeUpdate(sql);
			if (count > 0)
				return true;
			else
				return false;
		} catch (Exception ee) {
			ee.printStackTrace();
			return false;
		}
	}

	// 查询数据库的操作
	public static ResultSet executeRS(String sql) {// 查询
		Statement stm;
		try {
			stm = getConn().createStatement();
			return stm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}