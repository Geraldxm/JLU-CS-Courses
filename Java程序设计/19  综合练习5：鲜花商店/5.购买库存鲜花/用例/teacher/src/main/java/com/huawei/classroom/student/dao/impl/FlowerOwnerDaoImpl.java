package com.huawei.classroom.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huawei.classroom.student.dao.FlowerOwnerDao;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.dao.BaseDao;;

/**
 *顾客信息数据库操作类
 */
public class FlowerOwnerDaoImpl extends BaseDao implements FlowerOwnerDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集
	
	
	@Override
	public List<FlowerOwner> getAllOwner() {
		List<FlowerOwner> ownerList = new ArrayList<FlowerOwner>();
		try {
		String preparedSql = "select * from flowerowner";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				FlowerOwner flowerOwner = new FlowerOwner();
				flowerOwner.setId(rs.getInt(1));
				flowerOwner.setName(rs.getString(2));
				flowerOwner.setPassword(rs.getString(3));
				flowerOwner.setMoney(rs.getDouble(4));
				ownerList.add(flowerOwner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return ownerList;
	}

	@Override
	public int updateOwner(String sql, String[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	@Override
	public FlowerOwner selectOwner(String sql, String[] param) {
		FlowerOwner owner = null;
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
				owner = new FlowerOwner();
				owner.setId(rs.getInt(1));
				owner.setName(rs.getString(2));
				owner.setPassword(rs.getString(3));
				owner.setMoney(rs.getDouble(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return owner;
	}

	}


