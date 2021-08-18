package os;

import processManager.ProcessManager;

public class UXManager {
	
	private FileManager fileManager;
	private ProcessManager processManager; //ux가 프로세스 매니저를 알고 있다
	
	public UXManager() {
	
	}

	public void associate(FileManager fileManager, ProcessManager processManager) {
		this.fileManager = fileManager;
		this.processManager = processManager;
	}
	
	public void run() { // 프로세스 매니저를 실행시킨 것 
		//Scanner scanner = new Scanner(System.in);
		//String fileName = scanner.nextLine();
		//Process process = this.fileManager.getFile(fileName);
		//this.processManager.execute(process); //독립적으로 돌아야 함 thread로!
		// 프로세스 매니저는 메모리매니저를 알아야한다
	}
	// UI가 더블클릭하면 로더한테 프로그램파일을 줘야한다 프로세스를 만들어 놓으면 해당 위치까지 올리는 것?
}
