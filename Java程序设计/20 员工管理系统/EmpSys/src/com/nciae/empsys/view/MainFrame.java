package com.nciae.empsys.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desk = new JDesktopPane();
	JMenuBar jb = new JMenuBar();
	JToolBar tlb = new JToolBar();
	JMenu base = new JMenu("基本资料维护");
	JMenu tool = new JMenu("工具");
	JMenu help = new JMenu("帮助");
	JMenuItem user = new JMenuItem("员工信息管理");
	JMenuItem puser = new JMenuItem("员工信息管理");
	JButton buser = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("qiye.gif")));
	JMenuItem chat = new JMenuItem("即时通信");
	JMenuItem pchat = new JMenuItem("即时通信");
	JMenuItem aboutme = new JMenuItem("关于我们");
	JMenuItem aboutsys = new JMenuItem("关于系统");
	Dimension dim = getToolkit().getScreenSize();
	JPopupMenu p = new JPopupMenu();

	public MainFrame() {
		super("登录系统窗口");
		base.add(user);
		tool.add(chat);
		user.addActionListener(this);
		puser.addActionListener(this);
		help.add(aboutme);
		help.add(aboutsys);
		jb.add(base);
		jb.add(tool);
		jb.add(help);
		this.setJMenuBar(jb);
		p.add(puser);
		p.add(pchat);
		desk.addMouseListener(this);
		this.setContentPane(desk);
		desk.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("qiye.gif"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize((int) dim.getWidth(), (int) dim.getHeight() - 30);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource().equals(user) || e.getSource().equals(puser)) {
				UserInfoFrame s = new UserInfoFrame("维护员工基本资料", false, true, false, true);
				desk.add(s);
				s.pack();
				s.setBounds((int) dim.getWidth() / 2 - 200, (int) dim.getHeight() / 2 - 200, 700, 600);
				s.setVisible(true);
				s.setSelected(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent m) {
		if (m.getButton() == m.BUTTON3) {
			p.show(this, m.getX(), m.getY());
		}
	}

	public void mousePressed(MouseEvent m) {
	}

	public void mouseReleased(MouseEvent m) {
	}

	public void mouseEntered(MouseEvent m) {
	}

	public void mouseExited(MouseEvent m) {
	}
}