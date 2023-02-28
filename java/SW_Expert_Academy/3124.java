import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 크루스칼 알고리즘을 활용한다
 * 간선 리스트를 만들고 비용 오름차순으로 정렬한다
 * 가장 비용이 적은 간선부터 Union 연산을 해나간다
 */

public class Solution {

	static int V, E;
	static long minCostSum;
	static int usedEdgeCount;
	static int[] parents;
	static Edge[] edges;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			minCostSum = 0L;
			usedEdgeCount = 0;
			parents = new int[V + 1];
			edges = new Edge[E];

			// 서로소 집합 부모 초기화
			for (int v = 1; v <= V; v++) {
				parents[v] = v;
			}

			// 간선 리스트 생성 후 비용 오름차순 정렬
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges[e] = new Edge(from, to, cost);
			}
			Arrays.sort(edges);

			// 크루스칼 알고리즘 수행
			for (Edge e : edges) {
				if (union(e.from, e.to)) {
					minCostSum += e.cost;
					usedEdgeCount++;
					if (usedEdgeCount == V - 1) {
						break;
					}
				}
			}
			sb.append(minCostSum).append("\n");
		}

		System.out.println(sb.toString());
	}

	static int findParent(int vertex) {
		if (parents[vertex] == vertex)
			return vertex;
		return parents[vertex] = findParent(parents[vertex]);
	}

	static boolean union(int v1, int v2) {
		int p1 = findParent(v1);
		int p2 = findParent(v2);
		if (p1 == p2) {
			return false;
		}
		if (p1 > p2) {
			parents[p1] = p2;
		} else {
			parents[p2] = p1;
		}
		return true;
	}

}

class Edge implements Comparable<Edge> {

	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}

}