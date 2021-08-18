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
		this.uxManager = new UXManager(); // ���� �Ŵ����� IO�Ŵ����� ��� �� �˾ƾ� �Ѵ�. ��� �� ���ɽ�Ʈ���̼� ���ش�. �ð������� �� ������� �ϱ⶧���� �� �˾ƾ� �Ѵ�.
	}
	
	public void associate() {
		this.processManager.associate(this.memoryManager, this.fileManager);
		this.uxManager.associate(this.fileManager, this.processManager); // ���μ����Ŵ����� ���ϸŴ��� �˰� �־�� ��
	}

	public void run() {
		int i = 1; //process ID
		Process process = this.fileManager.getFile("exe/p1.ppp");
		this.processManager.setReadyQueue(process, i++); //readyQ�� �ִ� ��
		process = this.fileManager.getFile("exe/p2.ppp");
		this.processManager.setReadyQueue(process, i++);
		while (true) {
			System.out.println(this.processManager.getId() + "�� process ���� ��");
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
