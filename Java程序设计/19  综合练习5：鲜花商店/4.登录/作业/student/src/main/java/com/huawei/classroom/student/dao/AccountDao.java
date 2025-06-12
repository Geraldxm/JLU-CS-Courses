package com.huawei.classroom.student.dao;

import java.util.List;

import com.huawei.classroom.student.entity.Account;





public interface AccountDao {

	/**
	 * 更新鲜花商店与顾客的交易信息
	 */
	public abstract int updateAccount(String sql, Object[] param);

	/**
	 * 根据查询条件查询出鲜花商店帐单
	 */
	public abstract List<Account> getFlowerStoreAccount(String sql, String[] param);

}
