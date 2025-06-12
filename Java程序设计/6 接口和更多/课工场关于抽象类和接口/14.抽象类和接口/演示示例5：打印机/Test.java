package cn.jbit.printer.printer;


/**
 * 测试类
 * @author 北大青鸟
 */
public class Test {
	
	public static void main(String[] args) {
		//1、定义打印机
		InkBox inkBox = null;
		Paper paper = null;
		Printer printer=new Printer();
		
		//2.1、使用黑白墨盒在A4纸上打印
		inkBox=new GrayInkBox();
		paper=new A4Paper();		
		printer.setInkBox(inkBox);
		printer.setPaper(paper);
		printer.print();

		//2.2、使用彩色墨盒在B5纸上打印
		inkBox=new ColorInkBox();
		paper=new B5Paper();		
		printer.setInkBox(inkBox);
		printer.setPaper(paper);
		printer.print();
		
		//2.3使用彩色墨盒在A4纸上打印
		paper=new  A4Paper();
		printer.setPaper(paper);
		printer.print();
		
	}

}
