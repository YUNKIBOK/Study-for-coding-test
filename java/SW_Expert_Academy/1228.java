import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 암호문을 리스트로 선언한다(원소 추가에 용이한 LinkedList 사용)
 * 시뮬레이션한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			List<Integer> numbers = new LinkedList<>();

			int N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}

			int M = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < M; i++) {
				if (st.nextToken().equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					List<Integer> tempList = new LinkedList<>();
					for (int j = 0; j < y; j++) {
						tempList.add(Integer.parseInt(st.nextToken()));
					}
					numbers.addAll(x, tempList);
				}
			}

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(numbers.get(i)).append(" ");
			}
			sb.append("\n");

		}

		System.out.println(sb.toString());
	}

}