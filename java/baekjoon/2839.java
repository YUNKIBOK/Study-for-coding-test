
/*
 * 1. 다이나믹 프로그래밍을 이용한다.
 * 2. N킬로그램은 N - 3킬로그램의 봉지 수  + 1과 N - 5킬로그램의 봉지수  + 1중 더 적은 봉지를 가져가면 된다.
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// 킬로그램 배달하는 봉지의 최소 개수 초기화
		int[] counts = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			counts[i] = Integer.MAX_VALUE;
		}
		counts[3] = 1;
		if (N >= 5)
			counts[5] = 1;

		// 다이나믹 프로그래밍
		for (int i = 6; i <= N; i++) {
			int use3 = counts[i - 3] == Integer.MAX_VALUE ? Integer.MAX_VALUE : counts[i - 3] + 1;
			int use5 = counts[i - 5] == Integer.MAX_VALUE ? Integer.MAX_VALUE : counts[i - 5] + 1;
			counts[i] = Math.min(use3, use5);
		}

		System.out.println(counts[N] == Integer.MAX_VALUE ? -1 : counts[N]);
	}
}
