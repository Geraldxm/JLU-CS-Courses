import java.awt.BorderLayout ;
import java.awt.event.WindowAdapter ;
import java.awt.event.ActionListener ;
import java.awt.event.WindowEvent ;
import java.awt.event.ActionEvent ;
import javax.swing.JTable ;
import javax.swing.JButton ;
import javax.swing.JScrollPane ;
import javax.swing.JFrame ;
import javax.swing.JPanel ;
import javax.swing.DefaultCellEditor ;
import javax.swing.JComboBox ;
import javax.swing.table.DefaultTableModel ;
import javax.swing.table.TableColumnModel ;
import javax.swing.table.TableColumn ;

class ChangeTable implements ActionListener{	// ͨ����ťʵ�ֶ�̬���
	private JFrame frame = new JFrame("Welcome To MLDN") ; 
	private JTable table = null ;
	private DefaultTableModel tableModel ;	// TableModel
	private String[] titles = {"����","����","�Ա�","��ѧ�ɼ�","Ӣ��ɼ�","�ܷ�","�Ƿ񼰸�"} ;
	private Object [][] userInfo = {
			{"���˻�",30,"��",89,97,186,true} ,
			{"�",23,"Ů",90,93,183,false} 
		} ;	// ��������
	private JButton addRowBtn = new JButton("������") ;	 // ���尴ť
	private JButton removeRowBtn = new JButton("ɾ����") ;	 // ���尴ť
	private JButton addColBtn = new JButton("������") ;	 // ���尴ť
	private JButton removeColBtn = new JButton("ɾ����") ;	 // ���尴ť
	public ChangeTable(){
		this.tableModel = new DefaultTableModel(this.userInfo,this.titles) ;
		this.table = new JTable(this.tableModel) ;
		JScrollPane scr = new JScrollPane(this.table) ;
		JPanel toolBar = new JPanel() ;
		toolBar.add(this.addRowBtn) ;
		toolBar.add(this.removeRowBtn) ;
		toolBar.add(this.addColBtn) ;
		toolBar.add(this.removeColBtn) ;
		frame.add(toolBar,BorderLayout.NORTH) ;	// �������
		frame.add(scr,BorderLayout.CENTER) ;	// �������
		frame.setSize(370,160) ;
		frame.setVisible(true) ;
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(1) ;
			}
		}) ;
		this.addRowBtn.addActionListener(this) ;
		this.removeRowBtn.addActionListener(this) ;
		this.addColBtn.addActionListener(this) ;
		this.removeColBtn.addActionListener(this) ;


	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.addColBtn){	// ������
			this.tableModel.addColumn("������") ;
		}
		if(e.getSource() == this.addRowBtn){
			this.tableModel.addRow(new Object[]{}) ;
		}
		if(e.getSource()==this.removeColBtn){
			int colCount = this.tableModel.getColumnCount() - 1 ;// ȡ���е�����
			if(colCount>=0){
				// ���Ҫ��ɾ����������ҵ�TableColumnModel
				TableColumnModel columMode = this.table.getColumnModel()  ;
				TableColumn taleColumn = columMode.getColumn(colCount) ;
				columMode.removeColumn(taleColumn) ;	// ɾ��ָ������
				this.tableModel.setColumnCount(colCount) ;
			}
		}
		if(e.getSource()==this.removeRowBtn){
			int rowCount = this.tableModel.getRowCount() - 1 ;
			if(rowCount>=0){	// �ж��Ƿ����п���ɾ��
				this.tableModel.removeRow(rowCount) ;
				this.tableModel.setRowCount(rowCount) ;	// �����µ�����
			}
		}
	}
}

public class JTableDemo03{
	public static void main(String args[]){
		new ChangeTable() ;		
	}
}