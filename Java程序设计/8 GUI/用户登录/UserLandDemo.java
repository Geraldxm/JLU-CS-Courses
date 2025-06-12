import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
//����UserLandDemo�̳���JFrame������ʵ����ActionListener�ӿ�
public class UserLandDemo extends JFrame implements ActionListener 
{
	/*
	 * ������������
	 * nameLabel��ʾ�û�����passWordLabel��ʾ������
	 * nameText��ʾ��Ҫ���û������û�
	 * passWordText��ʾ��Ҫ���û��������룬���������á�*������
	 * okButton��ʾ��ȷ����ť��cancelButton��ʾ��ȡ����ť
	 */
	JLabel nameLabel,passWordLabel ;
	JTextField nameText ;
	JPasswordField passWordText ;
	JButton okButton,cancelButton ;
	//������
	public UserLandDemo(String title)
	{
		//���ø���Ĺ�����
		super(title) ;
		//�����������
		nameLabel = new JLabel("�û�����") ;
		nameText = new JTextField(10) ;
		passWordLabel = new JLabel("��    �룺") ;
		passWordText = new JPasswordField(10) ;
		okButton = new JButton("ȷ��") ;
		cancelButton = new JButton("ȡ��") ;
		//ΪokButton��cancelButton������ťע�������
		okButton.addActionListener(this) ;
		cancelButton.addActionListener(this) ;
		//�����������˸���������
		JPanel namePanel,passWordPanel,okPanel,containterPanel ;
		namePanel = new JPanel() ;
		passWordPanel = new JPanel() ;
		okPanel = new JPanel() ;
		containterPanel = new JPanel() ;
		//�����������
		namePanel.setBackground(new Color(0,100,50)) ;
		passWordPanel.setBackground(new Color(0,100,50)) ;
		okPanel.setBackground(new Color(0,100,50)) ;
		//�Ѹ��������ӵ���Ӧ�������
		namePanel.add(nameLabel) ;
		namePanel.add(nameText) ;
		passWordPanel.add(passWordLabel) ;
		passWordPanel.add(passWordText) ;
		okPanel.add(okButton) ;
		okPanel.add(cancelButton) ;
		//������岼�ֹ�����
		containterPanel.setLayout(new GridLayout(3,1)) ;
		containterPanel.add(namePanel) ;
		containterPanel.add(passWordPanel) ;
		containterPanel.add(okPanel) ;
        //������岼�ֹ�����
		this.setLayout(new BorderLayout()) ;
		this.getContentPane().add(containterPanel,BorderLayout.CENTER) ;
		//���ô���
		this.setLocation(100, 100) ;
		this.setSize(200, 150) ;
		//��Ӵ���ر��¼�������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		//��ʾ����
		this.setVisible(true) ;
	}
	//ʵ�ּ�����
	public void actionPerformed(ActionEvent e) {
		//����¼�Դ
		JButton jb = (JButton)e.getSource() ;
		//�ж��¼�Դ
		if(jb == okButton)
		{
			//��������������������name��passWord������������뵽ϵͳ�е�
			//�û���������
			String name,passWord ;
			name = nameText.getText() ;
			passWord = passWordText.getText() ;
			//�ж��û������������Ƿ�Ϊ�գ�
			if(name.length() == 0 || passWord.length() == 0)
			{
				//��ʾ��ʾ��Ϣ
				JOptionPane.showMessageDialog(this, "�û��������벻��Ϊ��!") ;
			}
			else
			{
				if(name.equals("zhao") && passWord.equals("123"))
				{
					//��ʾ��¼�ɹ���ʾ��Ϣ
					JOptionPane.showMessageDialog(this, "��¼�ɹ�����ӭ����!") ;
				}
				else
				{
					//��ʾ�û������������ʾ��Ϣ
					JOptionPane.showMessageDialog(this, "�û������������˶Ժ��ٵ�¼!") ;
				}
			}
		}
		else
		{
			//�������򣬲���ʹ�û���������ý���
			nameText.setText("") ;
			passWordText.setText("") ;
			nameText.setFocusable(true) ;
		}
	}
	//ϵͳִ�����
	public static void main(String[] args) {
		new UserLandDemo("�û���¼����") ;
	}

}
