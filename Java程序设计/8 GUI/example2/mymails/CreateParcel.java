package mymails;

import java.awt.*;
import java.awt.event.*;

public class CreateParcel extends Dialog implements ActionListener{

  TextField tSource=new TextField();
  TextField tDest=new TextField();
  TextField tWeight=new TextField();

  Label sourceLabel=new Label("Input Source Address:");
  Label destLabel=new Label("Input Destination Address:");
  Label weightLabel=new Label("Input Weight:");

  Button bCreate=new Button("Create");
  Button bQuit=new Button("Cancel");

  public CreateParcel(Frame f,String s,boolean b){
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