package mymails;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowWindow extends JFrame  implements ActionListener{

  /**
	 * 
	 */
	private static final long serialVersionUID = 4682620976913344475L;

private JMenuBar mb=new JMenuBar();

  private JMenu mCreate= new JMenu("Create");
  private JMenuItem create1=new JMenuItem("Create a PostParcel");
  private JMenuItem create2=new JMenuItem("Create a PostEmit");
  private JMenuItem create3=new JMenuItem("Quit");

  private JMenu mView= new JMenu("Functions");
  private JMenuItem view1=new JMenuItem("View all mails");
  private JMenuItem view2=new JMenuItem("Post all mails");

  private JPanel pl=new JPanel();
  private JLabel welcome=new JLabel("Welcome  ChinaPost!");

  public ShowWindow(String s){
     super(s);
    try{
     init();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  private void init() throws Exception {

    mb.add(mCreate);

    mCreate.add(create1);
    mCreate.add(create2);
    mCreate.addSeparator();
    mCreate.add(create3);
    create1.addActionListener(this);
    create2.addActionListener(this);
    create3.addActionListener(this);

    mb.add(mView);
    mView.add(view1);
    mView.add(view2);
    view1.addActionListener(this);
    view2.addActionListener(this);

    pl.add(welcome);
    this.setJMenuBar(mb);
    this.setLayout(new FlowLayout());
    this.add(pl);
  }

  public static void main(String[] args){

    ShowWindow sw=new ShowWindow("My Test");
    sw.setSize(200,300);
    sw.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
          if( e.getActionCommand()=="Create a PostParcel")
          {
             new CreateParcel(this,"Create Parcel",true);
          }
          else if(e.getActionCommand()=="Create a PostEmit")
          {
             new CreateEmit(this,"Create Remittance",true);
          }
          else if(e.getActionCommand()=="View all mails")
          {

          }
          else if(e.getActionCommand()=="Post all mails")
          {

          }
          else if(e.getActionCommand()=="Quit")
          {
              System.exit(0);
          }
  }

}