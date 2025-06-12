package com.ssdult.eflowerShop.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ssdult.eflowerShop.dao.BaseDao;
import com.ssdult.eflowerShop.dao.FlowerDao;
import com.ssdult.eflowerShop.entity.Flower;

/**
 * FlowerDao���MySQL���ݿ��ʵ���ࡣ
 */
public class FlowerDaoImpl extends BaseDao implements FlowerDao{
private Connection conn = null; // �������ݿ�����
private PreparedStatement pstmt = null; // ����ִ��SQL���
private ResultSet rs = null; // �û������ѯ�����
/**
 * ��ѯ�����ʻ�
 */
@Override
public List<Flower> getAllFlower() {
	List<Flower> flowerList = new ArrayList<Flower>();
	try {
	String preparedSql = "select id,name,typeName,owner_id,store_id,price from flower ";
	conn = getConn(); // �õ����ݿ�����
	pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
	rs = pstmt.executeQuery(); // ִ��SQL���
while (rs.next()) {
Flower flower = new Flower();
	  flower.setId(rs.getInt(1));
	  flower.setName(rs.getString(2));
	  flower.setTypeName(rs.getString(3));
	  flower.setOwnerId(rs.getInt(4));
	  flower.setStoreId(rs.getInt(5));
	  flower.setPrice(rs.getDouble(6));
	  flowerList.add(flower);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return flowerList;
	}
/**
 * ���ݲ�����ѯ��Ӧ���������ʻ�
 */
	@Override
	public List<Flower> selectFlower(String sql, String[] param) {
		List<Flower> flowerList = new ArrayList<Flower>();
		try {
		conn = getConn(); // �õ����ݿ�����
		pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i + 1, param[i]); // ΪԤ����sql���ò���
			}
		}
		rs = pstmt.executeQuery(); // ִ��SQL���
		while (rs.next()) {
		  Flower flower = new Flower();
		  flower.setId(rs.getInt(1));
		  flower.setName(rs.getString(2));
		  flower.setTypeName(rs.getString(3));
		  flower.setOwnerId(rs.getInt(4));
		  flower.setStoreId(rs.getInt(5));
		  flower.setPrice(rs.getDouble(6));
		  flowerList.add(flower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return flowerList;
	}
	/**
	 * �����ʻ���Ϣ
	 */
	@Override
	public int updateFlower(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}
}	
//ʡ��FlowerDao����������
}
