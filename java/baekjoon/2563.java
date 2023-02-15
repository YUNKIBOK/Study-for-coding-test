import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 도화지에 최대 100장의 색종이를 붙이는 시뮬레이션을 한다
 * false는 흰색 도화지를, true는 검은색 색종이가 붙은 영역을 의미한다
 * 색종이를 붙이면서 true의 개수를 센다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean[][] isBlack = new boolean[100][100];

		int count = 0;
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			// 색종이 붙이기
			for (int p = startRow; p < startRow + 10; p++) {
				for (int q = startCol; q < startCol + 10; q++) {
					if (isBlack[p][q] == false) {
						isBlack[p][q] = true;
						count += 1;
					}
				}
			}
		}

		System.out.println(count);
	}

}