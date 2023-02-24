import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * BFS로 구역을 나누고 빨강색 파란색 초록색 구역을 센다
 * 구역을 탐색하면서 재탐색 하지 않도록 색을 바꾼다
 * 빨강-초록 구역을 탐색해야 하므로 빨강, 초록 구역은 동일한 색으로 바꾼다
 */

public class Main {

	static int N;

	static int rCount; // 빨강 구역 개수
	static int gCount; // 초록 구역 개수
	static int bCount; // 파랑 구역 개수
	static int dCount; // 빨강-초록 구역 개수

	// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 그림 정보
	static char[][] painting;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		painting = new char[N][N];

		for (int i = 0; i < N; i++) {
			painting[i] = in.readLine().toCharArray();
		}

		// R, G, B 구역 세기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (painting[i][j] == 'R' || painting[i][j] == 'G' || painting[i][j] == 'B')
					BFS(new Position(i, j));
			}
		}

		// D 구역 세기
		dCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (painting[i][j] == 'D')
					BFS(new Position(i, j));
			}
		}

		sb.append(rCount + gCount + bCount).append(" ").append(dCount + bCount);
		System.out.println(sb.toString());
	}

	static void BFS(Position start) {
		Queue<Position> queue = new ArrayDeque<>();
		queue.add(start);
		// 현재 구역 색
		char color = painting[start.r][start.c];
		// 탐색 후 바꿀 색(R, G -> D / B, D -> 공백)
		char changedColor = 'D';
		if (color == 'R')
			rCount++;
		else if (color == 'G')
			gCount++;
		else if (color == 'B') {
			changedColor = ' ';
			bCount++;
		} else if (color == 'D') {
			changedColor = ' ';
			dCount++;
		}
		painting[start.r][start.c] = changedColor;

		while (queue.size() > 0) {
			Position current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = current.r + dr[i];
				int nc = current.c + dc[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && painting[nr][nc] == color) {
					painting[nr][nc] = changedColor;
					queue.add(new Position(nr, nc));
				}
			}
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