import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 재귀(맵, 카운트(현재 인덱스), 종료 인덱스, 구간 합)함수를 활용한다
 * 입력 값과 출력 값이 많으므로 BufferedReader/Writer, StringBuilder를 사용한다
 * ----------------------------------------------------------------------------
 * 시간 초과를 해결하기 위해서 다른 방법을 생각했다
 * 숫자들을 입력받으면서 구간 합을 계산하여 모두 저장해둔다
 * A ~ B까지의 구간 합은 1부터 B까지의 구간 합 - 1부터 A이전까지의 구간 합이다
 */

public class Main {

	public static StringBuilder sb = new StringBuilder();
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N + 1];
		int[] sums = new int[N+1];
		int tempSum = 0;
		st = new StringTokenizer(in.readLine(), " ");
		for (int n = 1; n <= N; n++) {
			numbers[n] = Integer.parseInt(st.nextToken());
			tempSum += numbers[n];
			sums[n]=tempSum;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			sb.append((sums[to]-sums[from - 1])).append("\n");
//			partSum(numbers, from, to, 0);
		}
		
		out.append(sb.toString());
		out.flush();
	}

//	public static void partSum(int[] numbers, int current, int to, int sum) {
//		if (current > to) {
//			sb.append(sum).append("\n");
//			return;
//		}
//		partSum(numbers, current + 1, to, sum + numbers[current]);
//	}

}
