
/*
 * 퇴사일로부터 현재까지 거꾸로 체크한다.
 * 다이나믹프로그래밍을 활용한다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE;
		int[] dp = new int[N];
		Consulting[] consultings = new Consulting[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			consultings[i] = new Consulting(time, price);
		}

		for (int i = N - 1; i >= 0; i--) {
			Consulting consulting = consultings[i];
			int tempMax = 0;
			for (int j = i + consulting.getTime(); j < N; j++) {
				tempMax = Math.max(tempMax, dp[j]);
			}
			if (i + consulting.getTime() - 1 < N) {
				dp[i] = tempMax + consulting.getPrice();
			} else {
				dp[i] = tempMax;
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}

class Consulting {

	private int time;
	private int price;

	public Consulting(int time, int price) {
		this.time = time;
		this.price = price;
	}

	public int getTime() {
		return time;
	}

	public int getPrice() {
		return price;
	}

}
