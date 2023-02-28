import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 * 5,000만 번 시뮬레이션하여 무한 루프 여부를 알아낸다
 * 무한 루프 안에 유한 루프가 있을 수 있으므로 무한 루프로 판별된 경우 또 5,000만 번 시뮬레이션하여 탈출하지 못해 접근 가능한 '['의 최소 인덱스와 ']'의 최대 인덱스를 구한다
 */
public class Main {

	static int m;
	static int c;
	static int i;
	static char[] datas;
	static char[] codes;
	static char[] inputs;
	static int dataPointer;
	static int codePointer;
	static int inputPointer;
	static int loopStartPointer;
	static int loopEndPointer;
	static int balance; // '['와 ']' 짝 찾기에 사용하는 변수

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());

			// 변수 초기화
			datas = new char[m];
			codes = new char[c];
			inputs = new char[i];
			dataPointer = 0;
			codePointer = 0;
			inputPointer = 0;
			loopStartPointer = Integer.MAX_VALUE;
			loopEndPointer = Integer.MIN_VALUE;
			balance = 0;

			// 명령어 저장
			codes = in.readLine().toCharArray();

			// 입력 저장
			inputs = in.readLine().toCharArray();

			// 프로그램 무한 루프 테스트
			boolean isLoop = true;
			for (int k = 0; k <= 50_000_000; k++) { // 50,000,000번 수행하고 딱 종료된 경우 하단 명령어를 수행하지 못하므로 50,000,001번 수행한다
				// 무한 루프에 빠지지 않는 경우
				if (codePointer >= c) {
					sb.append("Terminates").append("\n");
					isLoop = false;
					break;
				}

				char command = codes[codePointer];
				switch (command) {
				case '-':
					if (datas[dataPointer] == 0) {
						datas[dataPointer] = 255;
					} else {
						datas[dataPointer] -= 1;
					}
					codePointer++;
					break;
				case '+':
					if (datas[dataPointer] == 255) {
						datas[dataPointer] = 0;
					} else {
						datas[dataPointer] += 1;
					}
					codePointer++;
					break;
				case '<':
					if (dataPointer == 0) {
						dataPointer = m - 1;
					} else {
						dataPointer -= 1;
					}
					codePointer++;
					break;
				case '>':
					if (dataPointer == m - 1) {
						dataPointer = 0;
					} else {
						dataPointer += 1;
					}
					codePointer++;
					break;
				case '[':
					if (datas[dataPointer] == 0) {
						balance -= 1;
						while (balance != 0) {
							codePointer++;
							if (codes[codePointer] == '[') {
								balance -= 1;
							} else if (codes[codePointer] == ']') {
								balance += 1;
							}
						}
						codePointer++;
					} else {
						codePointer++;
					}
					break;
				case ']':
					if (datas[dataPointer] != 0) {
						balance += 1;
						while (balance != 0) {
							codePointer--;
							if (codes[codePointer] == ']') {
								balance += 1;
							} else if (codes[codePointer] == '[') {
								balance -= 1;
							}
						}
						codePointer++;
					} else {
						codePointer++;
					}
					break;
				case '.':
					codePointer++;
					break;
				case ',':
					if (inputPointer >= i) {
						datas[dataPointer] = 255;
					} else {
						datas[dataPointer] = inputs[inputPointer++];
					}
					codePointer++;
					break;
				}
			}

			// 무한 루프에 빠진 경우
			if (isLoop) {
				for (int k = 0; k < 50_000_000; k++) {
					loopEndPointer = Math.max(loopEndPointer, codePointer);
					loopStartPointer = Math.min(loopStartPointer, codePointer - 1);

					char command = codes[codePointer];
					switch (command) {
					case '-':
						if (datas[dataPointer] == 0) {
							datas[dataPointer] = 255;
						} else {
							datas[dataPointer] -= 1;
						}
						codePointer++;
						break;
					case '+':
						if (datas[dataPointer] == 255) {
							datas[dataPointer] = 0;
						} else {
							datas[dataPointer] += 1;
						}
						codePointer++;
						break;
					case '<':
						if (dataPointer == 0) {
							dataPointer = m - 1;
						} else {
							dataPointer -= 1;
						}
						codePointer++;
						break;
					case '>':
						if (dataPointer == m - 1) {
							dataPointer = 0;
						} else {
							dataPointer += 1;
						}
						codePointer++;
						break;
					case '[':
						if (datas[dataPointer] == 0) {
							balance -= 1;
							while (balance != 0) {
								codePointer++;
								if (codes[codePointer] == '[') {
									balance -= 1;
								} else if (codes[codePointer] == ']') {
									balance += 1;
								}
							}
							codePointer++;
						} else {
							codePointer++;
						}
						break;
					case ']':
						if (datas[dataPointer] != 0) {
							balance += 1;
							while (balance != 0) {
								codePointer--;
								if (codes[codePointer] == ']') {
									balance += 1;
								} else if (codes[codePointer] == '[') {
									balance -= 1;
								}
							}
							codePointer++;
						} else {
							codePointer++;
						}
						break;
					case '.':
						codePointer++;
						break;
					case ',':
						if (inputPointer >= i) {
							datas[dataPointer] = 255;
						} else {
							datas[dataPointer] = inputs[inputPointer++];
						}
						codePointer++;
						break;
					}
				}

				sb.append("Loops ").append(loopStartPointer).append(" ").append(loopEndPointer).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

}