import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라 알고리즘을 사용한다
 * 1. 시작점까지의 거리를 0으로 만들고 시작점에서 갈 수 있는 간선을 우선순위 큐에 넣는다
 * 2. 우선순위 큐가 빌 때까지 다음 작업을 반복한다
 * 3. 우선순위 큐에서 간선 하나를 꺼내고 두 노드 사이의 거리를 좁힐 수 있는지 확인한다
 * 4. 좁힐 수 있다면 새로운 노드에서 갈 수 있는 간선을 우선순위 큐에 모두 넣는다
 */

public class Main {

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(in.readLine());

		// 인접 리스트 방식 사용
		List<Edge>[] map = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			map[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			map[from].add(new Edge(to, cost));
		}

		// 시작점부터 노드까지의 거리를 저장하는 배열 초기화
		int[] distances = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			distances[i] = INF;
		}
		distances[start] = 0;

		// 시작점에서 갈 수 있는 간선을 우선순위 큐에 삽입
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
		edges.add(new Edge(start, 0));

		// 다익스트라 알고리즘 적용
		while (edges.size() > 0) {
			Edge current = edges.poll();
			int now = current.to;
			int dist = current.cost;
			if (distances[now] < dist)
				continue;
			for (Edge e : map[now]) {
				if (dist + e.cost < distances[e.to]) {
					distances[e.to] = dist + e.cost;
					edges.add(new Edge(e.to, distances[e.to]));
				}
			}
		}

		// 출력
		for (int i = 1; i <= V; i++) {
			if (distances[i] == INF)
				sb.append("INF");
			else
				sb.append(distances[i]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}

class Edge implements Comparable<Edge> {
	int to;
	int cost;

	public Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		if (cost > o.cost)
			return 1;
		else
			return -1;
	}
}