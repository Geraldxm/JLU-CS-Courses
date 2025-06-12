import  java.awt.*;
import  java.awt.event.*;
public  class  TwoListen  implements MouseMotionListener, MouseListener,WindowListener
{
   private  Frame  f;
   private  TextField  tf; 
   
   public  static  void  main(String  args[]){
    TwoListen  two = new  TwoListen();
    two.go();
  }
  public  void  go(){
    
    f = new  Frame("Two  listeners  example");
    f.add(new  Label("Click and drag the mouse") , "North"); 
    tf = new  TextField(30);
    f.add(tf , "South");
    f.addMouseMotionListener(this);
    f.addMouseListener(this); 
    f.addWindowListener(this);
    f.setSize(300 , 200);
    f.setVisible(true);
  }
  public  void  mouseDragged(MouseEvent  e)
  {
    String  s="Mouse dragging :X="+e.getX()+"Y="+e.getY();
    tf.setText(s);
  }

  public  void  mouseMoved(MouseEvent  e){ }
  public  void  mousePressed(MouseEvent  e){ }
  public  void  mouseReleased(MouseEvent  e){ }
  public  void  mouseEntered(MouseEvent  e)
  {
        String  s = "The mouse entered";
        tf.setText(s);
  }
  public  void  mouseExited(MouseEvent  e){
        String  s = "The mouse exited";
        tf.setText(s);
  }
  public  void  mouseClicked(MouseEvent  e){ }
      public  void  windowClosing(WindowEvent  e){
        System.exit(1);
      }
  public  void  windowOpened(WindowEvent  e){ }
  public  void  windowIconified(WindowEvent  e){ }
  public  void  windowDeiconified(WindowEvent  e){ }
  public  void  windowClosed(WindowEvent  e){ }
  public  void  windowActivated(WindowEvent  e){ }
  public  void  windowDeactivated(WindowEvent  e){ }
    }
  