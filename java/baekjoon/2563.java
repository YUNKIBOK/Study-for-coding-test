
/*
 * 색종이가 붙여진 위치를 표시하고 개수를 센다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[100][100];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			for (int r = row; r < row + 10; r++) {
				for (int c = col; c < col + 10; c++) {
					map[r][c] = 1;
				}
			}

		}
		
		int count = 0;
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (map[r][c] == 1)
					count++;
			}
		}
		System.out.println(count);
	}
}
