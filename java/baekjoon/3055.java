import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*

매 분마다 물이 퍼지는 것을 맵에 반영한다
매 분마다 고슴도치를 한 칸 이동 시킨다
물과 고슴도치의 이동을 BFS로 구현하는 데 만약 고슴도치에 도착점에 도달한다면
시간을 반환한다
고슴도치가 도착점에 도달하기 전에 BFS가 종료되면 탈출 불가능한 경우이다 */
public class Main {

	public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		Queue<Position> waters = new ArrayDeque<>();
		Queue<Position> routes = new ArrayDeque<>();
		Position hedgehog = new Position(0, 0), goal = new Position(0, 0);

		// 맵 저장
		char[][] map = new char[R][C];
		char[] rowData;
		for (int r = 0; r < R; r++) {
			rowData = in.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				map[r][c] = rowData[c];
				if (map[r][c] == 'S') {
					hedgehog = new Position(r, c);
					routes.add(new Position(r, c));
				} else if (map[r][c] == 'D') {
					goal = new Position(r, c);
				} else if (map[r][c] == '*') {
					waters.add(new Position(r, c));
				}
			}
		}

		// BFS 준비
		int time = 0;
		boolean[][] isVisited = new boolean[R][C];
		isVisited[hedgehog.r][hedgehog.c] = true;

		// 매분마다 BFS
		loop: while (routes.size() > 0) {
			time++;

			int size = waters.size();
			int nr, nc;

			// 물 퍼트리기
			while (size-- > 0) {
				Position current = waters.poll();
				for (int i = 0; i < 4; i++) {
					nr = current.r + dr[i];
					nc = current.c + dc[i];

					if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] == '.') {
						map[nr][nc] = '*';
						waters.add(new Position(nr, nc));
					}
				}
			}

			size = routes.size();
			// 고슴도치 이동하기
			while (size-- > 0) {
				Position current = routes.poll();
				for (int i = 0; i < 4; i++) {
					nr = current.r + dr[i];
					nc = current.c + dc[i];

					if (0 <= nr && nr < R && 0 <= nc && nc < C && isVisited[nr][nc] == false
							&& (map[nr][nc] == '.' || map[nr][nc] == 'D')) {
						if (nr == goal.r && nc == goal.c) { // 고슴도치가 굴에 도착한 경우
							System.out.println(time);
							break loop;
						} else {
							isVisited[nr][nc] = true;
							routes.add(new Position(nr, nc));
						}
					}

				}
			}

			// 고슴도치가 더 이상 갈 길이 없는 경우
			if (routes.size() == 0) {
				System.out.println("KAKTUS");
				break;
			}
		}
	}
}

// 좌표
class Position {
	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
}