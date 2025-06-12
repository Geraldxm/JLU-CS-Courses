import java.awt.event.*;
class ButtonListener implements  ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        if( e.getActionCommand()=="Test")
        {
            System.out.println(
                                    "Button Test pressed.");
        }
        else
       {
            System.exit(0);
        }
    }
}
