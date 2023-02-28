import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Union - Find 연산을 적절하게 수행한다
 * Find 연산 시 부모 압축 최적화를 한다
 * Union 연산 시 새로운 부모는 더 작은 수로 설정한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] parents = new int[N + 1];

			// 최초 부모는 자기 자신로 설정
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			// 명령어 수행
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int command = Integer.parseInt(st.nextToken());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());

				if (command == 0) {
					union(num1, num2, parents);
				} else if (command == 1) {
					int parent1 = findParent(num1, parents);
					int parent2 = findParent(num2, parents);

					if (parent1 == parent2) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static int findParent(int i, int[] parents) {
		if (parents[i] == i) {
			return i;
		}
		return parents[i] = findParent(parents[i], parents);
	}

	static void union(int num1, int num2, int[] parents) {
		int parent1 = findParent(num1, parents);
		int parent2 = findParent(num2, parents);

		if (parent1 < parent2) {
			parents[parent2] = parent1;
		} else if (parent1 > parent2) {
			parents[parent1] = parent2;
		}
	}

}