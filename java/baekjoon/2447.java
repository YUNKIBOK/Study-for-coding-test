import java.util.Scanner;

/*
 * 배열을 생성하고 재귀를 활용하여 배열에 적절한 값을 세팅한다
 * 그리고 출력한다
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = in.nextInt();
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = '*';
			}
		}

		recursive(map, 0, N, 0, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void recursive(char[][] map, int startRow, int endRow, int startCol, int endCol) {
		int length = endRow - startRow;

		for (int i = startRow + length / 3; i < endRow - length / 3; i++) {
			for (int j = startCol + length / 3; j < endCol - length / 3; j++) {
				map[i][j] = ' ';
			}
		}

		if (length == 3) {
			return;
		}

		recursive(map, startRow, startRow + length / 3, startCol, startCol + length / 3);
		recursive(map, startRow, startRow + length / 3, startCol + length / 3, startCol + length * 2 / 3);
		recursive(map, startRow, startRow + length / 3, startCol + length * 2 / 3, startCol + length);

		recursive(map, startRow + length / 3, startRow + length * 2 / 3, startCol, startCol + length / 3);
		recursive(map, startRow + length / 3, startRow + length * 2 / 3, startCol + length * 2 / 3, startCol + length);

		recursive(map, startRow + length * 2 / 3, startRow + length * 3 / 3, startCol, startCol + length / 3);
		recursive(map, startRow + length * 2 / 3, startRow + length * 3 / 3, startCol + length / 3, startCol + length * 2 / 3);
		recursive(map, startRow + length * 2 / 3, startRow + length * 3 / 3, startCol + length * 2 / 3, startCol + length);
	}
	
}