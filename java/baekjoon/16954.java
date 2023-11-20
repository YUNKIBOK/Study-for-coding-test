import java.io.*;
import java.util.*;

/*
 * 조건에 맞게 시뮬레이션 한다.
 */
public class Main {

	static BufferedReader br;
	static char[][] map; // 미로 지도
	static Queue<Integer> queue; // BFS를 위한 큐
	static int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 8방 탐색
	static int[] dc = { 1, 1, 0, -1, -1, -1, 0, 1 }; // 8방 탐색
	static char[] newLine = { '.', '.', '.', '.', '.', '.', '.', '.' }; // 새로운 미로 첫 행
	static boolean[][] isVisited; // BFS 턴별 방문여부

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[8][8];

		// 지도 셋팅
		for (int i = 0; i < 8; i++) {
			char[] row = br.readLine().toCharArray();
			map[i] = row;
		}

		// BFS 시작 준비
		queue = new ArrayDeque<>();
		queue.add(7 * 8 + 0);

		// BFS
		while (!queue.isEmpty()) {
			int size = queue.size();
			isVisited = new boolean[8][8];

			// 턴별 BFS하며 방문여부를 기록(현재 위치 서있기, 같은 위치 다시 오기 등을 위해서)
			for (int s = 0; s < size; s++) {
				int current = queue.poll();
				int cr = current / 8;
				int cc = current % 8;

				// 미로 탈출
				if (cr == 0 && cc == 7) {
					System.out.println(1);
					System.exit(0);
				}

				// 미로가 움직여 벽이 됐으면 캐릭터는 더 이상 움직일 수 없다.
				if (map[cr][cc] == '#') {
					continue;
				}

				// 제자리에 서있기
				if (isVisited[cr][cc] == false) {
					isVisited[cr][cc] = true;
					queue.add(cr * 8 + cc);
				}

				// 8방 탐색
				int nr, nc;
				for (int d = 0; d < 8; d++) {
					nr = cr + dr[d];
					nc = cc + dc[d];
					if (0 <= nr && nr < 8 && 0 <= nc && nc < 8 && map[nr][nc] == '.' && isVisited[nr][nc] == false) {
						isVisited[nr][nc] = true;
						queue.add(nr * 8 + nc);
					}
				}
			}

			// 미로 움직이기
			for (int r = 7; r >= 1; r--) {
				map[r] = map[r - 1];
			}
			map[0] = newLine;
		}

		System.out.println(0);
	}
}