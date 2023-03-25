
import java.util.*;
import java.io.*;

/*
 * 산성 용액만 주어진 경우 가장 절대값이 작은 산성 용액 두개를 선택한다
 * 알칼리성 용액만 주어진 경우 마찬가지이다
 * 산성과 알칼리성 용액이 모두 주어진 경우 투 포인터를 활용하여 절대값을 줄여나간다
 * 이 문제는 N이 10만이다
 * 조합을 사용하는 완전 탐색은 10만C2 = 약 50억이므로 시간 초과가 일어날 수 있다
 * 따라서 완전 탐색이 아닌 다른 방법을 생각해야 한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 변수 초기화
		int N = Integer.parseInt(in.readLine());
		int[] liquids = new int[N];

		// 용액 특성 저장
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int current = Integer.parseInt(st.nextToken());
			liquids[i] = current;
		}

		// 입력이 모두 알칼리성 용액이거나 산성 용액인 경우
		if (liquids[0] < 0 && liquids[N - 1] < 0) {
			sb.append(liquids[N - 2]).append(" ").append(liquids[N - 1]);
			System.out.println(sb.toString());
			System.exit(0);
		} else if (liquids[0] > 0 && liquids[N - 1] > 0) {
			sb.append(liquids[0]).append(" ").append(liquids[1]);
			System.out.println(sb.toString());
			System.exit(0);
		}

		// 입력에 알칼리성 용액과 산성 용액이 함께 주어진 경우
		int left = 0; // 왼쪽 포인터
		int right = N - 1; // 오른쪽 포인터
		int min = Integer.MAX_VALUE;
		while (left < right) { // 포인터 교차 시 중단
			int current = Math.abs(liquids[left] + liquids[right]);
			if (current <= min) { // 최소값 갱신
				min = current;
				sb.setLength(0);
				sb.append(liquids[left]).append(" ").append(liquids[right]);
			}

			// 왼쪽 포인터를 움직여 절대값 크기를 줄일 수 있는 경우
			int mvLeft = Math.abs(liquids[left + 1] + liquids[right]);
			if (mvLeft < current) {
				left++;
				continue;
			}

			// 오른쪽 포인터를 움직여 절대값 크기를 줄일 수 있는 경우
			int mvRight = Math.abs(liquids[left] + liquids[right - 1]);
			if (mvRight < current) {
				right--;
				continue;
			}

			// 포인터를 하나만 움직인다는 것은 하나의 용액을 고정하고 테스트를 이어나간다는 것이다
			// 포인터를 하나만 움직일 수 없다는 것은 A 용액을 고정했을 때와 B 용액을 고정했을 때의 최적의 답을 구한 상태라는 의미이다
			// 즉, 앞으로 새로운 두 용액으로 테스트를 이어나가야 한다는 의미이다
			// 따라서, 포인터 중 하나만 움직여 절대값을 줄일 수 없는 경우에는 두 포인터 모두 이동시키고 테스트를 이어나간다
			left++;
			right--;
		}
		System.out.println(sb.toString());
	}

}
