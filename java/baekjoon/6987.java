import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 경기의 승부에 대한 경우의 수를 완전 탐색한다
 * 주어진 결과와 다르면 더 이상 탐색하지 않는다
 * 한 조에 국가는 6개인데 경기는 6C2 = 15개가 정해져있다
 * 조합의 수가 적으므로 프로그래밍으로 조합을 구하지 말자
 * 사람이 계산하여 미리 입력해두면 시간을 줄일 수 있다
 * 최악의 경우 3 ^ 15(승, 무승부, 패를 15번 선택할 수 있다)이고 이는 약 1,400만이다
 * 1,400만 * 4 = 5,600만이므로 주어진 시간 안에 문제 해결이 가능하다
 */

public class Main {

	// 6개의 국가 간 경기 일정
	static final int[][] plays = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 2 }, { 1, 3 }, { 1, 4 },
			{ 1, 5 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 3, 4 }, { 3, 5 }, { 4, 5 } };
	// 국가별 승, 무승부, 패에 대한 카운터
	static int[] winCount;
	static int[] drawCount;
	static int[] loseCount;
	// 기자가 보내온 국가별 결과
	static int[] win = new int[6];
	static int[] draw = new int[6];
	static int[] lose = new int[6];
	// 결과의 가능성 여부
	static boolean isPossible = false;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		boolean isScoreRight = true;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			// 기자가 보내온 국가별 결과 저장
			for (int j = 0; j < 6; j++) {
				win[j] = Integer.parseInt(st.nextToken());
				draw[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());
				// 국가별 승, 무승부, 패의 합이 5보다 작거나 최적의 해를 구할 수 없다
				if (win[j] + draw[j] + lose[j] != 5) {
					sb.append(String.format("%d ", 0));
					isScoreRight = false;
					break;
				}
			}

			// 국가별 승, 무승부, 패의 합이 부적절하면 하단의 알고리즘을 적용할 필요가 없다
			if (isScoreRight == false) {
				isScoreRight = true;
				continue;
			}

			// 경우의 수 계산하기
			winCount = new int[6];
			drawCount = new int[6];
			loseCount = new int[6];

			checkPossibility(0);
			if (isPossible) {
				sb.append(String.format("%d ", 1));
			} else {
				sb.append(String.format("%d ", 0));
			}
			// 다음 반복을 위한 변수 초기화
			isPossible = false;
		}

		System.out.println(sb.toString());
	}

	static void checkPossibility(int count) {
		// 기자가 보내온 결과에 적합하게 모든 경기를 치룬 경우
		if (count >= 15) {
			isPossible = true;
			return;
		}

		int player1 = plays[count][0];
		int player2 = plays[count][1];

		// player1이 승리한 경우
		if (winCount[player1] + 1 <= win[player1] && loseCount[player2] + 1 <= lose[player2]) {
			winCount[player1] += 1;
			loseCount[player2] += 1;
			checkPossibility(count + 1);
			winCount[player1] -= 1;
			loseCount[player2] -= 1;
		}

		// 무승부인 경우
		if (drawCount[player1] + 1 <= draw[player1] && drawCount[player2] + 1 <= draw[player2]) {
			drawCount[player1] += 1;
			drawCount[player2] += 1;
			checkPossibility(count + 1);
			drawCount[player1] -= 1;
			drawCount[player2] -= 1;
		}

		// player1이 패배한 경우
		if (winCount[player2] + 1 <= win[player2] && loseCount[player1] + 1 <= lose[player1]) {
			winCount[player2] += 1;
			loseCount[player1] += 1;
			checkPossibility(count + 1);
			winCount[player2] -= 1;
			loseCount[player1] -= 1;
		}
	}

}