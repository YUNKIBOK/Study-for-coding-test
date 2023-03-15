
import java.util.*;
import java.io.*;

/*
 * DFS 탐색하여 단지를 만들고 단지 내 집 개수를 리스트에 저장한다
 * 리스트를 정렬한 뒤 리스트 길이부터 출력한다
 */
public class Main {

	static int count; // 단지 내 집 개수

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 변수 초기화
		int N = Integer.parseInt(in.readLine());
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int[][] map = new int[N][N];
		boolean[][] isVisited = new boolean[N][N];

		// 지도 저장
		for (int i = 0; i < N; i++) {
			String row = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j) - '0';
			}
		}

		List<Integer> housePerGroup = new ArrayList<>();

		// DFS 수행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && isVisited[i][j] == false) {
					count = 1;
					dfs(housePerGroup, map, isVisited, dr, dc, N, i, j);
					housePerGroup.add(count);
				}
			}
		}

		// 출력
		Collections.sort(housePerGroup);
		sb.append(housePerGroup.size()).append("\n");
		for (Integer i : housePerGroup) {
			sb.append(i).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void dfs(List<Integer> housePerGroup, int[][] map, boolean[][] isVisited, int[] dr, int[] dc, int N, int r,
			int c) {
		isVisited[r][c] = true;
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == 1 && isVisited[nr][nc] == false) {
				count++;
				dfs(housePerGroup, map, isVisited, dr, dc, N, nr, nc);
			}
		}
	}
}
