
/*
 * D째 날 떡의 개수 K 는 A와 B에 대한 합이고 계수는 피보나치 수열을 따른다 
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

		int D = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[] fibonacci = new int[D+1];
		fibonacci[1] = 1;
		fibonacci[2] = 1;
		for(int i=3; i<=D; i++) {
			fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
		}

		endPoint:
		for (int i = 1; i <= B / 2; i++) {
			breakPoint:
			for (int j = i; j <= (B + 1) / 2; j++) {
				int result = i * fibonacci[D-2] + j * fibonacci[D-1];
//				System.out.println(i + " "+ j+" "+ result);
				if(result > B)
					break breakPoint;
				else if (result == B) {
					System.out.println(i);
					System.out.println(j);
					break endPoint;
				}
			}
		}
	}

}
