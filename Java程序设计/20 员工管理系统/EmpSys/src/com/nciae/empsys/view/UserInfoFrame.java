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
	JButton btadd = new JButton("���");
	JButton btupdate = new JButton("�޸�");
	JButton btdelete = new JButton("ɾ��");
	JButton btselect = new JButton("��ѯ");
	JButton btquit = new JButton("�˳�");
	JButton btsave = new JButton("����");
	JButton btcanel = new JButton("ȡ��");
	JButton btp = new JButton("��һҳ<<");
	JButton btn = new JButton(">>��һҳ");
	JTable TbUserInfo = new JTable();
	JTextField cno = new JTextField(10);
	JTextField cname = new JTextField(10);
	JTextField cuname = new JTextField(10);
	JRadioButton cutype1 = new JRadioButton("��������Ա");
	JRadioButton cutype2 = new JRadioButton("����Ա");
	ButtonGroup bgtype = new ButtonGroup();
	JRadioButton csex1 = new JRadioButton("��");
	JRadioButton csex2 = new JRadioButton("Ů");
	ButtonGroup bgsex = new ButtonGroup();
	JComboBox<String> cpolitics = new JComboBox<String>();
	JComboBox<String> cdept = new JComboBox<String>();
	JTextField cage = new JTextField(10);
	JTextField cbirth = new JTextField(10);
	JTextField caddress = new JTextField(10);
	JTextField cphone = new JTextField(10);
	JTextArea cper = new JTextArea(3, 40);
	JTextArea cmark = new JTextArea(3, 40);
	JRadioButton cstate1 = new JRadioButton("��ְ");
	JRadioButton cstate2 = new JRadioButton("��ְ");
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
		JLabel l1 = new JLabel("Ա�����");
		JLabel l2 = new JLabel("Ա���˻�");
		JLabel l4 = new JLabel("Ա������");
		JLabel l5 = new JLabel("Ա������");
		JLabel l6 = new JLabel("Ա���Ա�");
		JLabel l7 = new JLabel("������ò");
		JLabel l8 = new JLabel("��������");
		JLabel l9 = new JLabel("Ա������");
		JLabel l10 = new JLabel("��������");
		JLabel l11 = new JLabel("��ͥסַ");
		JLabel l12 = new JLabel("���˼��");
		JLabel l13 = new JLabel("��ϵ�绰");
		JLabel l14 = new JLabel("Ŀǰ״̬");
		JLabel l15 = new JLabel("������ע");
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
		p0.add(btp);// ��һҳ
		p0.add(btn);// ��һҳ

		p2.add(l1);// ��һ�пؼ�
		p2.add(cno);
		p2.add(l2);
		p2.add(cuname);
		p2.add(l4);
		p2.add(cname);

		p3.add(l5);// �ڶ��пؼ�
		cutype1.setSelected(true);
		bgtype.add(cutype1);
		bgtype.add(cutype2);
		p3.add(cutype1);
		p3.add(cutype2);

		p3.add(l6);// �����пؼ�
		csex1.setSelected(true);
		bgsex.add(csex1);
		bgsex.add(csex2);
		p3.add(csex1);
		p3.add(csex2);
		p3.add(l7);
		cpolitics.addItem("�й���Ա");
		cpolitics.addItem("Ԥ����Ա");
		cpolitics.addItem("������Ա");
		cpolitics.addItem("Ⱥ��");
		cpolitics.addItem("����");
		p3.add(cpolitics);

		p5.add(l8);// �����пؼ�
		cdept.addItem("����");
		cdept.addItem("����");
		cdept.addItem("�з�");
		p5.add(cdept);
		p5.add(l9);
		p5.add(cage);
		p5.add(l10);
		p5.add(cbirth);

		p6.add(l11);// �����пؼ�
		p6.add(caddress);

		p6.add(l13);// �����пؼ�
		p6.add(cphone);
		p6.add(l14);
		cstate1.setSelected(true);
		bgstate.add(cstate1);
		bgstate.add(cstate2);
		p6.add(cstate1);
		p6.add(cstate2);

		p7.add(l12);// �����пؼ�
		p7.add(cper);

		p8.add(l15);// �ڰ��пؼ�
		p8.add(cmark);

		p1.add(btadd);// ���еĲ�����ť
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
		// ����JSprollPane�Ĵ�С
		sp.setPreferredSize(new Dimension(500, 200));
		this.add(sp, BorderLayout.NORTH);
		this.add(p4, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		// ����ѯ����ť�Ĳ�������
		if (e.getSource().equals(btselect)) {
			HandleEmp h = new HandleEmp();
			Employee emp = new Employee();
			bindEmp(emp);
			showTable(0, emp);
			setBtn(true, true, true, false, false);
		}
		// ����ӡ���ť�Ĳ�������
		if (e.getSource().equals(btadd)) {
			setBtn(false, false, false, true, true);
			this.cno.requestFocus();
		}
		// �����桱��ť�Ĳ�������
		if (e.getSource().equals(btsave)) {
			if (CommonDialog.addDialog(this)) {
				HandleEmp h = new HandleEmp();
				Employee emp = new Employee();
				bindEmp(emp);
				if (h.addEmp(emp)) {
					JOptionPane.showMessageDialog(this, "��ӳɹ���");
					showTable(0, null);
					setBtn(true, true, true, false, false);
				}
			}
		}
		// ��ȡ������ť�Ĳ�������
		if (e.getSource().equals(btcanel)) {
			setBtn(true, true, true, false, false);
		}
		// ����һҳ����ť�Ĳ�������
		if (e.getSource().equals(btp)) {
			page--;
			int s = page * pagesize;
			showTable(s, null);
			setBtn(true, true, true, false, false);
		}
		// ����һҳ����ť�Ĳ�������
		if (e.getSource().equals(btn)) {
			page++;
			int s = page * pagesize;
			showTable(s, null);
			setBtn(true, true, true, false, false);
		}
		// ��ɾ������ť�Ĳ�������
		if (e.getSource().equals(btdelete)) {
			if (CommonDialog.deleteDialog(this)) {
				try {
					HandleEmp h = new HandleEmp();
					if (h.deleteEmp(cno.getText())) {
						JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
						showTable(page * pagesize, null);
						setBtn(true, true, true, false, false);
					}
				} catch (Exception ee) {
				}
			}
		}
		// ���޸ġ���ť�Ĳ�������
		if (e.getSource().equals(btupdate)) {
			if (CommonDialog.updateDialog(this)) {
				HandleEmp h = new HandleEmp();
				Employee emp = new Employee();
				bindEmp(emp);
				if (h.updateEmp(emp)) {
					JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
					showTable(0, null);
					setBtn(true, true, true, false, false);
				}
			}
		}
		// ���˳�����ť�Ĳ�������
		if (e.getSource().equals(btquit)) {
			this.dispose();
		}
	}

	// �������ռ��������ݴ洢��emp������
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
			emp.setSex("��");
		else
			emp.setSex("Ů");
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

	// ���ò�����ť��������Ч��
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

	// �����ݿ��е����ݰ󶨵�JTable��
	private void showTable(int s, Employee emp) {
		HandleEmp h = new HandleEmp();
		ArrayList<Employee> list;
		if (emp != null)
			list = h.selectByCon(emp);
		else
			list = h.selectAll();
		tbvalue = new Object[pagesize][6];
		if (s < 0) {
			JOptionPane.showMessageDialog(null, "�Ѿ��ǵ�һҳ�ˣ�");
			return;
		}
		if (s > list.size()) {
			JOptionPane.showMessageDialog(null, "�Ѿ������һҳ�ˣ�");
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
		String[] tbtitle = { "Ա�����", "Ա���˺�", "Ա������", "Ա���Ա�", "��ϵ�绰", "Ա��״̬" };
		TbUserInfo.setModel(new DefaultTableModel(tbvalue, tbtitle));
		sp.setViewportView(TbUserInfo);
	}

	// ��굥���¼�����ʾ�����˵�
	private void TbUserInfoMouseClicked(MouseEvent evt) {
		int row = TbUserInfo.getSelectedRow();
		this.cno.setText(TbUserInfo.getValueAt(row, 0).toString());
		this.cuname.setText(TbUserInfo.getValueAt(row, 1).toString());
		this.cname.setText(TbUserInfo.getValueAt(row, 2).toString());
		if (TbUserInfo.getValueAt(row, 3).toString().equals("��")) {
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
