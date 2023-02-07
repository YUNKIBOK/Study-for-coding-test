import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 최고점에서 최저점으로 상자를 옮기기 위해 정렬을 활용한다
 * 제귀를 활용하는데 작업 횟수를 다 쓰거나 최고점과 최저점의 차이가 1 이하이면 반복을 탈출한다
 */

public class Solution {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] boxes = new int[100];

		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(boxes);
			System.out.println("#" + t + " " + moveBox(boxes, 0, N));
		}
	}

	public static int moveBox(int[] boxes, int count, int n) {
		if (count == n || boxes[99] - boxes[0] <= 1) {
			return boxes[99] - boxes[0];
		}

		boxes[99]--;
		boxes[0]++;
		Arrays.sort(boxes);
		return moveBox(boxes, count + 1, n);
	}
}
