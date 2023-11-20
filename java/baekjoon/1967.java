import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static List<Edge>[] map;
	static Queue<Edge> queue;
	static boolean[] isVisited;
	static int answer;
	static int n;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			map[i] = new ArrayList<Edge>();
		}

		int n1, n2, v;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			map[n1].add(new Edge(n2, v));
			map[n2].add(new Edge(n1, v));
		}

		answer = 0;
		for (int i = 1; i <= n; i++) {
			// 단말노드이면 BFS
//			if (map[i].size() == 1) {
//				queue = new ArrayDeque<Edge>();
//				isVisited = new boolean[n + 1];
//				tempAnswer = 0;
//				queue.add(new Edge((char)i, (char)0));
//
//				while (!queue.isEmpty()) {
//					Edge current = queue.poll();
//					if(current.value > tempAnswer) {
//						tempAnswer = current.value;
//					}
//					for (Edge e : map[current.next]) {
//						if (isVisited[e.next] == false) {
//							isVisited[e.next] = true;
//							queue.add(new Edge(e.next, (char) (current.value + e.value)));
//						}
//					}
//				}
//
//				if(tempAnswer > answer) {
//					answer = tempAnswer;
//				}
//			}

			if (map[i].size() == 1) {
				isVisited = new boolean[n + 1];
				DFS(i, 0);
			}

		}

		System.out.println((int) answer);
	}

	public static void DFS(int current, int value) {
		isVisited[current] = true;
		answer = Math.max(answer, value);

		for (Edge e : map[current]) {
			if (isVisited[e.next] == false) {
				DFS(e.next, value + e.value);
			}
		}

	}
}

class Edge {
	int next;
	int value;

	public Edge(int next, int value) {
		this.next = next;
		this.value = value;
	}
}