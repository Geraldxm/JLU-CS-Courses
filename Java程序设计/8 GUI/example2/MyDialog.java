import java.awt.event.*;
import java.awt.*;
class MyDialog extends Dialog implements ActionListener  //建立对话框类。
{  
   static final int YES=1,NO=0;
   int message=-1; Button yes,no;
   MyDialog(Frame f,String s,boolean b) //构造方法。
   {  super(f,s,b);
      yes=new Button("Yes"); yes.addActionListener(this);
      no=new Button("No");   no.addActionListener(this);
      setLayout(new FlowLayout());
      add(yes); add(no);
      setBounds(60,60,100,100);
   }
   public void actionPerformed(ActionEvent e)
   {  if(e.getSource()==yes) 
      { message=YES;setVisible(false);
      }
     else if(e.getSource()==no)
      { message=NO;setVisible(false);
      }
   }
   
   public int getMessage()
   {  return message;
   }
}
