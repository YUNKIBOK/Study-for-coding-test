import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Union - Find 연산을 적절하게 수행한다
 * Find 연산 시 부모 압축 최적화를 한다
 * Union 연산 시 새로운 부모는 더 작은 수로 설정한다
 * 중복을 제거한 부모의 개수를 세면 무리의 개수를 알 수 있다
 * 모든 연산을 마치고 부모를 최신화 한 뒤 부모의 개수를 센다
 */

public class Solution {

	static int N;
	static int M;
	static int[] parents;

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
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			InitializeParent(N);

			// Union 연산 수행
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				union(num1, num2);
			}

			// 중복 없는 부모 개수 세기
			Set<Integer> setOfParents = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				// 마지막 부모 최신화
				int parent = findParent(i);
				setOfParents.add(parent);
			}
			sb.append(setOfParents.size()).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void InitializeParent(int N) {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findParent(int num) {
		if (parents[num] == num)
			return num;
		return parents[num] = findParent(parents[num]);
	}

	static void union(int num1, int num2) {
		int parent1 = findParent(num1);
		int parent2 = findParent(num2);
		if (parent1 < parent2) {
			parents[parent2] = parent1;
		} else if (parent1 > parent2) {
			parents[parent1] = parent2;
		}
	}

}