
import java.util.*;
import java.io.*;

/*
 * (N-1)*H 개의 점선 중 3개를 선택한 뒤 부분 조합으로 선택한 점선 연결 여부를 결정한다
 * 그리고 시뮬레이션한다
 * 시간 복잡도는 최악의 경우 (9*30C3)*(2^3) < 2억이다
 * ---------------------------------------------------------
 * 시간 초과 발생으로 DFS를 사용하기로 했다
 * 점선을 실선으로 바꾸어 나가고 목표 점선 개수에 도달하면 시뮬레이션 한다
 */
public class Main {

	static int N, M, H;
	static int min;
	static int lineMaxCount; // 실선으로 바꿔 볼 점선 최대 개수
	static boolean[][] lines; // 선 정보(true면 실선, false면 점선)

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		lineMaxCount = (N - 1) * H - M >= 3 ? 3 : H; // 최대 3개까지만 바꾸어 보면 된다, 바꿀 수 있는 점선 개수가 3개 이상이면 3으로 고정한다
		lines = new boolean[H + 1][N];

		// 실선 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lines[a][b] = true;
		}

		// 시뮬레이션
		for (int i = 0; i <= lineMaxCount; i++) { // 0개부터 최대 바꿀 수 있는 개수까지 시뮬레이션한다
			simulate(0, 0, i);
			if (min != Integer.MAX_VALUE) { // 최소 값을 찾으면 반복을 시뮬레이션을 중단한다
				break;
			}
		}

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	static void simulate(int count, int start, int lineCount) {
		if (count >= lineCount) {
			boolean isPossible = true;
			checkLadder: for (int c = 1; c <= N; c++) {
				int currentR = 0;
				int currentC = c;
				while (currentR < H) {
					currentR++;
					if (1 <= currentC - 1 && lines[currentR][currentC - 1] == true) {
						currentC -= 1;
						continue;
					}
					if (currentC <= N - 1 && lines[currentR][currentC] == true) {
						currentC += 1;
						continue;
					}
				}
				if (currentC != c) {
					isPossible = false;
					break checkLadder;
				}
			}

			if (isPossible == true) {
				min = Math.min(min, lineCount);
			}
			return;
		}

		for (int i = start; i < (H + 1) * N; i++) {
			int r = i / N;
			int c = i % N;
			if (r == 0 || c == 0) { // 인덱스 범위를 벗어난 경우
				continue;
			}
			if (lines[r][c] == true) { // 실선으로 주어진 경우
				continue;
			}
			if (1 <= c - 1 && lines[r][c - 1] == true) { // 왼쪽 실선과 이어지는 경우
				continue;
			}
			if (c + 1 <= N - 1 && lines[r][c + 1] == true) { // 오른쪽 실선과 이어지는 경우
				continue;
			}
			lines[r][c] = true;
			simulate(count + 1, i + 1, lineCount);
			lines[r][c] = false;
		}
	}

}