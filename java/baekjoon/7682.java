
import java.util.*;
import java.io.*;

/*
 * 게임판이 가득차서 끝난 것인지 3칸이 이어져서 끝난 것인지 검사한다
 * 두 번째 사람이 3칸을 이어서 끝난 경우에는 O 개수가 X 개수와 같아야 한다
 * 첫 번째 사람이 3칸을 이어서 끝난 경우에는 X 개수 - 1이 O 개수와 같아야 한다
 * 게임판이 가득 차 게임이 끝난 경우에는 X 개수는 5 O 개수는 4여야 한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String str = in.readLine();
			
			// 종료 조건
			if ("end".equals(str)) {
				break;
			}
			
			// 3 * 3 문자로 저장하고 문자 개수 세기
			char[][] map = new char[3][3];
			int xCount = 0;
			int oCount = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = str.charAt(i * 3 + j);
					if (map[i][j] == 'X') {
						xCount++;
					} else if (map[i][j] == 'O') {
						oCount++;
					}
				}
			}

			// 3칸을 이은 개수 구하기
			int xDoneCount = 0;
			int oDoneCount = 0;
			for (int r = 0; r < 3; r++) {
				if (map[r][0] != '.' && map[r][0] == map[r][1] && map[r][0] == map[r][2]) {
					if (map[r][0] == 'X') {
						xDoneCount++;
					} else if (map[r][0] == 'O') {
						oDoneCount++;
					}
				}
			}

			for (int c = 0; c < 3; c++) {
				if (map[0][c] != '.' && map[0][c] == map[1][c] && map[0][c] == map[2][c]) {
					if (map[0][c] == 'X') {
						xDoneCount++;
					} else if (map[0][c] == 'O') {
						oDoneCount++;
					}
				}
			}

			if (map[0][0] != '.' && map[0][0] == map[1][1] && map[0][0] == map[2][2]) {
				if (map[0][0] == 'X') {
					xDoneCount++;
				} else if (map[0][0] == 'O') {
					oDoneCount++;
				}
			}

			if (map[0][2] != '.' && map[0][2] == map[1][1] && map[0][2] == map[2][0]) {
				if (map[0][2] == 'X') {
					xDoneCount++;
				} else if (map[0][2] == 'O') {
					oDoneCount++;
				}
			}

			String result = "invalid";
			if (oDoneCount == 1 && xDoneCount == 0 && xCount == oCount) { // 두 번째 사람이 3칸을 이어서 끝난 경우
				result = "valid";
			} else if (oDoneCount == 0 && 0 < xDoneCount && xDoneCount <= 2 && xCount - 1 == oCount) { // 첫 번째 사람이 3칸을 이어서 끝난 경우
				result = "valid";
			} else if (oDoneCount == 0 && xDoneCount == 0 && xCount == 5 && oCount == 4) { // 게임판이 가득 차 끝난 경우
				result = "valid";
			}
			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
	}

}
