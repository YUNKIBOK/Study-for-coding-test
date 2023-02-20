import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 감독관을 한 명 배치한 후 부감독관을 배치한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		int[] classes = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			classes[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(in.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		Long count = 0L;
		for (int i = 0; i < N; i++) {
			// 총감독관과 부감독관 함께 배치
			int students = classes[i];
			students -= B;
			count++;
			if (students > 0) {
				count += (students / C);
				students %= C;
				if (students > 0) {
					count++;
				}
			}
		}

		System.out.println(count);
	}

}