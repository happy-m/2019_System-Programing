package os;

import processManager.Process;
import processManager.ProcessManager;

public class OperatingSystem {
	private UXManager uxManager;

	private ProcessManager processManager;
	private MemoryManager memoryManager;
	private FileManager fileManager;

	public OperatingSystem() {
		this.processManager = new ProcessManager();
		this.memoryManager = new MemoryManager();
		this.fileManager = new FileManager();
		this.uxManager = new UXManager(); // 파일 매니저랑 IO매니저랑 모든 걸 알아야 한다. 모든 걸 오케스트레이션 해준다. 시각적으로 다 보여줘야 하기때문에 다 알아야 한다.
	}
	
	public void associate() {
		this.processManager.associate(this.memoryManager, this.fileManager);
		this.uxManager.associate(this.fileManager, this.processManager); // 프로세스매니저와 파일매니저 알고 있어야 함
	}

	public void run() {
		int i = 1; //process ID
		Process process = this.fileManager.getFile("exe/p1.ppp");
		this.processManager.setReadyQueue(process, i++); //readyQ에 넣는 것
		process = this.fileManager.getFile("exe/p2.ppp");
		this.processManager.setReadyQueue(process, i++);
		while (true) {
			System.out.println(this.processManager.getId() + "번 process 실행 중");
			int interruptNum = this.processManager.execute();
			if (interruptNum == 0) {
				System.out.println("Time-Out");
			} else {
				this.processManager.halt();
				System.out.println("End");
			}
			if (!this.processManager.check()) {
				this.processManager.CSwitching();
			} else {
				System.exit(0);
			}
		}
	}

}
