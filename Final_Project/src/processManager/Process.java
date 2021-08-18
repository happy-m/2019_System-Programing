package processManager;

import java.util.Vector;


public class Process {
	
	private int id;
	// global constants
	enum EState {
		nnew, running, wait, ready, terminated
	};
	public enum ERegister {
		ePC, eCS, eDS, eSS, eHS, eMAR, eMBR, eIR, eStatus, eAC
	}
	public enum EInterrupt { eIO, eTerminate };

	// components
	private PCB pcb;
	private Segment codeSegment;
	private Segment stackSegment;

	// working variables(계산할 때 잠깐 쓰는 값) > get set 필요없음 : attribute와 components만 get set 필요
	// pcb는 노출되어 있어야 한다
	public int getPcb() {
		return pcb.getId();
	}

	public void setPcb(PCB pcb) {
		this.pcb = pcb;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return  this.id;
	}

	public Process(int[] codes, int stackSegmentSize) {
		this.pcb = new PCB();
		this.codeSegment = new Segment(codes);
		this.stackSegment = new Segment(stackSegmentSize);
	}

	public int execute(int pc) {
		int code = this.codeSegment.fetch(pc);
		int opcode = code >> 28; 
		if(opcode == 0) { // opcode = 0 : sti
			int address = code >> 14; 
			int data = code - (address << 14);
			this.stackSegment.store(address, data);
			return ++pc;
		} else if(opcode == 1) { // cmp
			int address = (code - (opcode << 28)) >> 14;
			int data = code - ((opcode << 28) + (address << 14));
			
			if(this.stackSegment.fetch(address) == data) {
				return ++pc;
			} else {
				return pc + 2;
			}
		} else if(opcode == 2) { // jump
			int address = (code - (opcode << 28)) >> 14;
			return address;
		} else if(opcode == 3) { // adda
			int address = (code - (opcode << 28)) >> 14;
			int data = code - ((opcode << 28) + (address << 14));
			this.stackSegment.store(address ,(this.stackSegment.fetch(address) + this.stackSegment.fetch(data)));
			return ++pc;
		} else if(opcode == 4) { // print
			int address = (code - (opcode << 28)) >> 14;
			System.out.println(this.stackSegment.fetch(address));
			return ++pc;
		} else if(opcode == 5) { // div
			int address = (code - (opcode << 28)) >> 14;
			int data = code - ((opcode << 28) + (address << 14));
			this.stackSegment.store(address ,(this.stackSegment.fetch(address) / data));
			return ++pc;
		} else if(opcode == 6) { // str
			int address = (code - (opcode << 28)) >> 14;
			int data = code - ((opcode << 28) + (address << 14));
			this.stackSegment.store(address, this.stackSegment.fetch(data));
			return ++pc;
		} else if(opcode == 7) { // addd
			int address = (code - (opcode << 28)) >> 14;
			int data = code - ((opcode << 28) + (address << 14));
			this.stackSegment.store(address ,(this.stackSegment.fetch(address) + data));
			return ++pc;
		} else { // halt
			return -1;
		}
	}
	
////////////////////////////////////////////////////////
	private class PCB { // CPU를 다 가져와야 한다
		private int id = 0;
		private EState eState;
		private Vector<Register> registers;

		public PCB() {
			this.registers = new Vector<Register>();
			for (ERegister eRegister : ERegister.values()) {
				this.registers.add(new Register());
			}
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public EState geteState() {
			return eState;
		}

		public void seteState(EState eState) {
			this.eState = eState;
		}

		public Vector<Register> getRegisters() {
			return registers;
		}

		public void setRegisters(Vector<Register> registers) {
			this.registers = registers;
		}
	}
	
	private class Segment {
		private int[] memory;

		public Segment(int size) { // stackSegmentSize를 가져온 거
			this.memory = new int[size];
		}

		public Segment(int[] memory) { // codes 가져온 거
			this.memory = memory;
		}

		public void store(int address, int data) {
			this.memory[address] = data;
		}

		public int fetch(int address) {
			return this.memory[address];
		}
	}


	private class Register {
		private int value;
		public Register() {
			
		}
		public int get() {
			return value;
		}
		public void set(int value) {
			this.value = value;
		}
	}

	public boolean checkInterruptStatus() {

		return false;
	}

}
