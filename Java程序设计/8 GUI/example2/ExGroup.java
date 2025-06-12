import java.awt.*;
 public class ExGroup {
       private Frame f; 
       private CheckboxGroup cg;
       public static void main(String args[])
        {
           ExGroup guiWindow = new ExGroup();
           guiWindow.go(); 
        }
       public void go() 
       {
         f = new Frame("GUI example");
         f.setLayout(new GridLayout(3,1));
         cg=new  CheckboxGroup();
	 f.add(new  Checkbox("Checkbox1" , cg , true));
	 f.add(new  Checkbox("Checkbox2" , cg , false));
	 f.add(new  Checkbox("Checkbox3" , cg , false));
         f.pack();
         f.setVisible(true);
      }
} 