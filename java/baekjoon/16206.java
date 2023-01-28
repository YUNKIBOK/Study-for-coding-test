
/*
 * 케이크를 순회하며 자를 수 있는 만큼 자른다.
 * 순회 시 길이가 10의 배수인 것부터 처리한다. 이때 길이가 짧은 것부터 처리한다.
 * 길이가 10인 케이크는 자르지 않고 1번 카운트한다.
 * 길이가 10보다 크고 10의 배수인 케이크는 10으로 나눈 몫 - 1번 자르고 몫만큼 카운트한다.
 * 길이가 10보다 크고 10의 배수가 아닌 케이크는 10으로 나눈 몫번 자르고 몫만큼 카운트한다.
 * 길이가 10보다 작은 케이크는 패스한다.
 * 자를 수 있는 횟수를 다 쓰거나 모든 케이크를 순회한 경우 반복을 마친다.
 * 반복을 마치고 순회하지 못한 길이가 10인 케이크가 있는지 확인한다.
 * 남은 횟수가 자를 수 있는 횟수보다 적은 경우 모든 횟수를 소진하고 남은 횟수만큼 카운트한다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int count = 0;
		int[] cakes = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			cakes[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cakes);

		for (int i = 0; i < N && M >= 0; i++) {
			int cake = cakes[i];
			if (cake % 10 == 0) {
				int quotient = cake / 10;
				if (quotient - 1 > M) {
					count += M;
					M = 0;
				} else {
					count += quotient;
					M -= (quotient - 1);
				}
			}
		}

		for (int i = 0; i < N && M > 0; i++) {
			int cake = cakes[i];

			if (cake % 10 != 0) {
				int quotient = cake / 10;
				if (quotient > M) {
					count += M;
					M = 0;
					break;
				} else {
					count += quotient;
					M -= quotient;
				}
			}
		}

		System.out.println(count);
	}
}
