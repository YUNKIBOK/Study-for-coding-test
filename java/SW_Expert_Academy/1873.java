import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 각 명령어를 메서드로 만들고 수행한다
 * 게임 맵은 전역 변수 배열에 저장한다
 * 전차는 단 하나만 있다 
 */

public class Solution {

	static char[][] map;
	static char[] commands;
	static int H;
	static int W;
	static int N;
	static int tankR;
	static int tankC;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			// 맵 저장 및 전차 위치 저장
			for (int i = 0; i < H; i++) {
				map[i] = in.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						tankR = i;
						tankC = j;
					}
				}
			}

			N = Integer.parseInt(in.readLine());
			// 명령어 저장 및 수행
			commands = in.readLine().toCharArray();
			for (char command : commands) {
				switch (command) {
				case 'U':
					up();
					break;
				case 'D':
					down();
					break;
				case 'L':
					left();
					break;
				case 'R':
					right();
					break;
				case 'S':
					shoot();
					break;
				}
			}

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	static void up() {
		map[tankR][tankC] = '^';
		int nr = tankR - 1;
		int nc = tankC;
		if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == '.') {
			map[nr][nc] = map[tankR][tankC];
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
		}
	}

	static void down() {
		map[tankR][tankC] = 'v';
		int nr = tankR + 1;
		int nc = tankC;
		if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == '.') {
			map[nr][nc] = map[tankR][tankC];
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
		}
	}

	static void left() {
		map[tankR][tankC] = '<';
		int nr = tankR;
		int nc = tankC - 1;
		if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == '.') {
			map[nr][nc] = map[tankR][tankC];
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
		}
	}

	static void right() {
		map[tankR][tankC] = '>';
		int nr = tankR;
		int nc = tankC + 1;
		if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == '.') {
			map[nr][nc] = map[tankR][tankC];
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
		}
	}

	static void shoot() {
		switch (map[tankR][tankC]) {
		case '^':
			for (int i = tankR - 1; i >= 0; i--) {
				if (map[i][tankC] == '*') {
					map[i][tankC] = '.';
					break;
				} else if (map[i][tankC] == '#') {
					break;
				}
			}
			break;
		case 'v':
			for (int i = tankR + 1; i < H; i++) {
				if (map[i][tankC] == '*') {
					map[i][tankC] = '.';
					break;
				} else if (map[i][tankC] == '#') {
					break;
				}
			}
			break;
		case '<':
			for (int i = tankC - 1; i >= 0; i--) {
				if (map[tankR][i] == '*') {
					map[tankR][i] = '.';
					break;
				} else if (map[tankR][i] == '#') {
					break;
				}
			}
			break;
		case '>':
			for (int i = tankC + 1; i < W; i++) {
				if (map[tankR][i] == '*') {
					map[tankR][i] = '.';
					break;
				} else if (map[tankR][i] == '#') {
					break;
				}
			}
			break;
		}
	}

}