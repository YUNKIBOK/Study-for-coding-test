
import java.util.*;
import java.io.*;

/*
 * 각 열을 기준 삼아 왼쪽에 물을 채울 수 있는지, 오른쪽에 물을 채울 수 있는지 확인한다
 * 물을 채울 수 있으면 채우고 그렇지 아니면 채우지 않는다
 * 최악의 경우에 500(열 순회) * (500(왼쪽 오른쪽 모두 탐색) + 500(왼쪽 오른쪽 모두 채우기)) = 50만이다 
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] current = new int[W];

		// 블록 저장
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < W; i++) {
			current[i] = Integer.parseInt(st.nextToken());
		}

		// 고인 빗물의 총량
		int count = 0;

		for (int i = 0; i < W; i++) {
			int now = current[i];
			// 왼쪽 검사
			int left = -1;
			for (int j = i; j - 1 >= 0; j--) {
				if (current[j - 1] >= now) {
					left = j - 1;
					break;
				}
			}
			if (left != -1) {
				for (int j = i - 1; j >= left + 1; j--) {
					count += Math.abs(now - current[j]);
					current[j] = now;
				}
			}

			// 오른쪽 검사
			int right = -1;
			for (int j = i; j + 1 < W; j++) {
				if (current[j + 1] >= now) {
					right = j + 1;
					break;
				}
			}
			if (right != -1) {
				for (int j = i + 1; j <= right - 1; j++) {
					count += Math.abs(now - current[j]);
					current[j] = now;
				}
			}

//			System.out.println(left);
//			System.out.println(right);
//			System.out.println(Arrays.toString(current));
		}

		System.out.println(count);
	}

}
