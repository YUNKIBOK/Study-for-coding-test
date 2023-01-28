
/*
 * K를 가치가 큰 동전부터 나누어본다.
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] coins = new int[N];
		for(int n =0;n<N;n++) {
			coins[n] = Integer.parseInt(br.readLine());
		}
		
		int currentIndex = N-1;
		int count = 0;
		while(K>0) {
			count+= K/coins[currentIndex];
			K %= coins[currentIndex--];
		}
		System.out.println(count);
	}
}
