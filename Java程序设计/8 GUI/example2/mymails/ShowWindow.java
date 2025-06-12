package mymails;

import java.awt.*;
import java.awt.event.*;

public class ShowWindow extends Frame  implements ActionListener{

  private MenuBar mb=new MenuBar();

  private Menu mCreate= new Menu("Create");
  private MenuItem create1=new MenuItem("Create a PostParcel");
  private MenuItem create2=new MenuItem("Create a PostEmit");
  private MenuItem create3=new MenuItem("Quit");

  private Menu mView= new Menu("Functions");
  private MenuItem view1=new MenuItem("View all mails");
  private MenuItem view2=new MenuItem("Post all mails");

  private Panel pl=new Panel();
  private Label welcome=new Label("Welcome  ChinaPost!");

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
    this.setMenuBar(mb);
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