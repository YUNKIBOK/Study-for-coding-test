import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// create 2D array
		int size = 4 * N - 3;
		char[][] map = new char[size][size];

		// draw rectangle
		for (int i = 0; i < 2 * N - 1; i++) {
			char content = i % 2 == 0 ? '*' : ' ';
			for (int j = i; j < size - i; j++) {
				for (int k = i; k < size - i; k++) {
					map[j][k] = content;
				}
			}
		}

		// print
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
