package processManager;

import os.MemoryManager;

public class Loader { // exe�� process������ �ٲ㼭 �޸𸮿� �÷��ִ� ����
	
	private MemoryManager memoryManager;
	
	public Process load(Process process) { //�޸𸮿� �÷��� ��, relocation���� etc, �������� �󸶳��� ���� ������ �־�� ��
		//�޸𸮰� ������ �ִٰ� �� �޸𸮿� �־�� �Ѵ�?
		this.memoryManager = new MemoryManager();
		this.memoryManager.allocate(process); 
		return process;
	}
}
