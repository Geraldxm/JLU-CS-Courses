import java.awt.*;
public class ExChoice {
       private Frame f; 
       public static void main(String args[])
        {
           ExChoice guiWindow = new ExChoice();
           guiWindow.go(); 
        }
       public void go() 
       {
         f = new Frame("GUI example");
         f.setLayout(new FlowLayout());
         Choice c=new  Choice();
         c.add("Green");
         c.add("Red");
         c.add("Blue");
	 f.add(c);
         f.pack();
         f.setVisible(true);
      }
} 