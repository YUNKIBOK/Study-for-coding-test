import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 * 연산은 순열을 만들어 수행하고 최적의 해를 찾는다
 */

public class Main {

	static int N;
	static int M;
	static int K;
	static int[][] table;
	static Command[] commands;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		table = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 배열 회전 연산 K개 저장
		commands = new Command[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			commands[i] = new Command(r, c, s);
		}

		// 순열 만들어 시뮬레이션 후 배열의 최솟값 갱신
		visited = new boolean[K];
		permutation(0, new Command[K]);
		System.out.println(min);
	}

	static void permutation(int count, Command[] temp) {
		if (count >= K) {
			int[][] tempTable = new int[N + 1][M + 1];
			for (int i = 1; i <= N; i++) {
				System.arraycopy(table[i], 0, tempTable[i], 0, M + 1);
			}
			for (int i = 0; i < K; i++) {
				turnTable(tempTable, temp[i].r, temp[i].c, temp[i].s);
			}
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += tempTable[i][j];
				}
				min = Math.min(min, sum);
			}
			return;
		}

		for (int i = 0; i < K; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				temp[count] = commands[i];
				permutation(count + 1, temp);
				visited[i] = false;
			}
		}
	}

	static void turnTable(int[][] table, int r, int c, int s) {
		int startRow = r - s;
		int endRow = r + s;
		int startCol = c - s;
		int endCol = c + s;
		int rowSize = endRow - startRow + 1;
		int colSize = endCol - startCol + 1;
		int count = Math.min(rowSize, colSize) / 2;
		int currentCount = 0;

		while (currentCount < count) {
			int leftUp = table[startRow][startCol];
			int leftDown = table[endRow][startCol];
			int rightUp = table[startRow][endCol];
			int rightDown = table[endRow][endCol];

			// 테두리 위쪽 회전 처리
			for (int i = endCol; i > startCol; i--) {
				table[startRow][i] = table[startRow][i - 1];
			}

			// 테두리 왼쪽 회전 처리
			for (int i = startRow; i < endRow; i++) {
				table[i][startCol] = table[i + 1][startCol];
			}

			// 테두리 오른쪽 회전 처리
			for (int i = endRow; i > startRow; i--) {
				table[i][endCol] = table[i - 1][endCol];
			}

			// 테두리 아래쪽 회전 처리
			for (int i = startCol; i < endCol; i++) {
				table[endRow][i] = table[endRow][i + 1];
			}

			// 누락된 값 보정
			table[startRow][startCol + 1] = leftUp;
			table[startRow + 1][endCol] = rightUp;
			table[endRow - 1][startCol] = leftDown;
			table[endRow][endCol - 1] = rightDown;

			// 반복을 위한 변수 조정
			startRow++;
			endRow--;
			startCol++;
			endCol--;
			currentCount++;
		}
	}

}

class Command {
	int r;
	int c;
	int s;

	public Command(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}