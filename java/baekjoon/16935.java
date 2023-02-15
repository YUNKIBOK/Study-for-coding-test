import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제와 같이 R번 시뮬레이션한다
 * 4번 연산은 3번 연산을 3번 수행한 것과 같다
 * 6번 연산은 5번 연산을 3번 수행한 것과 같다
 * 3번과 4번 연산 수행 시 N과 M이 바뀌는 것에 주의한다
 * N, M은 모두 짝수이다
 * 복사를 통해 임시 2차원 배열을 생성할 때 참조 값이 아닌 실제 원소 값을 복사해야 하는 것에 주의한다
 */

public class Main {
	static int[][] table;
	static int N;
	static int M;
	static int R;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		// 테이블 생성
		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령 수행
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < R; i++) {
			int command = Integer.parseInt(st.nextToken());
//			System.out.println(command);
			switch (command) {
			case 1:
				operation1();
				break;
			case 2:
				operation2();
				break;
			case 3:
				operation3();
				break;
			case 4:
				operation4();
				break;
			case 5:
				operation5();
				break;
			case 6:
				operation6();
				break;
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(String.format("%d ", table[i][j]));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void operation1() {
		for (int i = 0; i < N / 2; i++) {
			int[] temp = table[i];
			table[i] = table[N - 1 - i];
			table[N - 1 - i] = temp;
		}
	}

	static void operation2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				int temp = table[i][j];
				table[i][j] = table[i][M - 1 - j];
				table[i][M - 1 - j] = temp;
			}
		}
	}

	static void operation3() {
		int[][] tempTable = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(table[i], 0, tempTable[i], 0, M);
//			System.out.println(Arrays.toString(temp[i]));
		}

		// N, M 갱신
		int temp = N;
		N = M;
		M = temp;

		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				table[i][j] = tempTable[M - 1 - j][i];
			}
		}
	}

	static void operation4() {
		operation3();
		operation3();
		operation3();
	}

	static void operation5() {
		int[][] tempTable = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(table[i], 0, tempTable[i], 0, M);
		}

		// 구 4구역 -> 신 1구역
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				table[i][j] = tempTable[i + N / 2][j];
			}
		}

		// 구 1구역 -> 신 2구역
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				table[i][j] = tempTable[i][j - M / 2];
			}
		}

		// 구 2구역 -> 신 3구역
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				table[i][j] = tempTable[i - N / 2][j];
			}
		}

		// 구 3구역 -> 신 4구역
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				table[i][j] = tempTable[i][j + M / 2];
			}
		}
	}

	static void operation6() {
		operation5();
		operation5();
		operation5();
	}
}