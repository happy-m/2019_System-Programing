package os;

public class Main { 

	static public class BIOS { //전원을 키면 롬의 0번지를 키고 바이오스부터 쫙 시작 > 바이오스가 OS호출
		//초기화할때 PC에 0을 집어넣는데 그게 rom주소
		public void run() {}
	}
	
	public static void main(String[] args) {
		BIOS bios = new BIOS();
		bios.run();
		//하드웨어적으로 cpu와 메모리는 연결되어있어야 한다 < 하드웨어가 함(마더보드에 롬이 들어가 있다 거기까지 하드웨어가 연결되어있다) 롬을 키면서 키보드 모니터 등을 bios가 네트워크까지 연동함(I/O)
		// > 그래서 유저가 인풋 아웃풋을 주고 받을 수 있다 바이오스가 OS도 선택할 수 있게 해준다 이후에 OS가 모든 것들을 다시 선택한다 
		OperatingSystem operatingSystem = new OperatingSystem(); // os > 버철머신(가상의 os를 가정해서 os대신에 명령어를 주는 머신> 겉은 os와 똑같이 생김)으로 만들 예정
		// os생성자 생성
		operatingSystem.run();
	}
}
