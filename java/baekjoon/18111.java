
/*
 * 블록들 중 가장 높이가 낮은 것과 가장 높이가 높은 것 사이의 높이로 평탄화한다
 * 모든 경우의 수를 탐색한다
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int minLevel = Integer.MAX_VALUE;
		int maxLevel = Integer.MIN_VALUE;

		int minTime = Integer.MAX_VALUE;
		int minTimeAt = Integer.MAX_VALUE;

		int[][] ground = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int level = Integer.parseInt(st.nextToken());
				ground[i][j] = level;
				minLevel = Math.min(minLevel, level);
				maxLevel = Math.max(maxLevel, level);
			}
		}

		for (int l = maxLevel; l >= minLevel; l--) {
			int plusLevelCnt = 0;
			int minusLevelCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int level = ground[i][j];
					if (level > l)
						minusLevelCnt += (level - l);
					else if (level < l)
						plusLevelCnt += (l - level);
				}
			}
			if ((minusLevelCnt + B >= plusLevelCnt) && minTime > minusLevelCnt * 2 + plusLevelCnt) {
				minTimeAt = l;
				minTime = minusLevelCnt * 2 + plusLevelCnt;
			}
		}
		
		System.out.println(minTime + " " + minTimeAt);
	}

}
