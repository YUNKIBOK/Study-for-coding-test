import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS를 초마다 수행한다
 * 큐가 비면 더 이상 전파할 수 없는 것인데 이 때 익지 않은 토마토가 남아있으면
 * 토마토가 모두 익지 못하는 상황이다
 * 시간 복잡도는 N * M 이하이다
 */

public class Main {

	static int N, M;
	static int zeroCnt;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] box;
	static Queue<Position> queue;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		zeroCnt = 0;
		box = new int[N][M];
		queue = new ArrayDeque<Position>();

		// 토마토 저장, 안 익은 토마토 개수 카운트, BFS 시작 점 추가
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
				if (box[r][c] == 0) {
					zeroCnt++;
				} else if (box[r][c] == 1) {
					queue.add(new Position(r, c));
				}
			}
		}

		// 초마다 BFS
		int second = 0;
		while (queue.size() > 0) {
			second++;
			int qSize = queue.size();
			while (qSize-- > 0) {
				Position current = queue.poll();
				int nr, nc;
				for (int j = 0; j < 4; j++) {
					nr = current.r + dr[j];
					nc = current.c + dc[j];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && box[nr][nc] == 0) {
						box[nr][nc] = 1;
						queue.add(new Position(nr, nc));
						zeroCnt--;
					}
				}
			}
		}

		if (zeroCnt == 0) {
			System.out.println(second - 1);
		} else {
			System.out.println(-1);
		}
	}

}

class Position {

	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

}