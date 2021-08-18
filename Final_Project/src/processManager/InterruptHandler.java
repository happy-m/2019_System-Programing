package processManager;

import java.util.Vector;

public class InterruptHandler {
	// static�� ���������� �޸𸮸� �Ҵ�
	public enum EInterrupt {
		eNone, eProcessStart, eProcessTerminated, eTimeeStart, eTimeeFinished, eIOStarted, eIOFinished
	}

	public class Interrupt { // ����ü : �����ؼ� �� �� / ������ �������Ϳ� ����
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
	private int front; // queue ��
	private int rear; // queue ��
	private int length; // ���ͷ�Ʈ � ���Դ���
	private int maxLength;

	public InterruptHandler() {
		this.maxLength = 10;
		this.interrupts = new Interrupt[this.maxLength];
		this.front = 0;
		this.rear = 0;
		this.length = 0;
		this.ioDevices = new Vector<IODevice>();
	} // ����� queue�� ���� ���� ���� ��

	public void addInterrupt(Interrupt interrupt) { // ��ŧ�� queue(�R�� ��)
		this.interrupts[this.rear] = interrupt;
		this.rear = (this.rear + 1) % this.maxLength;
	}
	
	public void associate(IODevice ioDevice) {
		this.ioDevices.add(ioDevice);
	}

	public void process() {
		// IHR
		if (length > 0) {
			switch (this.interrupts[front].geteType()) { // thread�� �����
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
