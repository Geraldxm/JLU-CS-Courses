package com.nciae.empsys.view;

import javax.swing.*;

public class CommonDialog {
	public static boolean deleteDialog(JInternalFrame inf) {
		if (JOptionPane.showConfirmDialog(inf, "ȷ��ɾ����", "ȷ�϶Ի���",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;
	}

	public static boolean addDialog(JInternalFrame inf) {
		if (JOptionPane.showConfirmDialog(inf, "ȷ�������", "ȷ�϶Ի���",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;
	}

	public static boolean updateDialog(JInternalFrame inf) {
		if (JOptionPane.showConfirmDialog(inf, "ȷ���޸���", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	public static void comDialog(JFrame f) {
		JOptionPane.showMessageDialog(f, "��Ϣ��������ȷ�ϣ�");
	}
}
