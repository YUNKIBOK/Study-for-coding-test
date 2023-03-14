
import java.util.*;
import java.io.*;

/*
 * 년마다 BFS를 수행하여 빙산을 녹이고
 * 녹인 빙산들을 DFS 순회하며 덩어리 개수를 구한다
 */
public class Main {

	static int N, M;
	static int tempCount; // 빙산 덩어리 개수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map; // 빙산 지도
	static int[][] tempMap; // BFS용 임시 빙산 지도
	static boolean[][] isVisited; // DFS용 방문 빙산 표시
	static Queue<Position> queue;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		queue = new ArrayDeque<>();

		// 빙산 저장
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] > 0) {
					queue.add(new Position(r, c));
				}
			}
		}

		// 년마다 시뮬레이션
		int year = 0;
		while (queue.size() > 0) {
			// 빙산 덩어리 개수 세기
			tempCount = 0;
			isVisited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0 && isVisited[i][j] == false) {
						tempCount++;
						DFS(i, j);
					}
				}
			}

			// 빙산 덩어리가 2개 이상이면 시뮬레이션 종료
//			System.out.println(tempCount);
			if (tempCount >= 2) {
				break;
			}

			// BFS용 임시 빙산 지도 생성
			tempMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}

			// 빙산 녹이기
			tempCount = queue.size();
			while (tempCount-- > 0) {
				Position current = queue.poll();
				int nr, nc;
				int seaCount = 0;
				for (int i = 0; i < 4; i++) {
					nr = current.r + dr[i];
					nc = current.c + dc[i];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && tempMap[nr][nc] == 0) {
						seaCount++;
					}
				}

				map[current.r][current.c] -= seaCount;
				if (map[current.r][current.c] < 0) {
					map[current.r][current.c] = 0;
				} else { // 빙산이 남은 경우 다음 시뮬레이션에서 다시 녹인다
					queue.add(new Position(current.r, current.c));
				}
			}

//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			year++;
		}

		// 출력
		if (queue.size() == 0) {
			System.out.println(0);
		} else {
			System.out.println(year);
		}
	}

	static void DFS(int r, int c) {
		isVisited[r][c] = true;

		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] > 0 && isVisited[nr][nc] == false) {
				DFS(nr, nc);
			}
		}
	}

}

class Position {
	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
