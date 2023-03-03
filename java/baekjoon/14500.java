import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/*
 * 각 점에서 DFS를 3번 수행하고 최고 합계를 갱신한다 -> 'ㅜ' 모양을 제거한 모든 모양을 만들 수 있다
 * 'ㅜ' 모양은 DFS로 만들 수 없으므로 각 칸을 순회하며 4방향 중 가장 작은 것을 제외한 합으로 갱신한다
 */
public class Main {

	static int max; // 테틀로미노가 놓인 칸 수들의 최대 합
	static int N, M;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited; // DFS 방문 확인용

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		max = Integer.MIN_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		// 맵 저장
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// DFS로 'ㅜ' 모양을 제거한 테트로미노 검사
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				DFS(r, c, map[r][c], 0);
				visited[r][c] = false;
			}
		}

		// 맵을 순회하며 'ㅜ' 모양 테트로미노 검사
		int sum;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sum = map[r][c];
				int count = 0;
				int min = Integer.MAX_VALUE;
				int nr, nc;

				for (int d = 0; d < 4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < M) {
						sum += map[nr][nc];
						count++;
						min = Math.min(min, map[nr][nc]);
					}
				}
				if (count < 3) {
					continue;
				} else if (count == 3) {
					max = Math.max(max, sum);
				} else {
					max = Math.max(max, sum - min);
				}
			}
		}

		System.out.println(max);
	}

	static void DFS(int r, int c, int sum, int count) {
		if (count >= 3) {
			max = Math.max(max, sum);
			return;
		}

		int nr, nc;
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] == false) {
				visited[nr][nc] = true;
				DFS(nr, nc, sum + map[nr][nc], count + 1);
				visited[nr][nc] = false;
			}
		}
	}

}