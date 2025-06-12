import  java.awt.*;
public  class  ButtonDir{
  public  static  void  main(String  args[]){
    Frame  f = new  Frame("BorderLayout");
    f.add("North" , new  Button("North"));
    f.add("South" , new  Button("South"));
    f.add("East" , new  Button("East"));
    f.add("West" , new  Button("West"));
    f.add("Center" , new  Button("Center"));
    f.setSize(200 , 200);
    f.setVisible(true);
  }
}
