import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 스네이크버드가 최대한 길어지기 위해서는 가장 낮은 과일부터 먹기 시작해야 한다
 * 따라서 과일의 높이를 오름차순으로 정렬한 뒤 시뮬레이션한다
 * 정렬하는데 O(NlogN), 과일을 순회하며 시뮬레이션하는데 O(N)이다
 * 최종적으로 O(NlogN)이고 N <= 1,000이므로 시간은 넉넉하다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		// 과일의 높이 저장
		int[] fruits = new int[N];
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}

		// 과일의 높이 오름차순 정렬
		Arrays.sort(fruits);

		// 과일 먹기 시뮬레이션
		for (int i = 0; i < N; i++) {
			if (L >= fruits[i]) {
				L += 1;
			} else {
				break;
			}
		}

		System.out.println(L);
	}

}