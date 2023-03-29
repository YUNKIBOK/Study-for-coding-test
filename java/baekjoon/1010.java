import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * M개 중 N개를 뽑는 경우의 수(MCN)을 구한다
 * 조합도 결국엔 팩토리얼 연산으로 구할 수 있으므로 M까지의 팩토리얼을 구한다
 * --------------------------------------------------------------------------
 * long 자료형으로 했었는데 long 자료형도 오버플로우가 발생했다
 * 그래서 일반적으로 동적계획법을 활용한 이항 정리를 이용해서 값을 구하는 것 같다
 * 일단 오버플로우만 해결하면 답을 도출할 수 있다고 생각했기 때문에 BigInteger 자료형을 사용했다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 팩토리얼 결과 값 저장
			BigInteger[] factorial = new BigInteger[M + 1];
			factorial[0] = BigInteger.ONE;
			factorial[1] = BigInteger.ONE;
			for (int i = 2; i <= M; i++) {
				factorial[i] = factorial[i - 1].multiply(new BigInteger(i + ""));
			}
//			System.out.println(Arrays.toString(factorial));

			// MCN 구하기
			BigInteger result = factorial[M].divide(factorial[N].multiply(factorial[M - N]));
			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
	}

}