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
		//TODO:
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
		//TODO:
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
		//TODO:
	}

}
