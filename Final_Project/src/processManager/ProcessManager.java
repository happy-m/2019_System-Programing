package processManager;

import os.FileManager;
import os.MemoryManager;

public class ProcessManager { // 프로세스를 만들어서 exe코드를 번역해서 담아서 메모리에 올려야 함

	// components
	private Loader loader;
	private InterruptHandler interruptHandler;
	private Process currentProcess;
	private ProcessQueue readyQueue;
	private ProcessQueue ioQueue;

	// association
	private FileManager fileManager;
	private MemoryManager memoryManager;

	public ProcessManager() { // 프로세스 관리하는 애 > 관리 : 생성하고 소멸하는 것, 그리고 무엇을 하는지 제어해주는 것
		// 파일 프로세스 매니저와 메모리 매니저를 알아야 한다
		this.interruptHandler = new InterruptHandler();
		this.loader = new Loader(); // 프로세스 매니저가 로더의 위치를 알아야한다
		this.readyQueue = new ProcessQueue();
		this.ioQueue = new ProcessQueue();
	}

	public void associate(MemoryManager memoryManager, FileManager fileManager) {
		this.fileManager = fileManager; // 얘가 어딨는지 알아야 한다
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
	} // io발생, 프로그램이 끝나던가 타임슬라이스가 끝나던가 > 루핑에서 빠지는 상태 3가지

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
// 프로세스 매니저가 파일 매니저에서 읽어서 메모리에 올려서 실행한다. 메모리에 올리면 프로세스 정보를 프로세스큐(레디큐)에다가 집어넣는다.
// 하드디스크를 더블클릭하면 로더가 살아난다. 로더가 해당 하드디스크의 exe를 카피해서 메모리에 올린다
// 파일을 리드한다 > 메모리에 올린다. 메모리에 올리면 exe의 헤더를 보고 해석을 한다
// exe에도 파일컨트롤블락이 있는데 그것을 읽어보면 헤더에 어느 위치이고 이름은 뭐고 타입은 뭔지 등에 대한 정보가 다 들어가 있다
// 그걸 읽어서 해석해서 필요한 부분을 PCB에 올리는 것이다
