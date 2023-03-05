import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 * 자석은 배열로 구현한다 -> 인접한 날의 값을 편리하게 접근 가능
 * 자석 회전 계획을 배열로 만들어 갱신한 뒤 한 번에 돌린다
 */

public class Solution {

	static int K;
	static int[][] magnets; // 자석들(행: 자석 번호, 열: 자석 날 값)
	static int[] turnPlan; // 회전 계획(각 자석을 회전할 방향, 1: 시계 방향, -1: 반 시계 방향, 0: 회전x)

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			// 변수 초기화
			K = Integer.parseInt(in.readLine());
			magnets = new int[4 + 1][8];

			// 자석 날 값 저장
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// K번 자석 회전하기
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(in.readLine(), " ");
				int num = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				turn(num, direction);
			}

			int sum = 0;
			for (int i = 1; i <= 4; i++) {
				sum += magnets[i][0] * Math.pow(2, i - 1);
			}
			sb.append(sum).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void turn(int num, int direction) {
		turnPlan = new int[4 + 1];
		turnPlan[num] = direction; // 현재 자석 회전 방향 지정

		// 현재 자석보다 왼쪽에 위치한 자석들 방향 지정
		int index = num;
		while (index - 1 >= 1) {
			int current = magnets[index][6];
			int next = magnets[index - 1][2];
			if (current + next == 1) { // 극이 다르면 현재 자석과 반대 방향으로 회전
				turnPlan[index - 1] = -turnPlan[index];
			} else {
				turnPlan[index - 1] = 0;
			}
			index -= 1;
		}

		// 현재 자석보다 오른쪽에 위치한 자석들 방향 지정
		index = num;
		while (index + 1 <= 4) {
			int current = magnets[index][2];
			int next = magnets[index + 1][6];
			if (current + next == 1) { // 극이 다르면 현재 자석과 반대 방향으로 회전
				turnPlan[index + 1] = -turnPlan[index];
			} else {
				turnPlan[index + 1] = 0;
			}
			index += 1;
		}

		// 자석 회전 계획에 따라 회전 수행
		for (int i = 1; i <= 4; i++) {
			if (turnPlan[i] == 0) {
				continue;
			} else if (turnPlan[i] == -1) { // 반시계 방향 회전: 맨 앞 원소를 맨 뒤로
				int front = magnets[i][0];
				for (int j = 0; j + 1 < 8; j++) {
					magnets[i][j] = magnets[i][j + 1];
				}
				magnets[i][7] = front;
			} else if (turnPlan[i] == 1) { // 시계 방향 회전: 맨 뒤 원소를 맨 앞으로
				int rear = magnets[i][7];
				for (int j = 7; j - 1 >= 0; j--) {
					magnets[i][j] = magnets[i][j - 1];
				}
				magnets[i][0] = rear;
			}
		}
	}

}
