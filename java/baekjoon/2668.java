import java.util.*;
import java.io.*;

/*
 * 각 열의 두 번째 수를 이용해 다음 열을 찾아가는 DFS를 수행한다
 * DFS를 하면서 처음 시작했던 곳으로 돌아온 것과 첫 번째 수와 두 번째 수가 같은 원소들을 합한 것이 최대로 많이 뽑는 경우이다
 * ------------------------------------------------------------
 * 순환이 생기는 DFS 원소들에 자기 자신과 같은 원소를 모두 더한 것이 최대로 많이 뽑는 경우이다
 * DFS를 하면서 처음 시작했던 곳으로 돌아오지 못하면 뽑을 수 없는 경우이다
 */
public class Main {
	static HashSet<Integer> set; // 자기 자신과 같은 원소들
	static HashSet<Integer> longestSet; // 가능한 원소들 집합
	static HashSet<Integer> tempSet; // dfs용 원소 집합
	static int[][] numbers;
	static boolean[] isVisited;
	static int N;
	static int initialNum; // dfs 시작 원소 번호
	static boolean isPossible;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		numbers = new int[2][N + 1];
		longestSet = new HashSet<>();
		set = new HashSet<>();

		// 수 배열 저장 및 자기 자신과 같은 원소 구성
		for (int i = 1; i <= N; i++) {
			numbers[0][i] = i;
			numbers[1][i] = Integer.parseInt(in.readLine());
			if (i == numbers[1][i]) {
				set.add(i);
			}
		}

		// 수를 순회하며 dfs하여 가능한 원소 모으기
		for (int i = 1; i <= N; i++) {
			if (numbers[1][i] != i) {
				tempSet = new HashSet<>();
				isVisited = new boolean[N + 1];
				isVisited[i] = true;
				initialNum = i;
				isPossible = false;
				dfs(i);
//				System.out.println("tempSet " + Arrays.toString(tempSet.toArray()));
				if (isPossible == true) {
					longestSet.addAll(tempSet);
//					System.out.println(Arrays.toString(longestSet.toArray()));
				}
			}
		}

		// 최대로 많이 뽑은 경우에 대한 원소들 오름차순 정렬
		longestSet.addAll(set);
		List<Integer> list = new ArrayList(longestSet);
		Collections.sort(list);

		// 출력
		sb.append(list.size()).append("\n");
		for (int item : list) {
			sb.append(item).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void dfs(int n) {
		int next = numbers[1][n];

		if (next == initialNum) {
			tempSet.add(next);
			isPossible = true;
			return;
		}

		if (isVisited[next] == false) {
			isVisited[next] = true;
			tempSet.add(next);
			dfs(next);
		}
	}

}