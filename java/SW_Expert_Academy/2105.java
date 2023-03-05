import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

/*
 * DFS 탐색으로 대각선 직사각형을 만들어본다
 * 만들어지면 가장 많이 먹을 수 있는 디저트 수를 갱신한다
 */

public class Solution {

	static int max;
	static int startR, startC;
	static int[] dr = { 1, -1, -1, 1 };
	static int[] dc = { 1, 1, -1, -1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			// 변수 초기화
			int N = Integer.parseInt(in.readLine());
			max = Integer.MIN_VALUE;
			int[][] map = new int[N][N];

			// 2차원 배열에 디저트 정보 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// r, c를 시작점으로 DFS 수행하여 마름모 모양 경로 탐색
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					startR = r;
					startC = c;
//					System.out.println(r + " " + c);
					DFS(N, map, r, c, new boolean[N][N], new boolean[100 + 1], 0, 0);
				}
			}

			if (max == Integer.MIN_VALUE) {
				sb.append(-1);
			} else {
				sb.append(max);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static void DFS(int N, int[][] map, int r, int c, boolean[][] visited, boolean[] eat, int d, int count) {
		// 디저트를 하나 이상 먹은 경로 중 다시 시작점으로 돌아온 경우 갱신한다
		if (count != 0 && r == startR && c == startC) {
			max = Math.max(max, count);
//			System.out.println(max + " " +count);
			return;
		}

		// 이미 먹은 디저트라면 탐색을 종료한다
		if (eat[map[r][c]] == true) {
			return;
		}

		// 방문했던 경로라면 탐색을 종료한다
		if (visited[r][c] == true) {
			return;
		}

		// 방문 및 디저트 먹음 처리
		visited[r][c] = true;
		eat[map[r][c]] = true;

		// 같은 방향으로 전진해본다
		int nr = r + dr[d];
		int nc = c + dc[d];
		if (0 <= nr && nr < N && 0 <= nc && nc < N) {
			DFS(N, map, nr, nc, visited, eat, d, count + 1);
		}

		// 같은 방향으로 더 이상 갈 수 없다면 90도 회전하여 전진을 시도한다
		// 90도 회전 전진도 불가능한 경우 종료한다
		if (d + 1 >= 4) {
			visited[r][c] = false;
			eat[map[r][c]] = false;
			return;
		}

		// 90도 회전 후 전진
		nr = r + dr[d + 1];
		nc = c + dc[d + 1];
		if (0 <= nr && nr < N && 0 <= nc && nc < N) {
			DFS(N, map, nr, nc, visited, eat, d + 1, count + 1);
		}

		// 90도 회전 후 전진까지 시도해봤다면 방무 및 디저트 먹음 처리를 해제한다
		visited[r][c] = false;
		eat[map[r][c]] = false;
	}

}
