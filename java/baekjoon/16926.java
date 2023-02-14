import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시작 행, 종료 행, 시작 열, 종료 열을 매개변수로 받아 배열을 1회 돌리는 사용자 정의 메서드를 만든다
 * 회전을 하다보면 다시 처음 배열로 돌아온다
 * 회전을 R번 반복하지 말고 나머지 연산자를 활용해 불필요한 회전을 줄인다
 * 원소는 1억 이하이므로 int 타입을 사용한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] array = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				array[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0;
		int min = Math.min(N, M);
		while (count < (min / 2)) { // 사각형 테두리를 줄여가며 반복한다
			int rowLength = N - 2 * count;
			int colLength = M - 2 * count;
			int turnCount = R % (rowLength * 2 + colLength * 2 - 4); // 나머지 연산자로 회전 횟수를 줄인다
//			System.out.println(count + "번 테두리 " + turnCount + "번 왼쪽으로 회전");
			for (int p = 0; p < turnCount; p++) {
				turnLeft(array, 0 + count, N - 1 - count, 0 + count, M - 1 - count);
			}
			count += 1;
		}

		// 출력한다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(array[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void turnLeft(int[][] array, int upRow, int downRow, int leftCol, int rightCol) {
		// 좌상, 좌하, 우상, 우하 값을 저장해둔다
		int upLeft = array[upRow][leftCol];
		int downLeft = array[downRow][leftCol];
		int downRight = array[downRow][rightCol];
		int upRight = array[upRow][rightCol];

		// 테두리 윗 부분 회전
		for (int i = leftCol; i <= rightCol; i++) {
			if (i + 1 <= rightCol)
				array[upRow][i] = array[upRow][i + 1];
		}

		// 테두리 왼쪽 부분 회전
		for (int i = downRow; i >= upRow; i--) {
			if (i - 1 >= upRow) {
				array[i][leftCol] = array[i - 1][leftCol];
			}
		}

		// 테두리 아랫 부분 회전
		for (int i = rightCol; i >= leftCol; i--) {
			if (i - 1 >= leftCol)
				array[downRow][i] = array[downRow][i - 1];
		}

		// 테두리 오른쪽 부분 회전
		for (int i = upRow; i <= downRow; i++) {
			if (i + 1 <= downRow) {
				array[i][rightCol] = array[i + 1][rightCol];
			}
		}

		// 누락된 값 갱신
		array[upRow + 1][leftCol] = upLeft;
		array[downRow][leftCol + 1] = downLeft;
		array[downRow - 1][rightCol] = downRight;
		array[upRow][rightCol - 1] = upRight;
	}

}