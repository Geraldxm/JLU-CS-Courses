import java.awt.*;
public class FrameWithPanel extends Frame { 
      public FrameWithPanel (String str) {
      super (str);
     }
  public static void main (String args[]) {
      FrameWithPanel fr = new FrameWithPanel ("Frame with Panel");
         Panel pan = new Panel();
        fr.setSize(200,200);
        fr.setBackground(Color.blue); 
        fr.setLayout(null); //override default layout mgr
        pan.setSize (100,100);
        pan.setBackground(Color.yellow);
       fr.add(pan);
       fr.setVisible(true);
  } 
}
