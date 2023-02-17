import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 조합을 통해 M개의 치킨집 좌표를 구한다
 * M개의 치킨집을 뽑았으면 도시를 순회하며 하나의 집에서 치킨 거리를 구한다
 * 최솟값을 갱신한다
 */

public class Main {

	static int N;
	static int M;
	static int[][] city;
	static List<Position> chickenHouses;
	static int minChickenDistance = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N + 1][N + 1];
		chickenHouses = new ArrayList<>();
		// 도시 정보 저장
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					city[i][j] = value;
				} else if (value == 2) {
					chickenHouses.add(new Position(i, j));
				}
			}
		}
		// 치킨 집 조합 후 시뮬레이션
		calMinChickenDistance(0, 0, new int[M]);
		System.out.println(minChickenDistance);
	}

	static void calMinChickenDistance(int count, int start, int[] survived) {
		if (count >= M) {
			int tempChickenDistance = 0;
			for (int p = 1; p <= N; p++) {
				for (int q = 1; q <= N; q++) {
					if (city[p][q] == 1) { // 집이면 치킨 거리를 구한다
						int unitChickenDistance = Integer.MAX_VALUE;
						for (int r = 0; r < M; r++) {
							unitChickenDistance = Math.min(Math.abs(chickenHouses.get(survived[r]).r - p)
									+ Math.abs(chickenHouses.get(survived[r]).c - q), unitChickenDistance);
						}
						// 하나의 집에 대한 치킨 거리를 누적한다
						tempChickenDistance += unitChickenDistance;
					}
				}
			}
			// 도시의 치킨 거리 최솟값을 갱신한다
			minChickenDistance = Math.min(minChickenDistance, tempChickenDistance);
			return;
		}
		for (int i = start; i < chickenHouses.size(); i++) {
			survived[count] = i;
			calMinChickenDistance(count + 1, i + 1, survived);
		}
	}
}

class Position {

	int r;
	int c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

}