package com.nciae.empsys.handle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.nciae.empsys.table.Employee;
import com.nciae.empsys.utils.JDBCUtils;

public class HandleEmp {// 业务层MVC
	public boolean login(Employee e) {// 返回值是什么类型？谁登陆？形参是什么？员工对象
		// select查询
		String sql = "select * from base_employee where username='" + e.getName() + "'";
		ResultSet rs = JDBCUtils.executeRS(sql);
		try {
			if (rs.next()) {
				if (rs.getString("pass").equals(e.getPass()))
					return true;
				else
					return false;
			} else
				return false;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	// insert
	public boolean addEmp(Employee e) {
		String sql = "insert into base_employee(username,pass,utype,uName,sex,politics,dept,age,birth,address,phone,per,state,mark)values('"
				+ e.getName() + "','" + e.getPass() + "'," + e.getUtype() + ",'" + e.getuName() + "','" + e.getSex()
				+ "','" + e.getPolitics() + "','" + e.getDept() + "'," + e.getAge() + ",'" + e.getBirth() + "','"
				+ e.getAddress() + "','" + e.getPhone() + "','" + e.getPer() + "'," + e.getState() + ",'" + e.getMark()
				+ "')";
		return JDBCUtils.executeDDL(sql);
	}

	// delete
	public boolean deleteEmp(String id) {
		String sql = "delete from base_employee where id=" + id;
		return JDBCUtils.executeDDL(sql);
	}

	// update
	public boolean updateEmp(Employee e) {
		String sql = "update base_employee set username='" + e.getName() + "',pass='" + e.getPass() + "',utype="
				+ e.getUtype() + ",uName='" + e.getuName() + "',sex='" + e.getSex() + "',politics='" + e.getPolitics()
				+ "',dept='" + e.getDept() + "',age=" + e.getAge() + ",birth='" + e.getBirth() + "',address='"
				+ e.getAddress() + "',phone='" + e.getPhone() + "',per='" + e.getPer() + "',state=" + e.getState()
				+ ",mark='" + e.getMark() + "' where id=" + e.getId();
		return JDBCUtils.executeDDL(sql);
	}

	public Employee selectByID(int id) {
		String sql = "select * from base_employee where id=" + id;
		ResultSet rs = JDBCUtils.executeRS(sql);
		try {
			if (rs.next()) {// 查询到的结果转成对象
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("username"));
				emp.setPass(rs.getString("pass"));
				return emp;
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Employee> selectAll() {
		String sql = "select * from base_employee";
		ArrayList<Employee> emplist = new ArrayList<Employee>();
		ResultSet rs = JDBCUtils.executeRS(sql);
		try {
			while (rs.next()) {// 查询到的结果转成对象
				Employee emp = new Employee();// 新建一个对象
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("username"));
				emp.setPass(rs.getString("pass"));
				emp.setUtype(rs.getInt("utype"));
				emp.setuName(rs.getString("uName"));
				emp.setSex(rs.getString("sex"));
				emp.setPolitics(rs.getString("politics"));
				emp.setDept(rs.getString("dept"));
				emp.setAge(rs.getInt("age"));
				emp.setBirth(rs.getString("birth"));
				emp.setAddress(rs.getString("address"));
				emp.setPhone(rs.getString("phone"));
				emp.setPer(rs.getString("per"));
				emp.setState(rs.getInt("state"));
				emp.setMark(rs.getString("mark"));
				emplist.add(emp);
			}
			return emplist;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Employee> selectByCon(Employee em) {
		String sql = "select * from base_employee where 1=1";
		if (!em.getuName().equals("")) {
			sql = sql + " and username like '%" + em.getuName() + "%'";
		}
		sql = sql + " and state=" + em.getState();
		if (!em.getName().equals(""))
			sql = sql + " and uname like '%" + em.getName() + "%'";
		ArrayList<Employee> emplist = new ArrayList<Employee>();
		ResultSet rs = JDBCUtils.executeRS(sql);
		try {
			while (rs.next()) { // 将查询到的结果转成对象
				Employee emp = new Employee();// 新建一个对象
				emp.setId(rs.getInt("id"));
				emp.setuName(rs.getString("username"));
				emp.setPass(rs.getString("pass"));
				emp.setUtype(rs.getInt("utype"));
				emp.setName(rs.getString("uName"));
				emp.setSex(rs.getString("sex"));
				emp.setPolitics(rs.getString("politics"));
				emp.setDept(rs.getString("dept"));
				emp.setAge(rs.getInt("age"));
				emp.setBirth(rs.getString("birth"));
				emp.setAddress(rs.getString("address"));
				emp.setPhone(rs.getString("phone"));
				emp.setPer(rs.getString("per"));
				emp.setState(rs.getInt("state"));
				emp.setMark(rs.getString("mark"));
				emplist.add(emp);
			}
			return emplist;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
