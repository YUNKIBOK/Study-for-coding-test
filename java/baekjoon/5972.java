
import java.util.*;
import java.io.*;

/*
 * 특정 지점에서 특정 지점까지 이동하는 최소 비용을 구해야 한다
 * 비용은 0이거나 양의 정수이므로 전형적인 다익스트라 알고리즘 문제이다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 간선 정보 초기화
		List<Edge>[] edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		// 간선 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[from].add(new Edge(to, cost));
			edges[to].add(new Edge(from, cost));
		}

		// 다익스트라 알고리즘
		int[] distances = new int[N + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);

		PriorityQueue<Edge> pQueue = new PriorityQueue();
		distances[1] = 0;
		pQueue.add(new Edge(1, 0));
		while (pQueue.size() > 0) {
			Edge current = pQueue.poll();
			if (current.cost > distances[current.node])
				continue;
			for (Edge next : edges[current.node]) {
				if (distances[next.node] > distances[current.node] + next.cost) {
					distances[next.node] = distances[current.node] + next.cost;
					pQueue.add(new Edge(next.node, distances[next.node]));
				}
			}
		}

		System.out.println(distances[N]);
	}

}

class Edge implements Comparable<Edge> {
	int node, cost;

	public Edge(int to, int cost) {
		this.node = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}
