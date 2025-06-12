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

class ChangeTable implements ActionListener{	// 通过按钮实现动态表格
	private JFrame frame = new JFrame("Welcome To MLDN") ; 
	private JTable table = null ;
	private DefaultTableModel tableModel ;	// TableModel
	private String[] titles = {"姓名","年龄","性别","数学成绩","英语成绩","总分","是否及格"} ;
	private Object [][] userInfo = {
			{"李兴华",30,"男",89,97,186,true} ,
			{"李康",23,"女",90,93,183,false} 
		} ;	// 定义数据
	private JButton addRowBtn = new JButton("增加行") ;	 // 定义按钮
	private JButton removeRowBtn = new JButton("删除行") ;	 // 定义按钮
	private JButton addColBtn = new JButton("增加列") ;	 // 定义按钮
	private JButton removeColBtn = new JButton("删除列") ;	 // 定义按钮
	public ChangeTable(){
		this.tableModel = new DefaultTableModel(this.userInfo,this.titles) ;
		this.table = new JTable(this.tableModel) ;
		JScrollPane scr = new JScrollPane(this.table) ;
		JPanel toolBar = new JPanel() ;
		toolBar.add(this.addRowBtn) ;
		toolBar.add(this.removeRowBtn) ;
		toolBar.add(this.addColBtn) ;
		toolBar.add(this.removeColBtn) ;
		frame.add(toolBar,BorderLayout.NORTH) ;	// 加入组件
		frame.add(scr,BorderLayout.CENTER) ;	// 加入组件
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
		if(e.getSource() == this.addColBtn){	// 增加列
			this.tableModel.addColumn("新增列") ;
		}
		if(e.getSource() == this.addRowBtn){
			this.tableModel.addRow(new Object[]{}) ;
		}
		if(e.getSource()==this.removeColBtn){
			int colCount = this.tableModel.getColumnCount() - 1 ;// 取得列的数量
			if(colCount>=0){
				// 如果要想删除，则必须找到TableColumnModel
				TableColumnModel columMode = this.table.getColumnModel()  ;
				TableColumn taleColumn = columMode.getColumn(colCount) ;
				columMode.removeColumn(taleColumn) ;	// 删除指定的列
				this.tableModel.setColumnCount(colCount) ;
			}
		}
		if(e.getSource()==this.removeRowBtn){
			int rowCount = this.tableModel.getRowCount() - 1 ;
			if(rowCount>=0){	// 判断是否还有行可以删除
				this.tableModel.removeRow(rowCount) ;
				this.tableModel.setRowCount(rowCount) ;	// 设置新的行数
			}
		}
	}
}

public class JTableDemo03{
	public static void main(String args[]){
		new ChangeTable() ;		
	}
}