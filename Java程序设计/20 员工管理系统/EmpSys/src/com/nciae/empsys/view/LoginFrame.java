package com.nciae.empsys.view;

import java.awt.*;
import javax.swing.*;
import com.nciae.empsys.handle.HandleEmp;
import com.nciae.empsys.table.Employee;
import com.nciae.empsys.utils.Validator;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
	JButton btnlogin, btnexit;
	JTextField txtname;
	JPasswordField pfmima;

	public LoginFrame() {
		super("登录系统窗口");
		setLayout(new FlowLayout());
		this.add(new JLabel("用户名："));
		txtname = new JTextField(20);
		this.add(txtname);

		this.add(new JLabel("密码："));
		pfmima = new JPasswordField(20);
		this.add(pfmima);
		btnlogin = new JButton("登录");
		btnexit = new JButton("退出");
		btnlogin.addActionListener(this);
		btnexit.addActionListener(this);
		this.add(btnlogin);
		this.add(btnexit);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 100);
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnlogin)) {
			HandleEmp h = new HandleEmp();
			Employee emp = new Employee();
			if (Validator.isNoEmpty(txtname.getText()) && Validator.isLength(txtname.getText(), 6))
				emp.setName(txtname.getText());
			else {
				CommonDialog.comDialog(this);
				return;
			}
			if (Validator.isNoEmpty(pfmima.getText()) && Validator.isLength(pfmima.getText(), 6))
				emp.setPass(pfmima.getText());
			else {
				CommonDialog.comDialog(this);
				return;
			}
			if (h.login(emp)) {
				MainFrame m = new MainFrame();
				this.dispose();
			} else {
				txtname.setText("");
				pfmima.setText("");
			}
		}
		if (e.getSource().equals(btnexit)) {
			this.dispose();
		}
	}

	public static void main(String[] args) {
		LoginFrame m = new LoginFrame();
	}
}