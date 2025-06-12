package com.nciae.empsys.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import com.nciae.empsys.handle.HandleEmp;
import com.nciae.empsys.table.Employee;
import java.sql.*;
import java.util.ArrayList;

public class UserInfoFrame extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane sp = new JScrollPane();
	JButton btadd = new JButton("添加");
	JButton btupdate = new JButton("修改");
	JButton btdelete = new JButton("删除");
	JButton btselect = new JButton("查询");
	JButton btquit = new JButton("退出");
	JButton btsave = new JButton("保存");
	JButton btcanel = new JButton("取消");
	JButton btp = new JButton("上一页<<");
	JButton btn = new JButton(">>下一页");
	JTable TbUserInfo = new JTable();
	JTextField cno = new JTextField(10);
	JTextField cname = new JTextField(10);
	JTextField cuname = new JTextField(10);
	JRadioButton cutype1 = new JRadioButton("超级管理员");
	JRadioButton cutype2 = new JRadioButton("管理员");
	ButtonGroup bgtype = new ButtonGroup();
	JRadioButton csex1 = new JRadioButton("男");
	JRadioButton csex2 = new JRadioButton("女");
	ButtonGroup bgsex = new ButtonGroup();
	JComboBox<String> cpolitics = new JComboBox<String>();
	JComboBox<String> cdept = new JComboBox<String>();
	JTextField cage = new JTextField(10);
	JTextField cbirth = new JTextField(10);
	JTextField caddress = new JTextField(10);
	JTextField cphone = new JTextField(10);
	JTextArea cper = new JTextArea(3, 40);
	JTextArea cmark = new JTextArea(3, 40);
	JRadioButton cstate1 = new JRadioButton("在职");
	JRadioButton cstate2 = new JRadioButton("离职");
	ButtonGroup bgstate = new ButtonGroup();
	ResultSet rs = null;
	Object[][] tbvalue = null;
	private static int pagesize = 10, page = 0;

	public UserInfoFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconificial) {
		super(title, resizable, closable, maximizable, iconificial);
		showTable(0, null);
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();
		JPanel p10 = new JPanel();
		JPanel p4 = new JPanel(new GridLayout(8, 1));
		JLabel l1 = new JLabel("员工编号");
		JLabel l2 = new JLabel("员工账户");
		JLabel l4 = new JLabel("员工姓名");
		JLabel l5 = new JLabel("员工类型");
		JLabel l6 = new JLabel("员工性别");
		JLabel l7 = new JLabel("政治面貌");
		JLabel l8 = new JLabel("所属部门");
		JLabel l9 = new JLabel("员工年龄");
		JLabel l10 = new JLabel("出生日期");
		JLabel l11 = new JLabel("家庭住址");
		JLabel l12 = new JLabel("个人简介");
		JLabel l13 = new JLabel("联系电话");
		JLabel l14 = new JLabel("目前状态");
		JLabel l15 = new JLabel("其他备注");
		btadd.addActionListener(this);
		btsave.addActionListener(this);
		btcanel.addActionListener(this);
		btdelete.addActionListener(this);
		btupdate.addActionListener(this);
		btselect.addActionListener(this);
		btquit.addActionListener(this);
		btp.addActionListener(this);
		btn.addActionListener(this);
		TbUserInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				TbUserInfoMouseClicked(evt);
			}
		});
		p0.add(btp);// 上一页
		p0.add(btn);// 下一页

		p2.add(l1);// 第一行控件
		p2.add(cno);
		p2.add(l2);
		p2.add(cuname);
		p2.add(l4);
		p2.add(cname);

		p3.add(l5);// 第二行控件
		cutype1.setSelected(true);
		bgtype.add(cutype1);
		bgtype.add(cutype2);
		p3.add(cutype1);
		p3.add(cutype2);

		p3.add(l6);// 第三行控件
		csex1.setSelected(true);
		bgsex.add(csex1);
		bgsex.add(csex2);
		p3.add(csex1);
		p3.add(csex2);
		p3.add(l7);
		cpolitics.addItem("中共党员");
		cpolitics.addItem("预备党员");
		cpolitics.addItem("共青团员");
		cpolitics.addItem("群众");
		cpolitics.addItem("其他");
		p3.add(cpolitics);

		p5.add(l8);// 第四行控件
		cdept.addItem("机关");
		cdept.addItem("财务");
		cdept.addItem("研发");
		p5.add(cdept);
		p5.add(l9);
		p5.add(cage);
		p5.add(l10);
		p5.add(cbirth);

		p6.add(l11);// 第五行控件
		p6.add(caddress);

		p6.add(l13);// 第六行控件
		p6.add(cphone);
		p6.add(l14);
		cstate1.setSelected(true);
		bgstate.add(cstate1);
		bgstate.add(cstate2);
		p6.add(cstate1);
		p6.add(cstate2);

		p7.add(l12);// 第七行控件
		p7.add(cper);

		p8.add(l15);// 第八行控件
		p8.add(cmark);

		p1.add(btadd);// 所有的操作按钮
		btsave.setEnabled(false);
		btcanel.setEnabled(false);
		this.cno.setEditable(false);
		p1.add(btsave);
		p1.add(btcanel);
		p1.add(btupdate);
		p1.add(btdelete);
		p1.add(btselect);
		p1.add(btquit);

		p4.add(p0);
		p4.add(p2);
		p4.add(p3);
		p4.add(p5);
		p4.add(p6);
		p4.add(p7);
		p4.add(p8);
		p4.add(p1);
		// 设置JSprollPane的大小
		sp.setPreferredSize(new Dimension(500, 200));
		this.add(sp, BorderLayout.NORTH);
		this.add(p4, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		// “查询”按钮的操作流程
		if (e.getSource().equals(btselect)) {
			HandleEmp h = new HandleEmp();
			Employee emp = new Employee();
			bindEmp(emp);
			showTable(0, emp);
			setBtn(true, true, true, false, false);
		}
		// “添加”按钮的操作流程
		if (e.getSource().equals(btadd)) {
			setBtn(false, false, false, true, true);
			this.cno.requestFocus();
		}
		// “保存”按钮的操作流程
		if (e.getSource().equals(btsave)) {
			if (CommonDialog.addDialog(this)) {
				HandleEmp h = new HandleEmp();
				Employee emp = new Employee();
				bindEmp(emp);
				if (h.addEmp(emp)) {
					JOptionPane.showMessageDialog(this, "添加成功！");
					showTable(0, null);
					setBtn(true, true, true, false, false);
				}
			}
		}
		// “取消”按钮的操作流程
		if (e.getSource().equals(btcanel)) {
			setBtn(true, true, true, false, false);
		}
		// “上一页”按钮的操作流程
		if (e.getSource().equals(btp)) {
			page--;
			int s = page * pagesize;
			showTable(s, null);
			setBtn(true, true, true, false, false);
		}
		// “下一页”按钮的操作流程
		if (e.getSource().equals(btn)) {
			page++;
			int s = page * pagesize;
			showTable(s, null);
			setBtn(true, true, true, false, false);
		}
		// “删除”按钮的操作流程
		if (e.getSource().equals(btdelete)) {
			if (CommonDialog.deleteDialog(this)) {
				try {
					HandleEmp h = new HandleEmp();
					if (h.deleteEmp(cno.getText())) {
						JOptionPane.showMessageDialog(this, "删除成功！");
						showTable(page * pagesize, null);
						setBtn(true, true, true, false, false);
					}
				} catch (Exception ee) {
				}
			}
		}
		// “修改”按钮的操作流程
		if (e.getSource().equals(btupdate)) {
			if (CommonDialog.updateDialog(this)) {
				HandleEmp h = new HandleEmp();
				Employee emp = new Employee();
				bindEmp(emp);
				if (h.updateEmp(emp)) {
					JOptionPane.showMessageDialog(this, "修改成功！");
					showTable(0, null);
					setBtn(true, true, true, false, false);
				}
			}
		}
		// “退出”按钮的操作流程
		if (e.getSource().equals(btquit)) {
			this.dispose();
		}
	}

	// 将界面收集到的数据存储到emp对象中
	private void bindEmp(Employee emp) {
		if (!cno.getText().equals(""))
			emp.setId(Integer.parseInt(cno.getText()));
		emp.setuName(cuname.getText());
		emp.setPass("123456");
		if (cutype1.isSelected())
			emp.setUtype(1);
		else
			emp.setUtype(0);
		emp.setName(cname.getText());
		if (csex1.isSelected())
			emp.setSex("男");
		else
			emp.setSex("女");
		emp.setPolitics(cpolitics.getSelectedItem().toString());
		emp.setDept(cdept.getSelectedItem().toString());
		if (cage.getText() != null && !cage.getText().equals(""))
			emp.setAge(Integer.parseInt(cage.getText()));
		emp.setBirth(cbirth.getText());
		emp.setAddress(caddress.getText());
		emp.setPhone(cphone.getText());
		emp.setPer(cper.getText());
		if (cstate1.isSelected())
			emp.setState(1);
		else
			emp.setState(0);
		emp.setMark(cmark.getText());
	}

	// 设置操作按钮的能用性效果
	private void setBtn(boolean add, boolean delete, boolean update, boolean save, boolean canel) {
		this.btadd.setEnabled(add);
		this.btdelete.setEnabled(delete);
		this.btupdate.setEnabled(update);
		this.btsave.setEnabled(save);
		this.btcanel.setEnabled(canel);
		this.cno.setText("");
		this.cname.setText("");
		this.cphone.setText("");
		this.cuname.setText("");
	}

	// 将数据库中的数据绑定到JTable中
	private void showTable(int s, Employee emp) {
		HandleEmp h = new HandleEmp();
		ArrayList<Employee> list;
		if (emp != null)
			list = h.selectByCon(emp);
		else
			list = h.selectAll();
		tbvalue = new Object[pagesize][6];
		if (s < 0) {
			JOptionPane.showMessageDialog(null, "已经是第一页了！");
			return;
		}
		if (s > list.size()) {
			JOptionPane.showMessageDialog(null, "已经是最后一页了！");
			return;
		}
		for (int i = s, j = 0; i < s + pagesize; i++, j++) {
			if (i < list.size()) {
				tbvalue[j][0] = list.get(i).getId();
				tbvalue[j][1] = list.get(i).getuName();
				tbvalue[j][2] = list.get(i).getName();
				tbvalue[j][3] = list.get(i).getSex();
				tbvalue[j][4] = list.get(i).getPhone();
				tbvalue[j][5] = list.get(i).getState();
			}
		}
		String[] tbtitle = { "员工编号", "员工账号", "员工姓名", "员工性别", "联系电话", "员工状态" };
		TbUserInfo.setModel(new DefaultTableModel(tbvalue, tbtitle));
		sp.setViewportView(TbUserInfo);
	}

	// 鼠标单击事件，显示弹出菜单
	private void TbUserInfoMouseClicked(MouseEvent evt) {
		int row = TbUserInfo.getSelectedRow();
		this.cno.setText(TbUserInfo.getValueAt(row, 0).toString());
		this.cuname.setText(TbUserInfo.getValueAt(row, 1).toString());
		this.cname.setText(TbUserInfo.getValueAt(row, 2).toString());
		if (TbUserInfo.getValueAt(row, 3).toString().equals("男")) {
			this.csex1.setSelected(true);
		} else {
			this.csex2.setSelected(true);
		}
		this.cphone.setText(TbUserInfo.getValueAt(row, 4).toString());
		if (TbUserInfo.getValueAt(row, 5).toString().equals("1")) {
			this.cstate1.setSelected(true);
		} else {
			this.cstate2.setSelected(true);
		}
	}
}
