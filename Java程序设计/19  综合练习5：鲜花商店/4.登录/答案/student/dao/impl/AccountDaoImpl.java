package com.huawei.classroom.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huawei.classroom.student.dao.AccountDao;
import com.huawei.classroom.student.dao.BaseDao;
import com.huawei.classroom.student.entity.Account;


/**
 * 鲜花商店账户信息数据库操作类
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集
	@Override
	public int updateAccount(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	@Override
	public List<Account> getFlowerStoreAccount(String sql, String[] param) {
		List<Account> accountList = new ArrayList<Account>();
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			Account account = null;
			while (rs.next()) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setDealType(rs.getInt(2));//账户交易类型
				account.setFlowerId(rs.getInt(3));//鲜花编号
				account.setSellerId(rs.getInt(4));//买入者id
				account.setBuyerId(rs.getInt(5));//购买者id
				account.setPrice(rs.getDouble(6));//交易价格
				account.setDealTime(rs.getDate(7));//交易日期
				accountList.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return accountList;
	}
	}


