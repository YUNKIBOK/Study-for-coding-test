import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 데이터를 입력 받으면서 행마다 해당 열까지의 구간 합을 계산해둔다
 * 어떤 행에서 A열부터 B열까지의 구간 합은 1열부터 B열까지의 구간 합 - 1열부터 A열 이전까지의 구간 합이다
 * 수의 범위는 최대 1024 * 1024 * 1000 = 약 10억이므로 int 타입을 사용해도 괜찮다
 * 입력과 출력이 상당히 많으므로 Buffered 계열을 활용하고 StringBuilder를 사용한다
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
		int[][] numbers = new int[N + 1][N + 1];
		int[][] sums = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			int tempSum = 0;
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				numbers[r][c] = Integer.parseInt(st.nextToken());
				tempSum += numbers[r][c];
				sums[r][c] = tempSum;
			}
		}
		
//		for(int i=1; i<=N;i++)
//			System.out.println(Arrays.toString(sums[i]));

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int result = 0;
			for(int r=x1; r<=x2;r++) {
				result += (sums[r][y2]-sums[r][y1-1]);
			}
			sb.append(result).append("\n");
		}
		out.append(sb.toString());
		out.flush();
	}
	
}
