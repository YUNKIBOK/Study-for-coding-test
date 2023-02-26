import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 각 면을 2차원 배열로 저장한다(그 면을 바라봤을 때 기준)
 * 2차원 배열을 오른쪽 으로 회전하는 메서드를 만든다
 * 각 면을 회전하고 인접한 면의 열이나 행에 회전을 반영하는 메서드를 만든다
 */

public class Main {

	// 6면을 저장하는 배열
	// 하단과 같은 전개도 모양으로 저장한다
	// U
	// L F R B
	// D
	static char[][] upSide;
	static char[][] downSide;
	static char[][] frontSide;
	static char[][] backSide;
	static char[][] leftSide;
	static char[][] rightSide;

	// 순서대로 돌리는 면
	static char[] commands;
	// 순서대로 돌리는 방향
	static char[] directions;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			// 큐브 초기화
			upSide = new char[3][3];
			downSide = new char[3][3];
			frontSide = new char[3][3];
			backSide = new char[3][3];
			leftSide = new char[3][3];
			rightSide = new char[3][3];
			for (int i = 0; i < 3; i++) {
				Arrays.fill(upSide[i], 'w');
				Arrays.fill(downSide[i], 'y');
				Arrays.fill(frontSide[i], 'r');
				Arrays.fill(backSide[i], 'o');
				Arrays.fill(leftSide[i], 'g');
				Arrays.fill(rightSide[i], 'b');
			}

			// 명령어 저장
			int N = Integer.parseInt(in.readLine());
			commands = new char[N];
			directions = new char[N];
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				String command = st.nextToken();
				commands[i] = command.charAt(0);
				directions[i] = command.charAt(1);
			}

//			System.out.println(Arrays.toString(commands));
//			System.out.println(Arrays.toString(directions));

			// 큐브 돌리기
			for (int i = 0; i < N; i++) {
				char command = commands[i];
				char direction = directions[i];

				// 반시계 방향은 시계방향으로 3번 돌리는 것과 같다
				switch (command) {
				case 'F':
					turnFrontSide(upSide, rightSide, downSide, leftSide);
					if (direction == '-') {
						turnFrontSide(upSide, rightSide, downSide, leftSide);
						turnFrontSide(upSide, rightSide, downSide, leftSide);
					}
					break;
				case 'R':
					turnRightSide(upSide, backSide, downSide, frontSide);
					if (direction == '-') {
						turnRightSide(upSide, backSide, downSide, frontSide);
						turnRightSide(upSide, backSide, downSide, frontSide);
					}
					break;
				case 'L':
					turnLeftSide(upSide, frontSide, downSide, backSide);
					if (direction == '-') {
						turnLeftSide(upSide, frontSide, downSide, backSide);
						turnLeftSide(upSide, frontSide, downSide, backSide);
					}
					break;
				case 'U':
					turnUpSide(backSide, rightSide, frontSide, leftSide);
					if (direction == '-') {
						turnUpSide(backSide, rightSide, frontSide, leftSide);
						turnUpSide(backSide, rightSide, frontSide, leftSide);
					}
					break;
				case 'D':
					turnDownSide(frontSide, rightSide, backSide, leftSide);
					if (direction == '-') {
						turnDownSide(frontSide, rightSide, backSide, leftSide);
						turnDownSide(frontSide, rightSide, backSide, leftSide);
					}
					break;
				case 'B':
					turnBackSide(upSide, leftSide, downSide, rightSide);
					if (direction == '-') {
						turnBackSide(upSide, leftSide, downSide, rightSide);
						turnBackSide(upSide, leftSide, downSide, rightSide);
					}
					break;
				}
			}

			for (int p = 0; p < 3; p++) {
				for (int q = 0; q < 3; q++) {
					sb.append(upSide[p][q]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	// 한 면을 오른쪽으로 회전하기
	static void turnSide(char[][] side) {
		char[][] temp = new char[3][3];
		for (int i = 0; i < 3; i++) {
			System.arraycopy(side[i], 0, temp[i], 0, 3);
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				side[j][3 - 1 - i] = temp[i][j];
			}
		}
	}

	static void turnFrontSide(char[][] up, char[][] right, char[][] down, char[][] left) {
		turnSide(frontSide);

		char[] temp = new char[3];
		temp[0] = left[2][2];
		temp[1] = left[1][2];
		temp[2] = left[0][2];

		left[0][2] = down[0][0];
		left[1][2] = down[0][1];
		left[2][2] = down[0][2];

		down[0][0] = right[2][0];
		down[0][1] = right[1][0];
		down[0][2] = right[0][0];

		right[2][0] = up[2][2];
		right[1][0] = up[2][1];
		right[0][0] = up[2][0];

		up[2][2] = temp[2];
		up[2][1] = temp[1];
		up[2][0] = temp[0];
	}

	static void turnRightSide(char[][] up, char[][] right, char[][] down, char[][] left) {
		turnSide(rightSide);

		char[] temp = new char[3];
		temp[0] = left[2][2];
		temp[1] = left[1][2];
		temp[2] = left[0][2];

		left[0][2] = down[0][2];
		left[1][2] = down[1][2];
		left[2][2] = down[2][2];

		down[0][2] = right[2][0];
		down[1][2] = right[1][0];
		down[2][2] = right[0][0];

		right[2][0] = up[0][2];
		right[1][0] = up[1][2];
		right[0][0] = up[2][2];

		up[0][2] = temp[2];
		up[1][2] = temp[1];
		up[2][2] = temp[0];
	}

	static void turnLeftSide(char[][] up, char[][] right, char[][] down, char[][] left) {
		turnSide(leftSide);

		char[] temp = new char[3];
		temp[0] = left[2][2];
		temp[1] = left[1][2];
		temp[2] = left[0][2];

		left[0][2] = down[2][0];
		left[1][2] = down[1][0];
		left[2][2] = down[0][0];

		down[2][0] = right[2][0];
		down[1][0] = right[1][0];
		down[0][0] = right[0][0];

		right[2][0] = up[2][0];
		right[1][0] = up[1][0];
		right[0][0] = up[0][0];

		up[2][0] = temp[2];
		up[1][0] = temp[1];
		up[0][0] = temp[0];
	}

	static void turnUpSide(char[][] up, char[][] right, char[][] down, char[][] left) {
		turnSide(upSide);

		char[] temp = new char[3];
		temp[0] = left[0][2];
		temp[1] = left[0][1];
		temp[2] = left[0][0];

		left[0][2] = down[0][2];
		left[0][1] = down[0][1];
		left[0][0] = down[0][0];

		down[0][2] = right[0][2];
		down[0][1] = right[0][1];
		down[0][0] = right[0][0];

		right[0][2] = up[0][2];
		right[0][1] = up[0][1];
		right[0][0] = up[0][0];

		up[0][2] = temp[0];
		up[0][1] = temp[1];
		up[0][0] = temp[2];

	}

	static void turnDownSide(char[][] up, char[][] right, char[][] down, char[][] left) {
		turnSide(downSide);

		char[] temp = new char[3];
		temp[0] = left[2][0];
		temp[1] = left[2][1];
		temp[2] = left[2][2];

		left[2][0] = down[2][0];
		left[2][1] = down[2][1];
		left[2][2] = down[2][2];

		down[2][0] = right[2][0];
		down[2][1] = right[2][1];
		down[2][2] = right[2][2];

		right[2][0] = up[2][0];
		right[2][1] = up[2][1];
		right[2][2] = up[2][2];

		up[2][0] = temp[0];
		up[2][1] = temp[1];
		up[2][2] = temp[2];
	}

	static void turnBackSide(char[][] up, char[][] right, char[][] down, char[][] left) {
		turnSide(backSide);

		char[] temp = new char[3];
		temp[0] = left[2][2];
		temp[1] = left[1][2];
		temp[2] = left[0][2];

		left[2][2] = down[2][0];
		left[1][2] = down[2][1];
		left[0][2] = down[2][2];

		down[2][0] = right[0][0];
		down[2][1] = right[1][0];
		down[2][2] = right[2][0];

		right[0][0] = up[0][2];
		right[1][0] = up[0][1];
		right[2][0] = up[0][0];

		up[0][0] = temp[2];
		up[0][1] = temp[1];
		up[0][2] = temp[0];
	}

}