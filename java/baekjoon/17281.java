import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 안타부터 아웃까지 메서드를 만들고 시뮬레이션한다
 */
public class Main {

	static int N;
	static int max; // 가장 많은 득점
	static int outCount; // 타순 이닝별 아웃 개수
	static int tempScore; // 타순 시뮬레이션별 득점
	static int currentPlayer; // 시뮬레이션 과정에서 현재 플레이어의 타순
	static int[][] players; // 선수 이닝별 결과
	static boolean[] runners; // 주자 현황

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		max = Integer.MIN_VALUE;
		players = new int[9 + 1][N];

		// 선수 이닝별 결과 저장
		for (int c = 0; c < N; c++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int r = 1; r <= 9; r++) {
				players[r][c] = Integer.parseInt(st.nextToken());
			}
		}

//		for (int c = 0; c < N; c++) {
//			for (int r = 1; r <= 9; r++) {
//				System.out.print(players[r][c] + " ");
//			}
//			System.out.println();
//		}

		// 타순별 시뮬레이션을 수행한다
		permutation(0, new int[9], new boolean[9 + 1]);
		System.out.println(max);
	}

	static void permutation(int count, int[] order, boolean[] visited) {
		if (count >= 9) {
//			System.out.println(Arrays.toString(order));
			outCount = 0;
			tempScore = 0;
			currentPlayer = 0;
			runners = new boolean[3 + 1];
			// 이닝을 반복한다
			for (int n = 0; n < N; n++) {
				while (true) {
					int command = players[order[currentPlayer]][n];
					if (command == 1) { // 안타
						runners[0] = true;
						run();
						runners[0] = false;
					} else if (command == 2) { // 2루타
						runners[0] = true;
						run();
						runners[0] = false;
						run();
					} else if (command == 3) { // 3루타
						runners[0] = true;
						run();
						runners[0] = false;
						run();
						run();
					} else if (command == 4) { // 홈런
						runners[0] = true;
						run();
						runners[0] = false;
						run();
						run();
						run();
					} else if (command == 0) { // 아웃
						if (out(currentPlayer)) {
							break;
						}
					}
					currentPlayer = (currentPlayer + 1) % 9;
				}
			}
//			System.out.println(tempScore);
			max = Math.max(max, tempScore);

			return;
		}

		// 순열 구하기
		if (count == 4 - 1) {
			order[count] = 1;
			permutation(count + 1, order, visited);
		} else {
			for (int i = 2; i <= 9; i++) {
				if (visited[i] == false) {
					visited[i] = true;
					order[count] = i;
					permutation(count + 1, order, visited);
					visited[i] = false;
				}
			}
		}
	}

	// 주자를 1루 이동하는 메서드
	static void run() {
		if (runners[3] == true) {
			tempScore++;
		}
		for (int i = 3; i >= 1; i--) {
			runners[i] = runners[i - 1];
		}
	}

	// 아웃을 반영하는 메서드
	static boolean out(int player) {
		if (outCount == 2) { // 아웃이 3개가 되는 상황이면 true를 반환한다
			currentPlayer = (player + 1) % 9;
			outCount = 0;
			runners = new boolean[3 + 1];
			return true;
		} else {
			outCount++;
			return false;
		}
	}

}