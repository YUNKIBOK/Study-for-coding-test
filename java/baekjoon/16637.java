import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 연산 순서에 의미가 있으며 연산의 개수가 최대 9개 이므로 순열을 떠올렸으나
 * 시간이 0.5초인 점에서 다른 방법을 생각해보았다
 * 순열로 하면 계산을 왼쪽에서 순서대로 하지 않는 경우까지 포함되는데...
 * DP를 활용하면 풀 수 있을 것 같다
 * DP[0]는 첫 피연산자, DP[1]은 첫 연산 결과
 * 점화식은 직전 결과에 바로 연산하는 것과 직전의 직전 결과에 현재 연산을 괄호로 수행한 것 중 최대값이다
 * -----------------------------------------------------------------
 * DP 사용 시 두번째 수와 세번째 수를 괄호로 감싸고 먼저 연산하는 사례 등을 생략하게 된다
 * 또한 마이너스 곱하기 마이너스 연산에 대한 경우가 모두 생략된다(최댓값만 저장해나가므로)
 * 순서대로 괄호 연산을 했을 때와 안했을 때를 수행해나가고 모든 연산을 마치면 최댓값을 갱신한다
 */

public class Main {

	static int N;
	static char[] mathChars;
	static int max;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		mathChars = in.readLine().toCharArray();
		max = Integer.MIN_VALUE;

		DFS(0, mathChars[0] - '0');

		System.out.println(max);
	}

	static int cal(int num1, int num2, char command) {
		int result = 0;
		if (command == '+') {
			result = num1 + num2;
		} else if (command == '-') {
			result = num1 - num2;
		} else if (command == '*') {
			result = num1 * num2;
		}
		return result;
	}

	static void DFS(int count, int result) {
		if (count >= N / 2) {
			max = Math.max(max, result);
			return;
		}

		DFS(count + 1, cal(result, mathChars[2 * count + 1 + 1] - '0', mathChars[2 * count + 1]));
		if (count + 2 <= N / 2) {
			// count + 1번째 연산자에 대한 괄호 연산 결과
			int temp = cal(mathChars[2 * (count + 1) + 1 - 1] - '0', mathChars[2 * (count + 1) + 1 + 1] - '0',
					mathChars[2 * (count + 1) + 1]);
			DFS(count + 2, cal(result, temp, mathChars[2 * (count) + 1]));
		}
	}

}