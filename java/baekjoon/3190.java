import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 뱀은 ArrayDeque를 활용하여 저장한다
 * 맵에 뱀을 표현하여 자기 자신에 부딪히는 경우를 구현한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		int K = Integer.parseInt(in.readLine());
		int[][] map = new int[N + 1][N + 1];

		// 우 하 좌 상
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		// 사과 저장
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}

		// 움직임 저장
		int L = Integer.parseInt(in.readLine());
		Queue<Movement> movements = new ArrayDeque<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			movements.add(new Movement(s, c));
		}

		// 시뮬레이션 준비
		ArrayDeque<Position> snake = new ArrayDeque<>();
		snake.add(new Position(1, 1));
		map[1][1] = 2;
		int currentR = 1;
		int currentC = 1;
		int direction = 0;
		int second = 0;

		// 시뮬레이션
		while (true) {
//			System.out.println(second);
//			System.out.println(currentR + " "+currentC);
//			System.out.println(Arrays.toString(snake.toArray()));

			second++;
			int nr = currentR + dr[direction];
			int nc = currentC + dc[direction];

			if (0 == nr || nr == N + 1 || 0 == nc || nc == N + 1 || map[nr][nc] == 2) {
//				System.out.println(nr + " " + nc);
				break;
			}

			snake.addFirst(new Position(nr, nc));
			if (map[nr][nc] == 1) {
				map[nr][nc] = 2;
			} else {
				Position tail = snake.removeLast();
				map[tail.r][tail.c] = 0;
				map[nr][nc] = 2;
			}

			if (movements.size() > 0 && movements.peek().second == second) {
				if (movements.peek().command == 'L') {
					direction -= 1;
					if (direction == -1) {
						direction = 3;
					}
				} else if (movements.peek().command == 'D') {
					direction += 1;
					if (direction == 4) {
						direction = 0;
					}
				}
				movements.poll();
			}

			currentR = nr;
			currentC = nc;
		}

		System.out.println(second);
	}
}

class Movement {

	int second;
	char command;

	public Movement(int second, char command) {
		this.second = second;
		this.command = command;
	}

	@Override
	public String toString() {
		return "Movement [second=" + second + ", command=" + command + "]";
	}

}

class Position {

	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Position [r=" + r + ", c=" + c + "]";
	}

}