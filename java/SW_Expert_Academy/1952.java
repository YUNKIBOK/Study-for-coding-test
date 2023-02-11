import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이용권 사용 순서에 의미가 있으므로 순열을 사용한다
 * 이용권은 중복 사용이 가능하므로 중복 순열을 사용한다
 * 50개의 테스트 케이스가 있고 중복 순열은 4^12의 시간 복잡도를 가진다
 * 50 * (4^12)는 약 2억이고 이는 제한 시간 3초 안에 연산이 가능하다
 * 입력이 많으므로 BufferedReader를 활용한다
 * 출력이 많으므로 StringBuilder를 활용한다
 */

class Solution {

	static int minCost = Integer.MAX_VALUE;
	static int[] monthlyPlan = new int[12];
	static int[] costOfTickets = new int[4];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			// 이용권 가격들 입력 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				costOfTickets[i] = Integer.parseInt(st.nextToken());
			}
			// 12개월 이용 계획 입력 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				monthlyPlan[i] = Integer.parseInt(st.nextToken());
			}
			// 수영장을 이용할 수 있는 최소 비용 계산
			calMinCost(0, 0);
			sb.append("#").append(t).append(" ").append(minCost).append("\n");
			minCost = Integer.MAX_VALUE;
		}
		System.out.println(sb.toString());
	}

	private static void calMinCost(int count, int sum) {
		// 12개월을 모두 이용한 경우
		if (count >= 12) {
			minCost = Math.min(minCost, sum);
			return;
		}

		// 4개의 이용권에 대한 중복 선택
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0: // 1일 이용권 선택
				calMinCost(count + 1, sum + monthlyPlan[count] * costOfTickets[i]);
				break;
			case 1: // 1달 이용권 선택
				calMinCost(count + 1, sum + costOfTickets[i]);
				break;
			case 2: // 3달 이용권 선택
				calMinCost(count + 3, sum + costOfTickets[i]);
				break;
			case 3: // 1년 이용권 선택
				calMinCost(count + 12, sum + costOfTickets[i]);
				break;
			}
		}
	}
}
