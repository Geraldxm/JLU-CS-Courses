import java.awt.event.*;
import java.awt.*;

class Dwindow extends Frame implements ActionListener 
{  
   TextArea text; Button button; MyDialog dialog;
   Dwindow(String s)
   {
      super(s);
      text=new TextArea(5,22); button=new Button("�򿪶Ի���"); 
      button.addActionListener(this);
      setLayout(new FlowLayout());
      add(button); add(text); 
      dialog=new MyDialog(this,"����ģʽ",true);
      setBounds(60,60,300,300); setVisible(true);
      //validate();
   }

   public void actionPerformed(ActionEvent e)
   {  if(e.getSource()==button)
        {  dialog.setVisible(true); //�Ի��򼤻�״̬ʱ,�����������䡣
          //�Ի�����ʧ�������������ִ�У�
           if(dialog.getMessage()==MyDialog.YES)   //��������˶Ի����"yes"��ť��
             {  text.append("\n�㵥���˶Ի����yes��ť");
             }
           else if(dialog.getMessage()==MyDialog.NO)   //��������˶Ի����"no"��ť��
            {  text.append("\n�㵥���˶Ի����No��ť");
            }
        }
   }
  public static void main(String args[])
   { 
     new Dwindow("���Ի���Ĵ���");
   }   
}
