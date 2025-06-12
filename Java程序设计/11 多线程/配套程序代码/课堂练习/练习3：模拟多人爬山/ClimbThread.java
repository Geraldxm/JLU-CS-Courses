package cn.myApp.ex2;

public class ClimbThread extends Thread {
	private int time; // ��100�׵�ʱ��
	public int num = 0; // �����ٸ�100��

	public ClimbThread(String name, int time, int kilometer) {
		super(name);
		this.time = time;
		this.num = kilometer * 1000 / 100;
	}

	public void run() {
		while (num > 0) {
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "����100�ף�");
			num--;
		}
		System.out.println(Thread.currentThread().getName()+"�����յ㣡");
	}
}
