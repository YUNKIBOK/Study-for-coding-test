import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 맵, 좌표, 이동 방향을 매개변수로 하는 제귀 함수를 활용한다
 * 도착지점에서부터 출발한다
 * 사다리를 타고 올라가다 왼쪽 또는 오른쪽으로 갈 수 있으면 가로로 이동한다
 * 탈출 조건은 사다리를 타고 있는 방향에 따라 달라진다(왼쪽, 오른쪽, 위쪽)
 * 입력이 많으므로 BufferedReader를 활용한다
 */

public class Solution {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[][] map = new int[100][100];
		int startRow = -1;
		int startCol = -1;
		for (int t = 1; t <= 10; t++) {
			in.readLine();
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (value == 2) {
						startRow = i;
						startCol = j;
					}
					map[i][j] = value;
				}
			}
//			System.out.println(startRow + " " + startCol);
			System.out.println("#" + t + " " + climbLadder(map, 'U', startRow, startCol));
		}

	}

	public static int climbLadder(int[][] map, char prevDirection, int row, int col) {
//		System.out.println(row + " "+col);
		if (row <= 0)
			return col;

		if (prevDirection != 'R' && col - 1 >= 0 && map[row][col - 1] == 1) {
			while (col - 1 >= 0 && map[row][col - 1] == 1) {
				col -= 1;
			}
			return climbLadder(map, 'L', row, col);
		} else if (prevDirection != 'L' && col + 1 < 100 && map[row][col + 1] == 1) {
			while (col + 1 < 100 && map[row][col + 1] == 1) {
				col += 1;
			}
			return climbLadder(map, 'R', row, col);
		} else {
			row -= 1;
			return climbLadder(map, 'U', row, col);
		}
	}
}
