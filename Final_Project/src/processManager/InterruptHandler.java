package processManager;

import java.util.Vector;

public class InterruptHandler {
	// static은 고정적으로 메모리를 할당
	public enum EInterrupt {
		eNone, eProcessStart, eProcessTerminated, eTimeeStart, eTimeeFinished, eIOStarted, eIOFinished
	}

	public class Interrupt { // 구조체 : 공유해서 쓸 것 / 원래는 레지스터에 있음
		private EInterrupt eType;
		private Object parameter;

		public Interrupt() {
			this.eType = EInterrupt.eNone;
			this.parameter = null;
		}

		public EInterrupt geteType() {
			return eType;
		}

		public void seteType(EInterrupt eType) {
			this.eType = eType;
		}

		public Object getParameter() {
			return parameter;
		}

		public void setParameter(Object parameter) {
			this.parameter = parameter;
		}
	}
	private Vector<IODevice> ioDevices;

	private Interrupt[] interrupts;
	private int front; // queue 앞
	private int rear; // queue 뒤
	private int length; // 인터럽트 몇개 들어왔는지
	private int maxLength;

	public InterruptHandler() {
		this.maxLength = 10;
		this.interrupts = new Interrupt[this.maxLength];
		this.front = 0;
		this.rear = 0;
		this.length = 0;
		this.ioDevices = new Vector<IODevice>();
	} // 여기는 queue로 따로 빼서 만들 것

	public void addInterrupt(Interrupt interrupt) { // 써큘러 queue(뻉뺑 돎)
		this.interrupts[this.rear] = interrupt;
		this.rear = (this.rear + 1) % this.maxLength;
	}
	
	public void associate(IODevice ioDevice) {
		this.ioDevices.add(ioDevice);
	}

	public void process() {
		// IHR
		if (length > 0) {
			switch (this.interrupts[front].geteType()) { // thread로 띄워라
			case eProcessStart:
				break;
			case eProcessTerminated:
				break;
			case eTimeeStart:
				break;
			case eTimeeFinished:
				break;
			case eIOStarted:
				break;
			case eIOFinished:
				break;
			default:
				break;
			}
			this.front = (this.front + 1) % this.maxLength;
			this.length--;
		}
	}
}
