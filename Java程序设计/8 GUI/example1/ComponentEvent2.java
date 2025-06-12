import java.awt.*;
import  java.awt.event.*;
public class ComponentEvent2
{
    public static void main(String args[])
    {
        Frame fr = new Frame("Component Event Test 1.1");
		
        ButtonListener bl = new ButtonListener();

        Button b1 = new Button("Test");
        Button b2 = new Button("Exit");

        b1.addActionListener(bl);
        b2.addActionListener(bl);

        fr.setLayout( new FlowLayout() );
        fr.add(b1);
        fr.add(b2);
        fr.setSize(200,200);
        fr.show();
        }
}