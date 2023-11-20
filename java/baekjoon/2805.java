import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int[] trees;
	static int min, max, mid;
	static int N, M;
	static long sum;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		trees = new int[N];
		min = 0;
		max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}

//		System.out.println(min + " " + max);

		mid = (min + max) / 2;
		while (min <= max) {
			sum = 0;
			for (int i = 0; i < N; i++) {
				if (trees[i] > mid) {
					sum += (trees[i] - mid);
				}

				if (sum > M) {
					break;
				}
			}
//			System.out.println(mid + " " + sum);

			if (sum > M) {
				min = mid + 1;
			} else if (sum == M) {
				break;
			} else {
				max = mid - 1;
			}
			mid = (min + max) / 2;
		}

		System.out.println(mid);
	}
}