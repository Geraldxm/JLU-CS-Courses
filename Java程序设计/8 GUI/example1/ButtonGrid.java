import  java.awt.*;
public  class  ButtonGrid{
  public  static  void  main(String  args[]){
     Frame  f = new  Frame("GridLayout");
     f.setLayout(new  GridLayout(3 , 2));
     f.add(new  Button("1"));
     f.add(new  Button("2"));
     f.add(new  Button("3"));
     f.add(new  Button("4"));
     f.add(new  Button("5"));
     f.add(new  Button("6"));
     f.setSize(200 , 200);
     f.setVisible(true);
  }
}
