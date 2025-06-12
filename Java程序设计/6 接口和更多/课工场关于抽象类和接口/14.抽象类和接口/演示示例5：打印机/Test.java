package cn.jbit.printer.printer;


/**
 * ������
 * @author ��������
 */
public class Test {
	
	public static void main(String[] args) {
		//1�������ӡ��
		InkBox inkBox = null;
		Paper paper = null;
		Printer printer=new Printer();
		
		//2.1��ʹ�úڰ�ī����A4ֽ�ϴ�ӡ
		inkBox=new GrayInkBox();
		paper=new A4Paper();		
		printer.setInkBox(inkBox);
		printer.setPaper(paper);
		printer.print();

		//2.2��ʹ�ò�ɫī����B5ֽ�ϴ�ӡ
		inkBox=new ColorInkBox();
		paper=new B5Paper();		
		printer.setInkBox(inkBox);
		printer.setPaper(paper);
		printer.print();
		
		//2.3ʹ�ò�ɫī����A4ֽ�ϴ�ӡ
		paper=new  A4Paper();
		printer.setPaper(paper);
		printer.print();
		
	}

}
