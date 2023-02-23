import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 방문한 정점을 출력해야 하고 DFS / BFS로 탐색해야 하므로 정점 중심으로 접근해야 한다
 * 따라서, 인접 리스트나 인접 행렬 방식으로 그래프를 저장한다
 * 정점이 1,000인데 인접 행렬 방식으로 구현 시 1,000,000 크기의 배열이 필요하다
 * 간선에 가중치가 없으므로 boolean 타입의 배열을 활용할 수 있고 이는 최대 1 MB이다
 * 주어진 공간(128 MB)은 상당히 여유롭다고 할 수 있다
 * 두 정점 사이에 여러 개의 간선을 한 번만 저장하기에 용이한 인접 행렬 방식을 활용할 것이다 
 */

public class Main {

	static boolean[][] graph;
	static boolean[] visited;
	static int N;
	static int M;
	static int V;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		// 인접 행렬 방식으로 그래프 저장
		graph = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from][to] = true;
			graph[to][from] = true;
		}

		// DFS
		visited = new boolean[N + 1];
		DFS(V);

		sb.append("\n");

		// BFS
		visited = new boolean[N + 1];
		BFS(V);

		System.out.println(sb.toString());
	}

	static void DFS(int start) {
		sb.append(start).append(" ");
		visited[start] = true;

		for (int i = 1; i <= N; i++) {
			if (graph[start][i] == true && visited[i] == false) {
				DFS(i);
			}
		}
	}

	static void BFS(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		sb.append(start).append(" ");

		while (queue.size() > 0) {
			int current = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (graph[current][i] == true && visited[i] == false) {
					queue.add(i);
					visited[i] = true;
					sb.append(i).append(" ");
				}
			}
		}
	}

}