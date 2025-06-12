import java.awt.*;
public class MyFrame extends Frame {
      public static void main (String args[]) {
         MyFrame fr = new MyFrame("Hello Out There!");
         fr.setSize(500,500);
         fr.setBackground(Color.blue);
         fr.setVisible(true);
       }
       public MyFrame (String str) {
            super(str);
       }
}
