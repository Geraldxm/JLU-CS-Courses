import java.awt.*;
public class ComponentEvent
{
    public static void main(String args[])
    {
        Frame fr = new Frame("Component Event Test 1.1");
		
        ButtonListener bl = new ButtonListener();

        Button bTest = new Button("Test");
        Button bExit = new Button("Exit");

        bTest.addActionListener(bl);
        bExit.addActionListener(bl);

        fr.setLayout( new FlowLayout() );
        fr.add(bTest);
        fr.add(bExit);
        fr.resize(200,200);
        fr.show();
        }
}
