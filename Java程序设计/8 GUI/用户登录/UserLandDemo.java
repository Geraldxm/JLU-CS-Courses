import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
//主类UserLandDemo继承了JFrame，并且实现了ActionListener接口
public class UserLandDemo extends JFrame implements ActionListener 
{
	/*
	 * 声明各个变量
	 * nameLabel显示用户名，passWordLabel显示密码名
	 * nameText表示：要求用户输入用户
	 * passWordText表示：要求用户输入密码，并且密码用‘*’返回
	 * okButton表示：确定按钮；cancelButton表示：取消按钮
	 */
	JLabel nameLabel,passWordLabel ;
	JTextField nameText ;
	JPasswordField passWordText ;
	JButton okButton,cancelButton ;
	//构造器
	public UserLandDemo(String title)
	{
		//调用父类的构造器
		super(title) ;
		//定义各个变量
		nameLabel = new JLabel("用户名：") ;
		nameText = new JTextField(10) ;
		passWordLabel = new JLabel("密    码：") ;
		passWordText = new JPasswordField(10) ;
		okButton = new JButton("确定") ;
		cancelButton = new JButton("取消") ;
		//为okButton和cancelButton两个按钮注册监听器
		okButton.addActionListener(this) ;
		cancelButton.addActionListener(this) ;
		//声明并定义了各个面板对象
		JPanel namePanel,passWordPanel,okPanel,containterPanel ;
		namePanel = new JPanel() ;
		passWordPanel = new JPanel() ;
		okPanel = new JPanel() ;
		containterPanel = new JPanel() ;
		//设置面板属性
		namePanel.setBackground(new Color(0,100,50)) ;
		passWordPanel.setBackground(new Color(0,100,50)) ;
		okPanel.setBackground(new Color(0,100,50)) ;
		//把各个组件添加到相应的面板中
		namePanel.add(nameLabel) ;
		namePanel.add(nameText) ;
		passWordPanel.add(passWordLabel) ;
		passWordPanel.add(passWordText) ;
		okPanel.add(okButton) ;
		okPanel.add(cancelButton) ;
		//设置面板布局管理器
		containterPanel.setLayout(new GridLayout(3,1)) ;
		containterPanel.add(namePanel) ;
		containterPanel.add(passWordPanel) ;
		containterPanel.add(okPanel) ;
        //设置面板布局管理器
		this.setLayout(new BorderLayout()) ;
		this.getContentPane().add(containterPanel,BorderLayout.CENTER) ;
		//设置窗体
		this.setLocation(100, 100) ;
		this.setSize(200, 150) ;
		//添加窗体关闭事件监听器
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		//显示窗体
		this.setVisible(true) ;
	}
	//实现监听器
	public void actionPerformed(ActionEvent e) {
		//获得事件源
		JButton jb = (JButton)e.getSource() ;
		//判断事件源
		if(jb == okButton)
		{
			//声明并定义了两个变量name和passWord，用来存放输入到系统中的
			//用户名和密码
			String name,passWord ;
			name = nameText.getText() ;
			passWord = passWordText.getText() ;
			//判断用户名或者密码是否为空？
			if(name.length() == 0 || passWord.length() == 0)
			{
				//显示提示信息
				JOptionPane.showMessageDialog(this, "用户名和密码不能为空!") ;
			}
			else
			{
				if(name.equals("zhao") && passWord.equals("123"))
				{
					//显示登录成功提示信息
					JOptionPane.showMessageDialog(this, "登录成功，欢迎光临!") ;
				}
				else
				{
					//显示用户名或密码错提示信息
					JOptionPane.showMessageDialog(this, "用户名或密码错，请核对后再登录!") ;
				}
			}
		}
		else
		{
			//清空输入框，并且使用户名输入框获得焦点
			nameText.setText("") ;
			passWordText.setText("") ;
			nameText.setFocusable(true) ;
		}
	}
	//系统执行入口
	public static void main(String[] args) {
		new UserLandDemo("用户登录界面") ;
	}

}
