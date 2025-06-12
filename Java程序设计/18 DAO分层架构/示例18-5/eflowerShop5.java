package com.ssdult.eflowerShop.dao; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库连接与关闭工具类
*/
public class BaseDao {
    private String driver = "com.mysql.jdbc.Driver";// 数据库驱动字符串
	private String url = "jdbc:mysql://localhost:3306/flowershop ";// 连接URL字符串
	private String user = "root"; // 数据库用户名
	private String password = "0000"; // 用户密码
    Connection conn = null;				// 数据连接对象
    /**
     * 获取数据库连接对象
     */
    public Connection getConnection() {
        if(conn==null) {
            // 获取连接并捕获异常
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();				// 异常处理
            }
        }
        return conn;							// 返回连接对象
    }
    /**
     * 关闭数据库连接
     * @param conn 数据库连接
     * @param stmt Statement对象
     * @param rs 结果集
     */
    public void closeAll(Connection conn, Statement stmt, 
                   ResultSet rs) {
        // 若结果集对象不为空,则关闭
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 若Statement对象不为空,则关闭
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 若数据库连接对象不为空,则关闭
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	 /**
	 * 增、删、改的操作
	 * @param sql 预编译的 SQL 语句          
	 * @param param 参数的字符串数组          
	 * @return 影响的行数
	 */
	public int exceuteUpdate (String preparedSql, Object[] param) {
		PreparedStatement pstmt = null;
		int num = 0;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
                     	//为预编译sql设置参数
					pstmt.setObject(i + 1, param[i]); 
				}
			}
			num = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(conn,pstmt,null);
		}
		return num;
	}
}
