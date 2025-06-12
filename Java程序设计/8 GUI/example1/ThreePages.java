import  java.awt.*;
import  java.awt.event.*;

public  class  ThreePages implements MouseListener 
{
	   CardLayout  layout = new  CardLayout();
	   Frame  f = new  Frame("CardLayout");
	   Button  page1Button;
	   Label  page2Label;
	   TextArea  page3Text;
	   Button  page3Top;
	   Button  page3Bottom;
  
	  public  static  void  main(String  args[]){
	    new  ThreePages().go();
	  }
  
	  public void go()
	  {       
		f.setLayout(layout);
		f.add(page1Button=new Button("Button page") , "page1Button");
		page1Button.addMouseListener(this);
		
		page2Label=new Label("Label page");
		
		f.add(page2Label,"page2Label");
				
		        page2Label.addMouseListener(this);
				
		Panel panel=new Panel();
		panel.setLayout(new BorderLayout());
		panel.add(page3Text=new TextArea("Composite page"), "Center");
		page3Text.addMouseListener(this);
		panel.add(page3Top=new Button("Top button") , "North");
		page3Top.addMouseListener(this);
		panel.add(page3Bottom=new Button("Bottom button") ,"South");
		page3Bottom.addMouseListener(this);
		f.add(panel,"panel");
		f.setSize(200,200);
		f.setVisible(true);
	  } 
         public void mouseClicked(MouseEvent e){
         	 layout.next(f);
         }
         public void mouseEntered(MouseEvent e){}
         public void mouseExited(MouseEvent e){}
         public void mousePressed(MouseEvent e){}
         public void mouseReleased(MouseEvent e){}
         
}