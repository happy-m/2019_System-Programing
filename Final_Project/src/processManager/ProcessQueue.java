package processManager;

import java.util.Vector;

public class ProcessQueue {
	private Vector<Process> processVector = new Vector<Process>();

	public void enqueue(Process process) {
		this.processVector.add(process);
	}

	public Process dequeue() {	
		return this.processVector.get(0);
	}
	
	public void CSwitching() {
		Process process = this.processVector.get(0);
		this.processVector.remove(0);
		this.processVector.add(process);
	}

	public boolean halt() {
		if(!this.processVector.isEmpty()) {
			this.processVector.remove(0);
			return true;
		} else {
			return false;
		}
	}

	public boolean check() {
		return this.processVector.isEmpty();
	}
}
