package mymails;

import java.awt.*;
import java.awt.event.*;

public class CreateEmit extends Dialog implements ActionListener{

  TextField tSource=new TextField();
  TextField tDest=new TextField();
  TextField tValue=new TextField();

  Label sourceLabel=new Label("Input Source Address:");
  Label destLabel=new Label("Input Destination Address:");
  Label valueLabel=new Label("Input Value:");

  Button bCreate=new Button("Create");
  Button bQuit=new Button("Cancel");

  public CreateEmit(Frame f,String s,boolean b){
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