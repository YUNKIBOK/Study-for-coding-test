import java.util.*;
import java.io.*;

/*
 * 블록을 이동하지 않는 경우부터 최대 5번 이동하는 경우까지
 * 이동에 대한 중복 순열을 만들고 시뮬레이션하여 나올 수 있는 최대 수를 구한다
 * 시간 복잡도는 최악의 경우에 6(0번 이동 ~ 5번 이동) * 20^2(맵 순회) * 6(5번 이동 + 최대 값 구하기위한 맵 순회 1번) * 1024 = 약 1억 4,000만
 * 실제 0번 ~ 4번 이동에서는 맵 순회 횟수가 줄어들므로 시간 안에 해결 가능하다
 */
public class Main {

	static int N;
	static int max;
	static int tempMax; // 시뮬레이션용 임시 최대 수
	static int[][] map;
	static int[][] tempMap; // 시뮬레이션용 임시 맵

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		max = 0;

		// 맵 저장
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 순열 구하고 시뮬레이션
		duplicatedPermutation(0, 0, new int[0]);
		duplicatedPermutation(0, 1, new int[1]);
		duplicatedPermutation(0, 2, new int[2]);
		duplicatedPermutation(0, 3, new int[3]);
		duplicatedPermutation(0, 4, new int[4]);
		duplicatedPermutation(0, 5, new int[5]);
		System.out.println(max);
	}

	static void duplicatedPermutation(int count, int target, int[] commands) {
		if (count >= target) {
//			System.out.println(Arrays.toString(commands));
			// 변수 초기화 및 임시 맵 생성
			tempMax = 0;
			tempMap = new int[N][N];
			for (int r = 0; r < N; r++) {
				System.arraycopy(map[r], 0, tempMap[r], 0, N);
			}

			// 이동 순열에 대한 시뮬레이션
			for (int i = 0; i < target; i++) {
				int command = commands[i];
				switch (command) {
				case 0:
					playUp();
					break;
				case 1:
					playDown();
					break;
				case 2:
					playLeft();
					break;
				case 3:
					playRigth();
					break;
				}
			}

			// 임시 최대 수 구하기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					tempMax = Math.max(tempMax, tempMap[r][c]);
				}
			}

//			if (target == 1) {
//				for (int p = 0; p < N; p++) {
//					System.out.println(Arrays.toString(tempMap[p]));
//				}
//				System.out.println();
//			}

			max = Math.max(max, tempMax);
			return;
		}

		for (int i = 0; i < 4; i++) {
			commands[count] = i;
			duplicatedPermutation(count + 1, target, commands);
		}
	}

	static void playUp() {
		for (int c = 0; c < N; c++) {
			Queue<Integer> queue = new ArrayDeque<>();
			boolean isCombined = false;
			// 존재하는 수 순서에 맞게 큐에 삽입
			for (int r = 0; r < N; r++) {
				if (tempMap[r][c] != 0) {
					queue.add(tempMap[r][c]);
					tempMap[r][c] = 0;
				}
			}
			// 큐에서 수를 빼면서 합칠 수 있으면 합치고 아니면 밀어서 이동하기
			int prev = 0, current = 0;
			int count = 0;
			while (queue.size() > 0) {
				if (prev == 0) {
					prev = queue.poll();
					tempMap[count++][c] = prev;
					continue;
				}

				current = queue.poll();
				if (prev == current && isCombined == false) {
					tempMap[count - 1][c] += current;
					prev *= 2;
					isCombined = true;
				} else {
					tempMap[count++][c] = current;
					prev = current;
					isCombined = false;
				}
			}
		}
	}

	static void playDown() {
		for (int c = 0; c < N; c++) {
			Queue<Integer> queue = new ArrayDeque<>();
			boolean isCombined = false;
			for (int r = N - 1; r >= 0; r--) {
				if (tempMap[r][c] != 0) {
					queue.add(tempMap[r][c]);
					tempMap[r][c] = 0;
				}
			}
			int prev = 0, current = 0;
			int count = N - 1;
			while (queue.size() > 0) {
				if (prev == 0) {
					prev = queue.poll();
					tempMap[count--][c] = prev;
					continue;
				}

				current = queue.poll();
				if (prev == current && isCombined == false) {
					tempMap[count + 1][c] += current;
					prev *= 2;
					isCombined = true;
				} else {
					tempMap[count--][c] = current;
					prev = current;
					isCombined = false;
				}
			}
		}
	}

	static void playLeft() {
		for (int r = 0; r < N; r++) {
			Queue<Integer> queue = new ArrayDeque<>();
			boolean isCombined = false;
			for (int c = 0; c < N; c++) {
				if (tempMap[r][c] != 0) {
					queue.add(tempMap[r][c]);
					tempMap[r][c] = 0;
				}
			}
			int prev = 0, current = 0;
			int count = 0;
			while (queue.size() > 0) {
				if (prev == 0) {
					prev = queue.poll();
					tempMap[r][count++] = prev;
					continue;
				}

				current = queue.poll();
				if (prev == current && isCombined == false) {
					tempMap[r][count - 1] += current;
					prev *= 2;
					isCombined = true;
				} else {
					tempMap[r][count++] = current;
					prev = current;
					isCombined = false;
				}
			}
		}
	}

	static void playRigth() {
		for (int r = 0; r < N; r++) {
			Queue<Integer> queue = new ArrayDeque<>();
			boolean isCombined = false;
			for (int c = N - 1; c >= 0; c--) {
				if (tempMap[r][c] != 0) {
					queue.add(tempMap[r][c]);
					tempMap[r][c] = 0;
				}
			}
			int prev = 0, current = 0;
			int count = N - 1;
			while (queue.size() > 0) {
				if (prev == 0) {
					prev = queue.poll();
					tempMap[r][count--] = prev;
					continue;
				}

				current = queue.poll();
				if (prev == current && isCombined == false) {
					tempMap[r][count + 1] += current;
					prev *= 2;
					isCombined = true;
				} else {
					tempMap[r][count--] = current;
					prev = current;
					isCombined = false;
				}
			}
		}
	}
}