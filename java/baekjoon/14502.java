import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 3개의 벽을 세우는 경우의 수를 조합으로 구한다
 * 조합 결과를 순회하면서 벽을 설치하고 바이러스가 BFS로 퍼지는 것을 시뮬레이션한다
 * 안전 영역의 최대 크기를 갱신해나간다
 */

public class Main {

	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		List<Position> viruses = new ArrayList<Position>();
		List<Position> spaces = new ArrayList<Position>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				if (value == 0) {
					spaces.add(new Position(i, j));
				} else if (value == 2) {
					viruses.add(new Position(i, j));
				}
			}
		}

		// 3개의 벽 설치 좌표 조합 구하기
		List<Position[]> combResults = new ArrayList<Position[]>();
		comb(0, 0, spaces, new Position[3], combResults);

		// 3개의 벽 설치 조합 순회하며 시뮬레이션
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < combResults.size(); i++) {
			// 임시 맵 생성
			int[][] tempMap = new int[N][M];
			for (int j = 0; j < N; j++) {
				System.arraycopy(map[j], 0, tempMap[j], 0, M);
			}
			// 벽 설치
			for (int p = 0; p < 3; p++) {
				int wallR = combResults.get(i)[p].getRow();
				int wallC = combResults.get(i)[p].getCol();
				tempMap[wallR][wallC] = 1;
			}
			// BFS로 바이러스 퍼트리기
			for (int q = 0; q < viruses.size(); q++) {
				BFS(viruses.get(q), tempMap);
			}
			// 안전 구역 크기 계산
			int count = 0;
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < M; b++) {
					if (tempMap[a][b] == 0)
						count++;
				}
			}
			// 최대 안전 구역 크기 갱신
			max = Math.max(max, count);
		}
		System.out.println(max);
	}

	public static void comb(int count, int start, List<Position> spaces, Position[] tempResult,
			List<Position[]> results) {
		if (count >= 3) {
			results.add(Arrays.copyOfRange(tempResult, 0, 3));
			return;
		}

		for (int i = start; i < spaces.size(); i++) {
			tempResult[count] = spaces.get(i);
			comb(count + 1, i + 1, spaces, tempResult, results);
		}
	}

	public static void BFS(Position start, int[][] map) {
		Queue<Position> queue = new LinkedList<Position>();
		queue.add(start);
		map[start.getRow()][start.getCol()] = 2;

		while (!queue.isEmpty()) {
			Position current = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = current.getRow() + dr[i];
				int nc = current.getCol() + dc[i];
				if (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length && map[nr][nc] == 0) {
					map[nr][nc] = 2;
					queue.add(new Position(nr, nc));
				}
			}
		}
	}

}

class Position {
	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}
}
