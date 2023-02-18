import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 노드(부모 노드 번호와 레벨로 구성) 배열을 생성한다
 * 공통 조상을 찾을 때까지 레벨을 맞춰나간다
 * O(NlogN)에 해결해야 하는데 조상을 찾는 것에 대한 최악의 경우는 편향 트리인 경우이며 O(N)이다
 * ----------------------------------------------------------------
 * 문제에서 노드 연결 정보를 제공하는 규칙(둘 중 하나는 루트와 연결된 노드) 등이 명시되지 않았으므로 BFS를 활용해 노드 정보를 갱신한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		Node[] nodes = new Node[N + 1];

		// 루트 노드 생성
		nodes[1] = new Node(1, 0);

		// 나머지 노드 연결 정보 생성
		List<Integer>[] connections = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			connections[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			connections[num1].add(num2);
			connections[num2].add(num1);
		}

		// BFS로 부모 노드 및 레벨 정보 갱신
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		visited[1] = true;
		while (queue.size() > 0) {
			int current = queue.poll();
			for (int next : connections[current]) {
				if (visited[next] == false) {
					nodes[next] = new Node(current, nodes[current].level + 1);
					visited[next] = true;
					queue.add(next);
				}
			}
		}

		// 두 노드의 가장 가까운 공통 조상 찾기
		int M = Integer.parseInt(in.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			Node node1 = nodes[Integer.parseInt(st.nextToken())];
			Node node2 = nodes[Integer.parseInt(st.nextToken())];

			int level1 = node1.level;
			int level2 = node2.level;
			int minLevel = Math.min(level1, level2);

			// 가장 가까운 공통 레벨 찾기
			while (level1 != minLevel) {
				node1 = nodes[node1.parent];
				level1 = node1.level;
			}
			while (level2 != minLevel) {
				node2 = nodes[node2.parent];
				level2 = node2.level;
			}

			// 가장 가까운 공통 조상 찾기
			while (node1 != node2) {
				node1 = nodes[node1.parent];
				node2 = nodes[node2.parent];
			}

			for (int p = 1; p <= N; p++) {
				if (nodes[p] == node1) {
					sb.append(p).append("\n");
					break;
				}
			}
		}

		System.out.println(sb.toString());
	}

}

class Node {

	int parent;
	int level;

	public Node(int parent, int level) {
		super();
		this.parent = parent;
		this.level = level;
	}

}