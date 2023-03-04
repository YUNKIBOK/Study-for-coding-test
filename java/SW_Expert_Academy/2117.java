import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 맵 전체를 포함할 수 있는 최대 방범 서비스는 N이 홀수일 때 N이고 N이 짝수일 때 N+1이다
 * 따라서 최대 방범 서비스부터 K를 감소시켜가며 손해를 보지 않는지 검사한다
 * 검사 시 맵의 모든 칸에서 BFS를 k번 실행해본다
 */

public class Solution {

	static int N, M;
	static int houseCnt; // BFS별 서비스 가능한 집 개수
	static int max; // 서비스 가능한 최대 집 개수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited;
	static Queue<Position> queue;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;
			map = new int[N][N];

			// 도시 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 검사해야할 최대 K와 손해가 발생하지 않는지 여부
			int maxRange = N % 2 == 0 ? N + 1 : N;
//			int maxRange = N + 1;
			boolean isPossible = false;

			// K를 줄여가며 모든 정점에서 K만큼 BFS한다
			for (int k = maxRange; k >= 2; k--) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (bfs(new Position(r, c), k)) { // true 반환 시 손해가 나지 않는 최대 K 발견
							isPossible = true;
						}
					}
				}
				// 더 작은 K는 검사할 필요가 없다(범위가 작아지면 포함 가능한 집 개수가 작아지므로)
				if (isPossible) {
					break;
				}
			}

			if (isPossible) {
				sb.append(max);
			} else { // K = 2까지 검사해도 불가능한 경우는 1이 답이다(집이 하나 이상은 존재하므로)
				sb.append(1);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	// 손해가 나면 false, 손해가 나지 않으면 최대 집 개수 갱신 후 true 반환
	static boolean bfs(Position start, int range) {
		houseCnt = 0;
		visited = new boolean[N][N];
		visited[start.r][start.c] = true;
		queue = new ArrayDeque<>();
		queue.add(start);

		// 초기 자금 계산
		int money = (range * range + (range - 1) * (range - 1)) * -1;
		if (map[start.r][start.c] == 1) {
			houseCnt++;
			money += M;
		}

//		System.out.println("range: " + range + " money: " + money);
		range -= 1;
		while (range-- > 0) {
			int qSize = queue.size();
			while (qSize-- > 0) {
				Position current = queue.poll();
				int nr, nc;
				for (int d = 0; d < 4; d++) {
					nr = current.r + dr[d];
					nc = current.c + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] == false) {
						if (map[nr][nc] == 1) {
							money += M;
							houseCnt++;
						}
						visited[nr][nc] = true;
						queue.add(new Position(nr, nc));
					}
				}
			}
		}
//		System.out.println(start.r + " " + start.c + " " + money);

		// 손해가 없으면 최대 포함 가능한 집 개수를 갱신한다
		if (money >= 0) {
//			System.out.println("가능");
//			System.out.println(houseCnt);
			max = Math.max(max, houseCnt);
			return true;
		}

		return false;
	}

}

class Position {

	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

}