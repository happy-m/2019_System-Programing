package os;

import processManager.ProcessManager;

public class UXManager {
	
	private FileManager fileManager;
	private ProcessManager processManager; //ux�� ���μ��� �Ŵ����� �˰� �ִ�
	
	public UXManager() {
	
	}

	public void associate(FileManager fileManager, ProcessManager processManager) {
		this.fileManager = fileManager;
		this.processManager = processManager;
	}
	
	public void run() { // ���μ��� �Ŵ����� �����Ų �� 
		//Scanner scanner = new Scanner(System.in);
		//String fileName = scanner.nextLine();
		//Process process = this.fileManager.getFile(fileName);
		//this.processManager.execute(process); //���������� ���ƾ� �� thread��!
		// ���μ��� �Ŵ����� �޸𸮸Ŵ����� �˾ƾ��Ѵ�
	}
	// UI�� ����Ŭ���ϸ� �δ����� ���α׷������� ����Ѵ� ���μ����� ����� ������ �ش� ��ġ���� �ø��� ��?
}
