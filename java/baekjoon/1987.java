import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 말의 위치에서부터 DFS 탐색을 한다
 * 지나온 알파벳은 boolean 배열을 사용하여 체크한다
 * 막다른 길을 만나면 길이를 갱신한다
 */

public class Main {

	static int R;
	static int C;
	static int max;

	// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean[] isMet; // 알파벳을 만난 적이 있는지 여부
	static boolean[][] isVisited; // 보드 칸 방문 여부
	static char[][] board;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];

		// 보드 저장
		for (int r = 0; r < R; r++) {
			board[r] = in.readLine().toCharArray();
		}

		// 기타 변수 초기화
		isMet = new boolean[26];
		isVisited = new boolean[R][C];
		max = Integer.MIN_VALUE;

		// 시작점 방문 처리
		isVisited[0][0] = true;
		isMet[board[0][0] - 'A'] = true;

		// 탐색
		DFS(1, 0, 0);

		System.out.println(max);
	}

	static void DFS(int length, int row, int col) {
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			int count = 0;
			if (0 <= nr && nr < R && 0 <= nc && nc < C && isMet[board[nr][nc] - 'A'] == false) {
				if (isVisited[nr][nc] == false) {
					count++;
					isVisited[nr][nc] = true;
					isMet[board[nr][nc] - 'A'] = true;
					DFS(length + 1, nr, nc);
					isMet[board[nr][nc] - 'A'] = false;
					isVisited[nr][nc] = false;
				}
			}
			// 막다른 길을 만났을 경우
			if (count == 0) {
				max = Math.max(max, length);
			}
		}
	}

}