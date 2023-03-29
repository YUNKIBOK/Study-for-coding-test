import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS를 활용한다
 * 최소 이동 횟수를 구해야 하므로 말처럼 움직이는 것 중 오른쪽 하단으로 이동할 수 있는 두가지만 활용한다
 * -----------------------------------------------------------------------------------------------------
 * 돌아서 가는 경우가 최선인 경우가 있으므로 모든 움직임을 검사한다
 * 방문 체크를 통해 같은 곳에 다시 도달하지 않도록 한다 -> 점프를 지금 말고 나중에 하는 것이 더 유리할 수 있으므로 다시 도달할 수 있어야 한다
 * 방문 배열을 모두 음수로 초기화하고 도착 시 남은 K 개수를 기록한다
 * 남은 K 개수가 더 많은 경로가 도착하면 업데이트 한다
 * 시간 복잡도는 최악의 경우에도 200 * 200 이므로 여유로울 것이다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		int K = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		if (W == 1 && H == 1) {
			System.out.println(0);
			System.exit(0);
		}

		int[] hr = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] hc = { 1, 2, 2, 1, -1, -2, -2, -1 };
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		// 맵 저장
		boolean[][] map = new boolean[H][W];
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
			}
		}

		// BFS 준비
		int[][] isVisited = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(isVisited[i], -1);
		}
		Queue<Position> queue = new ArrayDeque<>();
		queue.add(new Position(K, 0, 0));
		isVisited[0][0] = K; // 0: 방문하지 않은 경우, 1: 인접 방문한 경우, 2: 점프 방문한 경우, 3: 인접/점프 모두 방문한 경우
		int second = 0; // 목표 지점 도달에 걸리는 최소 시간

		loop: while (queue.size() > 0) {
			int size = queue.size();
			while (size-- > 0) {
				Position current = queue.poll();
				int leftK = current.leftK;
				int r = current.r;
				int c = current.c;
				int nr, nc;

//				System.out.println(r + " " + c + " " + leftK);

				// 인접한 장소 이동
				for (int d = 0; d < 4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					if (nr == H - 1 && nc == W - 1) {
						System.out.println(second + 1);
						System.exit(0);
						break loop;
					}
					if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == false && isVisited[nr][nc] < leftK) {
						isVisited[nr][nc] = leftK;
						queue.add(new Position(leftK, nr, nc));
					}
				}

				if (leftK > 0) { // 말처럼 이동할 수 있는 횟수가 남은 경우
					for (int d = 0; d < 8; d++) {
						nr = r + hr[d];
						nc = c + hc[d];
						if (nr == H - 1 && nc == W - 1) {
							System.out.println(second + 1);
							System.exit(0);
						}
//						System.out.println("점프" + nr + " " + nc);
						if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == false
								&& isVisited[nr][nc] < leftK - 1) {
							isVisited[nr][nc] = leftK - 1;
							queue.add(new Position(leftK - 1, nr, nc));
						}
					}
				}
			}
			second++;
			if (isVisited[H - 1][W - 1] != -1) {
				break loop;
			}
		}

		if (isVisited[H - 1][W - 1] != -1) {
			System.out.println(second);
		} else {
			System.out.println(-1);
		}
	}

}

class Position {
	int leftK;
	int r, c;

	public Position(int leftK, int r, int c) {
		this.leftK = leftK;
		this.r = r;
		this.c = c;
	}
}