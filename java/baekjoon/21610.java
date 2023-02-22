import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N * N 격자를 만든다
 * 구름 지도와 구름 소멸 지도를 만들어 활용한다
 * 구름 지도는 큐들의 배열로 만들어서 구름 이동을 원할하게 구현한다
 * 시뮬레이션한다
 */

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] clouds;
	static boolean[][] disappearedClouds;
	static int[][] commands;
	static int[] dr = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 바구니 정보 저장
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령어 정보 저장
		commands = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			commands[i][0] = Integer.parseInt(st.nextToken());
			commands[i][1] = Integer.parseInt(st.nextToken());
		}

		// 초기 구름 생성
		clouds = new boolean[N][N];
		clouds[N - 1][0] = true;
		clouds[N - 1][1] = true;
		clouds[N - 2][0] = true;
		clouds[N - 2][1] = true;

		// 초기 소멸 구름 생성
		disappearedClouds = new boolean[N][N];

		// 명령어 수행
		for (int i = 0; i < M; i++) {
			int d = commands[i][0];
			int s = commands[i][1];
			// 시뮬레이션
			simulate(d, s);
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	static void simulate(int d, int s) {
		// 구름 위치 d방향으로 s만큼 이동
		for (int i = 0; i < s; i++) {
			// 행 이동
			if (d == 2 || d == 3 || d == 4) { // 위로 올리기
				boolean[] tempRow = Arrays.copyOf(clouds[0], N);
				for (int j = 0; j < N - 1; j++) {
					clouds[j] = clouds[j + 1];
				}
				clouds[N - 1] = Arrays.copyOf(tempRow, N);
			} else if (d == 6 || d == 7 || d == 8) {// 아래로 내리기
				boolean[] tempRow = Arrays.copyOf(clouds[N - 1], N);
				for (int j = N - 1; j > 0; j--) {
					clouds[j] = clouds[j - 1];
				}
				clouds[0] = Arrays.copyOf(tempRow, N);
			}

			// 열 이동
			if (d == 1 || d == 2 || d == 8) { // 왼쪽으로 옮기기
				for (int j = 0; j < N; j++) {
					boolean tempValue = clouds[j][0];
					for (int k = 0; k < N - 1; k++) {
						clouds[j][k] = clouds[j][k + 1];
					}
					clouds[j][N - 1] = tempValue;
				}
			} else if (d == 4 || d == 5 || d == 6) { // 오른쪽으로 옮기기
				for (int j = 0; j < N; j++) {
					boolean tempValue = clouds[j][N - 1];
					for (int k = N - 1; k > 0; k--) {
						clouds[j][k] = clouds[j][k - 1];
					}
					clouds[j][0] = tempValue;
				}
			}
		}

		// 구름이 있는 칸의 바구니에 저장된 물의 양 1만큼 증가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (clouds[i][j] == true) {
					map[i][j] += 1;
				}
			}
		}

		// 소멸 구름 위치 저장
		for (int i = 0; i < N; i++) {
			System.arraycopy(clouds[i], 0, disappearedClouds[i], 0, N);
		}

		// 물복사버그 마법 시전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (clouds[i][j] == true) {
					int count = 0;
					if (0 <= i - 1 && 0 <= j - 1 && map[i - 1][j - 1] > 0)
						count++;
					if (0 <= i - 1 && j + 1 < N && map[i - 1][j + 1] > 0)
						count++;
					if (i + 1 < N && 0 <= j - 1 && map[i + 1][j - 1] > 0)
						count++;
					if (i + 1 < N && j + 1 < N && map[i + 1][j + 1] > 0)
						count++;
					map[i][j] += count;
				}
			}
		}

		// 새로운 구름 생성
		clouds = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2 && disappearedClouds[i][j] == false) {
					clouds[i][j] = true;
					map[i][j] -= 2;
				}
			}
		}
	}

}