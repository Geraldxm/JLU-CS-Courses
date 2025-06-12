package mymails;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateEmit extends JDialog implements ActionListener{

  /**
	 * 
	 */
	private static final long serialVersionUID = -3003302482588941544L;
JTextField tSource=new JTextField();
  JTextField tDest=new JTextField();
  JTextField tValue=new JTextField();

  JLabel sourceLabel=new JLabel("Input Source Address:");
  JLabel destLabel=new JLabel("Input Destination Address:");
  JLabel valueLabel=new JLabel("Input Value:");

  JButton bCreate=new JButton("Create");
  JButton bQuit=new JButton("Cancel");

  public CreateEmit(JFrame f,String s,boolean b){
    super(f,s,b);

    bCreate.addActionListener(this);
    bQuit.addActionListener(this);


    this.setLayout(new GridLayout(4,2));
    this.add(sourceLabel);
    this.add(tSource);
    this.add(destLabel);
    this.add(tDest);
    this.add(valueLabel);
    this.add(tValue);
    this.add(bCreate);
    this.add(bQuit);
    this.setSize(350,200);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
          if( e.getActionCommand()=="Create")
          {
             ShowMails.putMails(new Postemit(sourceLabel.getText(),destLabel.getText(),Double.parseDouble(tValue.getText())));
          }
          else if(e.getActionCommand()=="Cancel")
          {
              this.setVisible(false);
          }
  }

}