import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/*
 * 12!은 약 5억으로 5초 이내에 연산이 가능하다
 * 연산자는 왼쪽에서 오른쪽으로 계산하므로 순서가 의미가 있다 -> 순열을 활용한다
 * 나눗셈 결과에서 소수점 이하는 버리는 것에 유의한다
 * ---------------------------------------------------
 * 문제 하단에 테스트케이스까지 포함하여 3초 이내에 완료해야 한다고 서술되어 있다
 * 테스트 케이스 50개를 고려하지 못했으며 문제 상단에 5초 이내로 나와 있어 시간 복잡도 계산에 문제가 생겼다
 * 사칙연산에 사용되는 연산자는 4개로 4^12는 약 400만이다
 * 400만 곱하기 50은 약 2억으로 2초안에 연산이 가능하다
 * 중복 순열로 연산자 N-1개를 나열하고 문제에서 정해준 연산자 개수와 일치하는지 확인하여 최대/최소값을 갱신한다
 */

public class Solution {

	static int[] choiceMarks = { 0, 0, 0, 0 };
	static int[] targetMarks = new int[4];
	static int[] numbers;
	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			// 연산자 배열 및 연산자 사용 제한 생성
			for (int i = 0; i < 4; i++) {
				targetMarks[i] = Integer.parseInt(st.nextToken());
			}
			numbers = new int[N];
			st = new StringTokenizer(in.readLine(), " ");
			// 피연산자 배열 생성
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			// 중복 순열 활용
			calResult(0, numbers[0]);
			sb.append("#").append(t).append(" ").append(max - min).append("\n");
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
		}
		System.out.println(sb.toString());

	}

	private static void calResult(int count, int result) {
		if (count >= N - 1) {
			if (targetMarks[0] == choiceMarks[0] && targetMarks[1] == choiceMarks[1] && targetMarks[2] == choiceMarks[2]
					&& targetMarks[3] == choiceMarks[3]) {
				min = Math.min(min, result);
				max = Math.max(max, result);
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			choiceMarks[i] += 1;
			if (i == 0) {
				calResult(count + 1, result + numbers[count + 1]);
			} else if (i == 1) {
				calResult(count + 1, result - numbers[count + 1]);
			} else if (i == 2) {
				calResult(count + 1, result * numbers[count + 1]);
			} else if (i == 3) {
				calResult(count + 1, result / numbers[count + 1]);
			}
			choiceMarks[i] -= 1;
		}
	}

}