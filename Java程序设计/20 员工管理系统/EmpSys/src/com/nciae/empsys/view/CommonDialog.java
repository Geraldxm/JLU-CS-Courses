package com.nciae.empsys.view;

import javax.swing.*;

public class CommonDialog {
	public static boolean deleteDialog(JInternalFrame inf) {
		if (JOptionPane.showConfirmDialog(inf, "确认删除吗？", "确认对话框",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;
	}

	public static boolean addDialog(JInternalFrame inf) {
		if (JOptionPane.showConfirmDialog(inf, "确认添加吗？", "确认对话框",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;
	}

	public static boolean updateDialog(JInternalFrame inf) {
		if (JOptionPane.showConfirmDialog(inf, "确认修改吗？", "确认对话框", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	public static void comDialog(JFrame f) {
		JOptionPane.showMessageDialog(f, "信息有误，请再确认！");
	}
}
