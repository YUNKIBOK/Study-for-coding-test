import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 인접 리스트 방식으로 그래프를 구현한 뒤
 * 시작점에서 BFS를 수행한다
 * BFS 마지막 레벨에서 나온 대상 중 가장 번호가 큰 사람으로 갱신해야 한다
 */

public class Solution {

	static List<Integer>[] graph;
	static int start;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 변수 초기화
		graph = new ArrayList[100 + 1];

		for (int t = 1; t <= 10; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			// 그래프 생성
			for (int i = 1; i <= 100; i++) {
				graph[i] = new ArrayList<>();
			}
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}

			// BFS 수행 후 가장 나중에 연락 받는 사람 중 번호가 가장 큰 사람 찾기
			sb.append(BFS(start, new boolean[100 + 1])).append("\n");
		}

		System.out.println(sb.toString());
	}

	static int BFS(int start, boolean[] visited) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;

		int lastPerson = Integer.MIN_VALUE;
		while (queue.size() > 0) {
			lastPerson = Integer.MIN_VALUE;
			int size = queue.size();
			while (size-- > 0) { // BFS 레벨별로 수행하고 마지막 사람을 갱신한다
				int current = queue.poll();
				lastPerson = Math.max(lastPerson, current);
				for (int next : graph[current]) {
					if (visited[next] == false) {
						queue.add(next);
						visited[next] = true;
					}
				}
			}
		}

		return lastPerson;
	}

}