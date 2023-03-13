
import java.util.*;
import java.io.*;

/*
 * BFS로 탐색하고 토마토가 익는 것을 시뮬레이션한다
 * BFS는 초마다 실행한다
 */
public class Main {

	static int N, M, H;
	static int time; // BFS 소요 시간
	static int leftCount; // 잔여 익지 않은 토마토 개수
	static int[][][] apples;

	static Queue<Position> queue;

	// 위 아래 왼쪽 오른쪽 앞 뒤
	static int[] dh = { -1, 1, 0, 0, 0, 0 };
	static int[] dn = { 0, 0, 0, 0, 1, -1 };
	static int[] dm = { 0, 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		time = 0;
		leftCount = 0;

		apples = new int[H][N][M];
		queue = new ArrayDeque<>();
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int m = 0; m < M; m++) {
					apples[h][n][m] = Integer.parseInt(st.nextToken());
					if (apples[h][n][m] == 1) {
						queue.add(new Position(h, n, m));
					} else if (apples[h][n][m] == 0) {
						leftCount++;
					}
				}
			}
		}

		while (queue.size() > 0) {
			int qSize = queue.size();
			while (qSize-- > 0) {
				Position current = queue.poll();
				int nh, nn, nm;
				for (int d = 0; d < 6; d++) {
					nh = current.h + dh[d];
					nn = current.n + dn[d];
					nm = current.m + dm[d];
					if (0 <= nh && nh < H && 0 <= nn && nn < N && 0 <= nm && nm < M && apples[nh][nn][nm] == 0) {
						apples[nh][nn][nm] = 1;
						leftCount--;
						queue.add(new Position(nh, nn, nm));
					}
				}
			}
			if (queue.isEmpty()) {
				break;
			}
			time++;
		}

		if (leftCount > 0) {
			System.out.println(-1);
		} else {

			System.out.println(time);
		}
	}

}

class Position {

	int h, n, m;

	public Position(int h, int n, int m) {
		this.h = h;
		this.n = n;
		this.m = m;
	}

}
