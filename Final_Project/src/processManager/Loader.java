package processManager;

import os.MemoryManager;

public class Loader { // exe를 process구조로 바꿔서 메모리에 올려주는 역할
	
	private MemoryManager memoryManager;
	
	public Process load(Process process) { //메모리에 올려야 함, relocation세팅 etc, 페이지를 얼마나할 건지 가지고 있어야 함
		//메모리가 가지고 있다가 빈 메모리에 주어야 한다?
		this.memoryManager = new MemoryManager();
		this.memoryManager.allocate(process); 
		return process;
	}
}
