package cn.jbit.door;

/**
 * �����ࡣ
 * @author ��������
 */
public class DoorTest {
	public static void main(String[] args) {
      //���������������
		TheftproofDoor tfd=new TheftproofDoor();
		tfd.close();  //����
		tfd.lockUp();  //����
		tfd.openLock(); //����
		tfd.open();  //����
	}
}

