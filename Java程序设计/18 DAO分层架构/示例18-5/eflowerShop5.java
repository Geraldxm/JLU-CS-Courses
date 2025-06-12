package com.ssdult.eflowerShop.dao; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * ���ݿ�������رչ�����
*/
public class BaseDao {
    private String driver = "com.mysql.jdbc.Driver";// ���ݿ������ַ���
	private String url = "jdbc:mysql://localhost:3306/flowershop ";// ����URL�ַ���
	private String user = "root"; // ���ݿ��û���
	private String password = "0000"; // �û�����
    Connection conn = null;				// �������Ӷ���
    /**
     * ��ȡ���ݿ����Ӷ���
     */
    public Connection getConnection() {
        if(conn==null) {
            // ��ȡ���Ӳ������쳣
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();				// �쳣����
            }
        }
        return conn;							// �������Ӷ���
    }
    /**
     * �ر����ݿ�����
     * @param conn ���ݿ�����
     * @param stmt Statement����
     * @param rs �����
     */
    public void closeAll(Connection conn, Statement stmt, 
                   ResultSet rs) {
        // �����������Ϊ��,��ر�
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ��Statement����Ϊ��,��ر�
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // �����ݿ����Ӷ���Ϊ��,��ر�
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	 /**
	 * ����ɾ���ĵĲ���
	 * @param sql Ԥ����� SQL ���          
	 * @param param �������ַ�������          
	 * @return Ӱ�������
	 */
	public int exceuteUpdate (String preparedSql, Object[] param) {
		PreparedStatement pstmt = null;
		int num = 0;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
                     	//ΪԤ����sql���ò���
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
