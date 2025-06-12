package cn.jbit.printer.printer;

/**
 * 打印机类。
 * @author 北大青鸟
 */
public class Printer  {	
	InkBox inkBox;  //墨盒
	Paper paper;   //纸张
		
	/**
	 * 设置打印机墨盒
	 * @param inkBox 打印使用的墨盒
	 */
	public void setInkBox(InkBox inkBox){
		this.inkBox=inkBox;
	}
	
	/**
	 * 设置打印机纸张
	 * @param paper 打印使用的纸张
	 */
	public void setPaper(Paper paper){
		this.paper=paper;
	}

	/**
	 * 使用墨盒在纸张上打印
	 */
	public void print(){
		System.out.println("使用"+inkBox.getColor()+
				"墨盒在"+paper.getSize()+"纸张上打印。");
	}
}
