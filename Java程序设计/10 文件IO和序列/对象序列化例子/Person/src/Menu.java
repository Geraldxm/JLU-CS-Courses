public class Menu{
	public Menu(){
		while(true){
			this.show() ;		// �����Ƶ��ò˵�����ʾ
		}
	}
	public void show(){
		System.out.println("===== Xxxϵͳ =====") ;
		System.out.println("    [1]����������") ;
		System.out.println("    [2]��ɾ������") ;
		System.out.println("    [3]���޸�����") ;
		System.out.println("    [4]���鿴����") ;
		System.out.println("    [0]��ϵͳ�˳�\n") ;
		InputData input = new InputData() ;
		int i = input.getInt("��ѡ��","��������ȷ��ѡ�") ;
		switch(i){
			case 1:{
				Operate.add() ;		// �������Ӳ���
				break ;
			}
			case 2:{
				Operate.delete() ;	// ����ɾ������
				break ;
			}
			case 3:{
				Operate.update() ;	// ���ø��²���
				break ;
			}
			case 4:{
				Operate.find() ;		// ���ò鿴����
				break ;
			}
			case 0:{
				System.exit(1) ;		// ϵͳ�˳�
				break ;
			}
			default:{
				System.out.println("��ѡ����ȷ�Ĳ�����") ;
			}
		}
	}
};