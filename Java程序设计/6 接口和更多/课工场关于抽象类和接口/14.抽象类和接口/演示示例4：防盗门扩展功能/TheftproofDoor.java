package cn.jbit.door;

public class TheftproofDoor extends Door implements Lock, DoorBell {

	@Override
	public void lockUp() {
		System.out.println("���Կ�ף�������תԿ����Ȧ�������ˣ��γ�Կ�ס�");
	}

	@Override
	public void openLock() {
		System.out.println("���Կ�ף�������תԿ����Ȧ�������ˣ��γ�Կ�ס�");

	}

	@Override
	public void open() {
		System.out.println("�����ƣ��Ŵ��ˡ�");
	}

	@Override
	public void close() {
		System.out.println("�������ţ��Ź����ˡ�");
	}
	
	@Override
	public void takePictures() {
		System.out.println("��......����......��Ƭ�Ѵ洢");

	}
}
