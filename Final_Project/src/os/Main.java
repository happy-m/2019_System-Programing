package os;

public class Main { 

	static public class BIOS { //������ Ű�� ���� 0������ Ű�� ���̿������� �� ���� > ���̿����� OSȣ��
		//�ʱ�ȭ�Ҷ� PC�� 0�� ����ִµ� �װ� rom�ּ�
		public void run() {}
	}
	
	public static void main(String[] args) {
		BIOS bios = new BIOS();
		bios.run();
		//�ϵ���������� cpu�� �޸𸮴� ����Ǿ��־�� �Ѵ� < �ϵ��� ��(�������忡 ���� �� �ִ� �ű���� �ϵ��� ����Ǿ��ִ�) ���� Ű�鼭 Ű���� ����� ���� bios�� ��Ʈ��ũ���� ������(I/O)
		// > �׷��� ������ ��ǲ �ƿ�ǲ�� �ְ� ���� �� �ִ� ���̿����� OS�� ������ �� �ְ� ���ش� ���Ŀ� OS�� ��� �͵��� �ٽ� �����Ѵ� 
		OperatingSystem operatingSystem = new OperatingSystem(); // os > ��ö�ӽ�(������ os�� �����ؼ� os��ſ� ��ɾ �ִ� �ӽ�> ���� os�� �Ȱ��� ����)���� ���� ����
		// os������ ����
		operatingSystem.run();
	}
}
