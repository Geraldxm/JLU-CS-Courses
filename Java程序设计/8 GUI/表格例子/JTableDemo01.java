import java.awt.event.WindowAdapter ;
import java.awt.event.WindowEvent ;
import javax.swing.JTable ;
import javax.swing.JScrollPane ;
import javax.swing.JFrame ;
public class JTableDemo01{
	public static void main(String args[]){
		JFrame frame = new JFrame("Welcome To MLDN") ;
		String[] titles = {"����","����","�Ա�","��ѧ�ɼ�","Ӣ��ɼ�","�ܷ�","�Ƿ񼰸�"} ;
		Object [][] userInfo = {
			{"���˻�",30,"��",89,97,186,true} ,
			{"�",23,"Ů",90,93,183,false} 
		} ;	// ��������
		JTable table = new JTable(userInfo,titles) ;	// �������
		JScrollPane scr = new JScrollPane(table) ;
		frame.add(scr) ;
		frame.setSize(370,90) ;
		frame.setVisible(true) ;
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(1) ;
			}
		})
	}
}