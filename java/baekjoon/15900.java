import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 정점이 N개, 간선이 N - 1개인 트리이므로 순환이 없다
 * 루트 노드로부터 모든 리프 노드까지의 거리 합이 짝수면 형석이가 마지막 게임말을 제거하게 된다
 * 거리 합이 홀수이면 성원이가 마지막 게임말을 제거하고 승리할 수 있다
 */

public class Main {

	static String[] results = { "No", "Yes" };
	static int totalSum = 0;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		List<Edge>[] edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			edges[node1].add(new Edge(node2));
			edges[node2].add(new Edge(node1));
		}

//		for (int i = 1; i <= N; i++) {
//			System.out.println(edges[i]);
//		}

		boolean[] visited = new boolean[N + 1];
		DFS(0, 1, edges, visited);

		String result = "Yes";
		System.out.println(results[totalSum % 2]);
	}

	static void DFS(int sum, int node, List<Edge>[] edges, boolean[] visited) {
		visited[node] = true;
//		System.out.println(node + " "+ sum);

		for (Edge e : edges[node]) {
			int to = e.to;
			if (visited[to] == false) {
				DFS(sum + 1, to, edges, visited);
			}
		}

		if (edges[node].size() == 1)
			totalSum += sum;
		return;
	}

}

class Edge {

	int to;

	public Edge(int to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Edge [to=" + to + "]";
	}

}