package os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import processManager.Process;

public class FileManager {

	private int parseStack(Scanner sc) {
		return sc.nextInt();
	}

	private int[] parseCode(Scanner sc) {
		int[] codes = new int[13]; 
		int i = 0;
		while (sc.hasNext()) {
			String opcode = sc.next();
			if (opcode.equals("sti")) { //opcode = 0
				int code = 0 << 28;
				code = code + (sc.nextInt() << 14);
				code = code + sc.nextInt();
				codes[i] = code;
			} else if (opcode.equals("cmp")) { //opcode = 1
				int code = 0;
				code = 1 << 28;
				code = code + (sc.nextInt() << 14);
				code = code + sc.nextInt();
				codes[i] = code;
			} else if (opcode.equals("jump")) { //2
				int code = 0;
				code = 2 << 28;
				code = code + (sc.nextInt() << 14);
				codes[i] = code;
			} else if (opcode.equals("adda")) { //3
				int code = 0;
				code = 3 << 28;
				code = code + (sc.nextInt() << 14);
				code = code + sc.nextInt();
				codes[i] = code;
			} else if (opcode.equals("print")) { //4
				int code = 0;
				code = 4 << 28;
				code = code + (sc.nextInt() << 14);
				codes[i] = code;
			} else if (opcode.equals("div")) { //5
				int code = 0;
				code = 5 << 28;
				code = code + (sc.nextInt() << 14);
				code = code + sc.nextInt();
				codes[i] = code;
			} else if (opcode.equals("str")) { //6
				int code = 0;
				code = 6 << 28;
				code = code + (sc.nextInt() << 14);
				code = code + sc.nextInt();
				codes[i] = code;
			} else if (opcode.equals("addd")) { //7
				int code = 0;
				code = 7 << 28;
				code = code + (sc.nextInt() << 14);
				code = code + sc.nextInt();
				codes[i] = code;
			} else { // halt = 8
				int code = 0;
				code = 8 << 28;
				codes[i] = code;
			}
			i++;
		}
		return codes;
	}

	public Process getFile(String fileName) {
		try {
			int stackSegmentSize = 0;
			int[] codes = null;

			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) { // 읽어옴
				String line = scanner.nextLine();
				if (line.substring(0, 5).contentEquals(".code")) { // 코멘트 확인, 비어있는 라인 확인해서 스킵
					codes = this.parseCode(scanner); 
					break;
				} else if (line.substring(0, 6).contentEquals(".stack")) {
					stackSegmentSize = this.parseStack(scanner);
				} else if (line.isEmpty()) {
				}
			}
			Process process = new Process(codes, stackSegmentSize); //파일 다 읽었으니 프로세스 만들어야함
			scanner.close();
			return process;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
