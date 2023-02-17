import java.util.Scanner;

/*
 * 무거운 봉지를 최대한 많이 가져가야 전체 가져가는 봉지 개수를 줄일 수 있다
 * 5로 나눈 몫만큼 5kg을 가져가고
 * 5로 나눈 나머지를 3으로 나눈 몫만큼 3kg을 가져가면 배달하는 최소 개수이다
 * 3으로 나눈 나머지가 0이 아니면 Nkg을 만들 수 없다는 것이다
 * -------------------------------------------------------------------------
 * 위 방법은 6kg와 같은 경우 3kg 두개로 만들 수 있는데 만들 수 없다고 나온다
 * DP를 사용하는 새 방법을 사용하기로 했다
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] bagCount = new int[N + 1];

		bagCount[3] = 1;
		if (N >= 5)
			bagCount[5] = 1;

		for (int i = 6; i <= N; i++) {
			int using3 = bagCount[i - 3] != 0 ? bagCount[i - 3] : Integer.MAX_VALUE;
			int using5 = bagCount[i - 5] != 0 ? bagCount[i - 5] : Integer.MAX_VALUE;

			if (Math.min(using3, using5) == Integer.MAX_VALUE) {
				bagCount[i] = Integer.MAX_VALUE;
			} else {
				bagCount[i] = Math.min(using3, using5) + 1;
			}
		}

		if (bagCount[N] == Integer.MAX_VALUE || bagCount[N] == 0)
			System.out.println(-1);
		else
			System.out.println(bagCount[N]);
	}

}