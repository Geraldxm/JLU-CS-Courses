package com.huawei.classroom.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huawei.classroom.student.dao.BaseDao;
import com.huawei.classroom.student.dao.FlowerStoreDao;
import com.huawei.classroom.student.entity.FlowerStore;





public class FlowerStoreDaoImpl extends BaseDao implements FlowerStoreDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询到的鲜花商店结果集
	@Override
	public List<FlowerStore> getAllStore() {
		List<FlowerStore> storeList = new ArrayList<FlowerStore>();
		try {
		String preparedSql = "select * from flowerstore ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				FlowerStore flowerStore = new FlowerStore();
				flowerStore.setId(rs.getInt(1));
				flowerStore.setName(rs.getString(2));
				flowerStore.setPassword(rs.getString(3));
				flowerStore.setBalance(rs.getDouble(4));
				storeList.add(flowerStore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return storeList;
	}

	@Override
	public FlowerStore getFlowerStore(String sql, String[] param) {
		FlowerStore flowerStore = null;
		try {
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
			}
		}
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				flowerStore = new FlowerStore();
				flowerStore.setId(rs.getInt(1));
				flowerStore.setName(rs.getString(2));
				flowerStore.setPassword(rs.getString(3));
				flowerStore.setBalance(rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return flowerStore;
	}

	@Override
	public int updateStore(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

}
