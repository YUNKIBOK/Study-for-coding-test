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
 * 행마다 가로로 M개의 벌통을 선택하는 모든 경우를 구한다
 * 각 경우마다 부분조합을 사용하여 더했을 때 C 이하이며 최대가 되는 제곱합을 구한다
 * 조합을 사용하여 서로 겹치지 않으면서 제곱합이 최대가 되는 두 경우를 구한다
 */

public class Solution {

	static int N, M, C;
	static int tempMax; // 부분 조합에서 임시 제곱합
	static int max; // 최대 결과
	static int[][] honeys;
	static Selection[] selections;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			honeys = new int[N][N];
			selections = new Selection[N * (N - M + 1)];

			// 꿀 정보 저장
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int c = 0; c < N; c++) {
					honeys[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 체취 가능한 경우 구하기
			int count = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c <= N - M; c++) {
					selections[count++] = new Selection(r, c, c + M - 1, Arrays.copyOfRange(honeys[r], c, c + M));
				}
			}

			for (int i = 0; i < selections.length; i++) {
				// 체취 가능한 경우별 최대 제곱합 구하기
				tempMax = Integer.MIN_VALUE;
				powerSet(0, new boolean[M], selections[i].amounts, 0);
				selections[i].max = tempMax;
			}

//			System.out.println(Arrays.toString(selections));

			// 조합해서 체취 가능한 두 개 고르고 최대값 갱신
			max = Integer.MIN_VALUE;
			combination(0, 0, new int[2]);
			sb.append(max).append("\n");
		}

		System.out.println(sb.toString());

	}

	static void combination(int count, int start, int[] order) {
		if (count >= 2) {
			Selection s1 = selections[order[0]];
			Selection s2 = selections[order[1]];
			// 서로 겹치지 않아 체취 가능한 경우
			if ((s1.r != s2.r || ((s1.r == s2.r) && (s1.endC < s2.startC || s2.endC < s1.startC)))) {
				max = Math.max(max, s1.max + s2.max);
			}
			return;
		}

		for (int i = 0; i < selections.length; i++) {
			order[count] = i;
			combination(count + 1, i + 1, order);
		}
	}

	static void powerSet(int count, boolean[] selection, int[] amounts, int sum) {
		if (sum > C) {
			return;
		}

		// 제곱 합 계산
		if (count >= M) {
			int tempPowSum = 0;
			for (int i = 0; i < M; i++) {
				if (selection[i] == true) {
					tempPowSum += amounts[i] * amounts[i];
				}
			}
			tempMax = Math.max(tempMax, tempPowSum);
			return;
		}

		selection[count] = true;
		powerSet(count + 1, selection, amounts, sum + amounts[count]);

		selection[count] = false;
		powerSet(count + 1, selection, amounts, sum);
	}

}

class Selection {

	int r;
	int startC;
	int endC;
	int[] amounts;
	int max;

	public Selection(int r, int startC, int endC, int[] amounts) {
		this.r = r;
		this.startC = startC;
		this.endC = endC;
		this.amounts = amounts;
	}

	@Override
	public String toString() {
		return "Selection [r=" + r + ", startC=" + startC + ", endC=" + endC + ", amounts=" + Arrays.toString(amounts)
				+ ", max=" + max + "]";
	}

}