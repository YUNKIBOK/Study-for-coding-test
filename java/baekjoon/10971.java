import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백트랙킹(순열)을 사용하여 경로를 탐색한다
 * 탐색 중 갈 수 없는 경우를 만나거나 모든 도시를 만난 경우 탐색을 마치고 다른 경로를 찾는다
 */

public class Main {
	public static int minCost = Integer.MAX_VALUE;
	public static int sum = 0;
	public static boolean[] visited;
	public static int[] route;
	public static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		route = new int[N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backTracking(0, N, N);
		System.out.println(minCost);
	}

	public static void backTracking(int depth, int n, int r) {
		if (depth == r) {
//			for (int i : route) {
//				System.out.print(i + " ");
//			}
//			System.out.println();
			sum = 0;
			for (int i = 0; i < r; i++) {
				if (map[route[i]][route[(i + 1) % r]] == 0 || sum >= minCost) {
					return;
				}
				sum += map[route[i]][route[(i + 1) % r]];
			}

//			System.out.println(sum);
			minCost = Math.min(minCost, sum);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				route[depth] = i;
				backTracking(depth + 1, n, r);
//				데이터를 덮어쓰기 때문에 이 명령어는 실행하지 않아도 됨
//				route[depth] = 0;
				visited[i] = false;
			}
		}
	}
}
