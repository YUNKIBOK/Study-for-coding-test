import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 색종이를 붙여나간다
 * 개수를 다 쓰면 종료한다
 * 1을 모두 덮으면 종료한다
 */

public class Main {

	static boolean[][] map;
	static int[] numOfPapers;
	static int minPaperCnt;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		map = new boolean[10][10];
		numOfPapers = new int[] { 0, 5, 5, 5, 5, 5 };
		minPaperCnt = Integer.MAX_VALUE;
		int oneCount = 0;

		// 종이 정보 저장 및 1 개수 카운트
		for (int r = 0; r < 10; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < 10; c++) {
				int ch = st.nextToken().charAt(0);
				if (ch == '0')
					map[r][c] = false;
				else {
					map[r][c] = true;
					oneCount++;
				}
			}
		}

		// 색종이 붙이기 시뮬레이션
		DFS(0, 0, oneCount, 0);

		if (minPaperCnt == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minPaperCnt);
	}

	static void DFS(int currentR, int currentC, int oneCount, int count) {
		// 특정 색종이를 초과하여 사용한 경우 종료한다
		for (int i = 1; i <= 5; i++) {
			if (numOfPapers[i] < 0) {
//				System.out.println(i + "크기 색종이 부족");
				return;
			}
		}

		// 색종이로 1을 모두 가린 경우, 종이를 모두 탐색한 경우 종료한다
		if (oneCount == 0 || currentR == 10) {
//			System.out.println("완성 " + count);
			minPaperCnt = Math.min(minPaperCnt, count);
			return;
		}

		// 1인 칸에 대해서 색종이를 붙일 수 있는지 테스트한다
		if (map[currentR][currentC] == true) {
			// 5개 사이즈에 대해 검사
			for (int s = 1; s <= 5; s++) {
				int nr = currentR + s;
				int nc = currentC + s;
				if (nr <= 10 && nc <= 10) {
					boolean isPossible = true;
					check: for (int r = currentR; r < nr; r++) {
						for (int c = currentC; c < nc; c++) {
							if (map[r][c] == false) {
								isPossible = false;
								break check;
							}
						}
					}

					// s 사이즈 색종이를 붙일 수 있으면 붙인다
					if (isPossible) {
						for (int r = currentR; r < nr; r++) {
							for (int c = currentC; c < nc; c++) {
								map[r][c] = false;
							}
						}
						numOfPapers[s] -= 1;
//						System.out.println(currentR + " " + currentC + " " + s);
						DFS(currentR + (currentC + 1) / 10, (currentC + 1) % 10, oneCount - s * s, count + 1);
						numOfPapers[s] += 1;
						for (int r = currentR; r < nr; r++) {
							for (int c = currentC; c < nc; c++) {
								map[r][c] = true;
							}
						}
					}
				}
			}
		} else { // 색종이를 붙일 수 없는 칸이면 행과 열을 옮겨 검사를 이어나간다
			DFS(currentR + (currentC + 1) / 10, (currentC + 1) % 10, oneCount, count);
		}
	}

}