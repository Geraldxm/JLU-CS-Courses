import java.awt.*;
 public class ExGui {
       private Frame f; 
       public static void main(String args[])
        {
           ExGui guiWindow = new ExGui();
           guiWindow.go(); 
         }
      public void go() 
      {
         f = new Frame("GUI example");
         f.setLayout(new GridLayout(3,1));
         f.add(new Checkbox("one",null,true));
         f.add(new Checkbox("two"));
         f.add(new Checkbox("three"));
         f.pack();
         f.setVisible(true);
      }
} 