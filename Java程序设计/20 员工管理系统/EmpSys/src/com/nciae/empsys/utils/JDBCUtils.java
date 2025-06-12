package com.nciae.empsys.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	// �������ݿ�
	private static Connection getConn() {
		try {
			// ʹ��properties�ļ��洢�ĸ�����
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

	// ִ�����ݿ����ӡ�ɾ�����޸�
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

	// ��ѯ���ݿ�Ĳ���
	public static ResultSet executeRS(String sql) {// ��ѯ
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