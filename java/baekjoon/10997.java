import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// create 2D array
		int colSize = 4 * N - 3;
		int rowSize = N != 1 ? colSize + 2 : 1;
		char[][] map = new char[rowSize][colSize];

		// draw rectangle
		for (int i = 0; i < 2 * N - 1; i++) {
			char content = i % 2 == 0 ? '*' : ' ';
			char specialContent = content == '*' ? ' ' : '*';
			for (int j = i; j < rowSize - i; j++) {
				for (int k = i; k < colSize - i; k++)
					map[j][k] = content;
			}
			// special process
			if (i + 1 < rowSize && colSize - i - 1 > 0)
				map[i + 1][colSize - i - 1] = specialContent;
		}
		// special process
		map[rowSize / 2][colSize / 2] = '*';

		// print
		for (int i = 0; i < rowSize; i++) {
			String str = "";
			for (int j = 0; j < colSize; j++)
				str += map[i][j];
			System.out.println(str.trim());
		}
	}
}
