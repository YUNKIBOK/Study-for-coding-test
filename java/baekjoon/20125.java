import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 상하좌우에 쿠키가 위치한 칸이 심장이다
 * 심장을 구한 뒤 각 부분의 길이를 구한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		char[][] map = new char[N][N];
		for (int r = 0; r < N; r++) {
			map[r] = in.readLine().toCharArray();
		}
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int heartR = 0, heartC = 0;

		loop: for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int cnt = 0;
				int nr, nc;
				for (int d = 0; d < 4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N) {
						if (map[nr][nc] == '*') {
							cnt++;
						}
					}
				}
				if (cnt == 4) {
					heartR = r;
					heartC = c;
					break loop;
				}
			}
		}

		sb.append(heartR + 1).append(" ").append(heartC + 1).append("\n");

		int len = 0;
		for (int i = heartC - 1; i >= 0; i--) {
			if (map[heartR][i] == '*') {
				len++;
			} else {
				break;
			}
		}
		sb.append(len).append(" ");

		len = 0;
		for (int i = heartC + 1; i < N; i++) {
			if (map[heartR][i] == '*') {
				len++;
			} else {
				break;
			}
		}
		sb.append(len).append(" ");

		len = 0;
		int beltR = 0;
		for (int i = heartR + 1; i < N; i++) {
			if (map[i][heartC] == '*') {
				len++;
			} else {
				beltR = i - 1;
				break;
			}
		}
		sb.append(len).append(" ");

		len = 0;
		for (int i = beltR + 1; i < N; i++) {
			if (map[i][heartC - 1] == '*') {
				len++;
			} else {
				break;
			}
		}
		sb.append(len).append(" ");

		len = 0;
		for (int i = beltR + 1; i < N; i++) {
			if (map[i][heartC + 1] == '*') {
				len++;
			} else {
				break;
			}
		}
		sb.append(len).append(" ");

		System.out.println(sb.toString());
	}

}
