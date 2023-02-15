import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * DFS 또는 BFS를 사용한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		List<Edge>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, distance));
			list[to].add(new Edge(from, distance));
		}

		boolean[] visited = new boolean[N + 1];
		long[] costs = new long[N + 1];
		BFS(visited, list, 1, costs);

		long max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, costs[i]);
		}

		System.out.println(max);
	}

	static void BFS(boolean[] visited, List<Edge>[] list, int start, long[] costs) {
		Queue<Edge> queue = new ArrayDeque<>();
		queue.add(new Edge(start, 0));
		visited[start] = true;
		costs[start] = 0;

		while (queue.size() > 0) {
			Edge current = queue.poll();
			for (Edge e : list[current.to]) {
				if (!visited[e.to]) {
//					System.out.println(e);
					queue.add(e);
					visited[e.to] = true;
					costs[e.to] = costs[current.to] + e.distance;
				}
			}
		}
	}
}

class Edge {

	int to;
	int distance;

	public Edge(int to, int distance) {
		this.to = to;
		this.distance = distance;
	}

}