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
	private String[] titles = {"姓名","年龄","性别","数学成绩","英语成绩","总分","是否及格"} ;
	private Object [][] userInfo = {
			{"李兴华",30,"男",89,97,186,true} ,
			{"李康",23,"女",90,93,183,false} 
		} ;	// 定义数据
	public int getColumnCount(){	// 取得列的数量
		return this.titles.length ;
	}
	public int getRowCount(){		// 取得行的数量
		return this.userInfo.length ;
	}
	public Object getValueAt(int rowIndex, int columnIndex){
		return this.userInfo[rowIndex][columnIndex] ;
	}
	public String getColumnName(int columnIndex){
		return this.titles[columnIndex] ;
	}
	public Class<?> getColumnClass(int columnIndex) {	// 得到指定列的类型
		return this.getValueAt(0,columnIndex).getClass() ;
	}
	public boolean isCellEditable(int rowIndex, int columnIndex){	// 所有内容都可以编辑
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
		this.sexList.addItem("男") ;
		this.sexList.addItem("女") ;
		// 以下拉列表框的形式显示
		this.table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(this.sexList)) ;
		JScrollPane scr = new JScrollPane(this.table) ;
		JPanel toolBar = new JPanel() ;
		frame.add(toolBar,BorderLayout.NORTH) ;	// 加入组件
		frame.add(scr,BorderLayout.CENTER) ;	// 加入组件
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