import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 외판원 순회와 같다
 * 모든 경우를 체계적으로 따질 수 있으면 정답을 맞출 수 있다
 * 회사와 집은 고정되어 있으므로 N 명의 고객을 방문하는 경우의 수 순열로 모두 구해본다
 * 고객은 최대 10명인데 10!은 약 360만 이므로 10개의 테스트 케이스를 모두 수행해도 시간이 넉넉하다
 */

public class Solution {

	static int N;
	// 회사, 집, 고객의 좌표(인덱스 0: x좌표, 인덱스 1: y좌표)
	static int[][] company = new int[1][2];
	static int[][] house = new int[1][2];
	static int[][] customers;
	static int minDistance = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			// 회사 좌표 저장
			company[0][0] = Integer.parseInt(st.nextToken());
			company[0][1] = Integer.parseInt(st.nextToken());
			// 집 좌표 저장
			house[0][0] = Integer.parseInt(st.nextToken());
			house[0][1] = Integer.parseInt(st.nextToken());
			// 고객의 좌표 저장
			customers = new int[N][2];
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customers[i][0] = x;
				customers[i][1] = y;
			}
			// 순열을 활용한 고객 방문 시뮬레이션
			permutation(0, new int[N], new boolean[N]);
			sb.append(String.format("#%d %d\n", t, minDistance));
			minDistance = Integer.MAX_VALUE;
		}
		System.out.println(sb.toString());
	}

	static void permutation(int count, int[] order, boolean[] visited) {
		if (count >= N) {
			int sum = 0;
			// 회사와 첫 번째 고객 사이의 거리 누적
			sum += Math.abs(company[0][0] - customers[order[0]][0]) + Math.abs(company[0][1] - customers[order[0]][1]);
			// i번째 고객과 i + 1번째 고객 사이의 거리 누적
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(customers[order[i]][0] - customers[order[i + 1]][0])
						+ Math.abs(customers[order[i]][1] - customers[order[i + 1]][1]);
			}
			// 마지막 고객과 집 사이의 거리 누적
			sum += Math.abs(house[0][0] - customers[order[N - 1]][0])
					+ Math.abs(house[0][1] - customers[order[N - 1]][1]);
			// 최단 경로 갱신
			minDistance = Math.min(minDistance, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				order[count] = i;
				permutation(count + 1, order, visited);
				visited[i] = false;
			}
		}
	}

}