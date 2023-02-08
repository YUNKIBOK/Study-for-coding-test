import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 배열 선언 후 시작점부터 시뮬레이션을 한다
 * 1부터 N * N까지 숫자가 배치됨을 이용한다
 */

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
			int[][] map = new int[N][N];

			int direction = 0;
			int count = 1;
			int currentX = 0;
			int currentY = 0;

			while (count <= N * N) {
				// 달팽이 숫자 채우기
				map[currentX][currentY] = count++;
				currentX += dx[direction];
				currentY += dy[direction];

				// 진행 방향 수정 검토
				int rx = currentX + dx[direction];
				int ry = currentY + dy[direction];
				if (rx < 0 || rx >= N || ry < 0 || ry >= N || map[rx][ry] != 0) {
					direction += 1;
					direction %= 4;
				}
			}

			sb.append("#").append(t).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		out.append(sb.toString());
		out.flush();
	}

}
