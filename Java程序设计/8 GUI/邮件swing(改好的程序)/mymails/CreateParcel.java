package mymails;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateParcel extends JDialog implements ActionListener{

  /**
	 * 
	 */
	private static final long serialVersionUID = 5146116677335395903L;
JTextField tSource=new JTextField();
  JTextField tDest=new JTextField();
  JTextField tWeight=new JTextField();

  JLabel sourceLabel=new JLabel("Input Source Address:");
  JLabel destLabel=new JLabel("Input Destination Address:");
  JLabel weightLabel=new JLabel("Input Weight:");

  JButton bCreate=new JButton("Create");
  JButton bQuit=new JButton("Cancel");

  public CreateParcel(JFrame f,String s,boolean b){
    super(f,s,b);

    bCreate.addActionListener(this);
    bQuit.addActionListener(this);


    this.setLayout(new GridLayout(4,2));
    this.add(sourceLabel);
    this.add(tSource);
    this.add(destLabel);
    this.add(tDest);
    this.add(weightLabel);
    this.add(tWeight);
    this.add(bCreate);
    this.add(bQuit);
    this.setSize(350,200);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
          if( e.getActionCommand()=="Create")
          {
             ShowMails.putMails(new PostParcel(sourceLabel.getText(),destLabel.getText(),Double.parseDouble(tWeight.getText())));
          }
          else if(e.getActionCommand()=="Cancel")
          {
              this.setVisible(false);
          }
  }

}