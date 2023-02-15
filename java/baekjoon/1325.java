import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * N이 10,000이므로 O(N^2)의 시간 복잡도까지 가능하다
 * 각 컴퓨터에서 BFS를 시작하고 해킹 가능한 컴퓨터 수를 배열에 저장한다
 * BFS 수행 시 해킹할 수 있는 컴퓨터의 최댓값을 갱신한다
 * 배열을 순회하며 해킹 가능한 컴퓨터의 수가 최댓값과 같은 컴퓨터를 출력한다
 * DFS는 스택 오버플로우가 우려되어 사용하지 않는다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] map = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			map[from].add(to);
		}

		int[] numOfHacking = new int[N + 1];
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			int result = hacking(i, map, new boolean[N + 1]);
			numOfHacking[i] = result;
			max = Math.max(max, result);
		}

		for (int i = 0; i <= N; i++) {
			if (max == numOfHacking[i])
				sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}

	static int hacking(int from, List<Integer>[] map, boolean[] hacked) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(from);
		hacked[from] = true;
		int count = 1;

		while (queue.size() > 0) {
			int current = queue.poll();
			for (int to : map[current]) {
				if (hacked[to] == false) {
					queue.add(to);
					hacked[to] = true;
					count++;
				}
			}
		}
		return count;
	}

}