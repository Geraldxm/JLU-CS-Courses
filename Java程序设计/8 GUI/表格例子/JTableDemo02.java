import java.awt.BorderLayout ;
import java.awt.event.WindowAdapter ;
import java.awt.event.WindowEvent ;
import javax.swing.JTable ;
import javax.swing.JScrollPane ;
import javax.swing.JFrame ;
import javax.swing.JPanel ;
import javax.swing.DefaultCellEditor ;
import javax.swing.JComboBox ;
import javax.swing.table.AbstractTableModel ;

class DefaultTable extends AbstractTableModel{
	private String[] titles = {"����","����","�Ա�","��ѧ�ɼ�","Ӣ��ɼ�","�ܷ�","�Ƿ񼰸�"} ;
	private Object [][] userInfo = {
			{"���˻�",30,"��",89,97,186,true} ,
			{"�",23,"Ů",90,93,183,false} 
		} ;	// ��������
	public int getColumnCount(){	// ȡ���е�����
		return this.titles.length ;
	}
	public int getRowCount(){		// ȡ���е�����
		return this.userInfo.length ;
	}
	public Object getValueAt(int rowIndex, int columnIndex){
		return this.userInfo[rowIndex][columnIndex] ;
	}
	public String getColumnName(int columnIndex){
		return this.titles[columnIndex] ;
	}
	public Class<?> getColumnClass(int columnIndex) {	// �õ�ָ���е�����
		return this.getValueAt(0,columnIndex).getClass() ;
	}
	public boolean isCellEditable(int rowIndex, int columnIndex){	// �������ݶ����Ա༭
		return true ;
	}
	public void setValueAt(Object aValue, int rowIndex, int columnIndex){
		this.userInfo[rowIndex][columnIndex] = aValue ;
	}
}

class TableColumnModelDemo{
	private JFrame frame = new JFrame("Welcome To MLDN") ; 
	private JTable table = null ;
	private DefaultTable defaultTable = new DefaultTable() ;	// TableModel
	private JComboBox sexList = new JComboBox() ;
	public TableColumnModelDemo(){
		this.table = new JTable(this.defaultTable) ;
		this.sexList.addItem("��") ;
		this.sexList.addItem("Ů") ;
		// �������б�����ʽ��ʾ
		this.table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(this.sexList)) ;
		JScrollPane scr = new JScrollPane(this.table) ;
		JPanel toolBar = new JPanel() ;
		frame.add(toolBar,BorderLayout.NORTH) ;	// �������
		frame.add(scr,BorderLayout.CENTER) ;	// �������
		frame.setSize(370,90) ;
		frame.setVisible(true) ;
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(1) ;
			}
		}) ;
	}
}

public class JTableDemo02{
	public static void main(String args[]){
		new TableColumnModelDemo() ;		
	}
}