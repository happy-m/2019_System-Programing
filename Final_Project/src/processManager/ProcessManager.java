package processManager;

import os.FileManager;
import os.MemoryManager;

public class ProcessManager { // ���μ����� ���� exe�ڵ带 �����ؼ� ��Ƽ� �޸𸮿� �÷��� ��

	// components
	private Loader loader;
	private InterruptHandler interruptHandler;
	private Process currentProcess;
	private ProcessQueue readyQueue;
	private ProcessQueue ioQueue;

	// association
	private FileManager fileManager;
	private MemoryManager memoryManager;

	public ProcessManager() { // ���μ��� �����ϴ� �� > ���� : �����ϰ� �Ҹ��ϴ� ��, �׸��� ������ �ϴ��� �������ִ� ��
		// ���� ���μ��� �Ŵ����� �޸� �Ŵ����� �˾ƾ� �Ѵ�
		this.interruptHandler = new InterruptHandler();
		this.loader = new Loader(); // ���μ��� �Ŵ����� �δ��� ��ġ�� �˾ƾ��Ѵ�
		this.readyQueue = new ProcessQueue();
		this.ioQueue = new ProcessQueue();
	}

	public void associate(MemoryManager memoryManager, FileManager fileManager) {
		this.fileManager = fileManager; // �갡 ������� �˾ƾ� �Ѵ�
		this.memoryManager = memoryManager;
	}
	
	public void setReadyQueue(Process process, int i) {
		this.readyQueue.enqueue(process);
		process.setId(i);
	}
	
	public int getId() {
		return this.readyQueue.dequeue().getId();
	}

	public int execute() {
		Process process = this.readyQueue.dequeue();
		long time = 1;
		int pc = process.getPcb();
		while (time > -1) {
			long st = System.currentTimeMillis();
			pc = process.execute(pc);
			long et = System.currentTimeMillis();
			time = time - (et - st);
			if(pc < 0) {
				return -1;
			}
		}
		return 0;
	} // io�߻�, ���α׷��� �������� Ÿ�ӽ����̽��� �������� > ���ο��� ������ ���� 3����

	public void halt() {
		if(!this.readyQueue.halt()) {
			System.out.println("Empty");
		}
	}

	public boolean check() {
		return this.readyQueue.check();
	}

	public void CSwitching() {
		this.readyQueue.CSwitching();
	}
}
// ���μ��� �Ŵ����� ���� �Ŵ������� �о �޸𸮿� �÷��� �����Ѵ�. �޸𸮿� �ø��� ���μ��� ������ ���μ���ť(����ť)���ٰ� ����ִ´�.
// �ϵ��ũ�� ����Ŭ���ϸ� �δ��� ��Ƴ���. �δ��� �ش� �ϵ��ũ�� exe�� ī���ؼ� �޸𸮿� �ø���
// ������ �����Ѵ� > �޸𸮿� �ø���. �޸𸮿� �ø��� exe�� ����� ���� �ؼ��� �Ѵ�
// exe���� ������Ʈ�Ѻ���� �ִµ� �װ��� �о�� ����� ��� ��ġ�̰� �̸��� ���� Ÿ���� ���� � ���� ������ �� �� �ִ�
// �װ� �о �ؼ��ؼ� �ʿ��� �κ��� PCB�� �ø��� ���̴�
