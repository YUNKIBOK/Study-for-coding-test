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
 * 노드는 부모 노드 정보, 자식 노드들 정보, 인구 수로 정의한다
 * 루트 노드는 1로 가정한다
 * 트리에는 순환이 없기 때문에 어느 노드나 루트가 될 수 있다
 * 다만, 문제에서 N의 크기와 상관없이 1번 노드는 항상 존재하므로 1번 노드를 루트 노드로 가정한다
 * 노드 연결 정보를 저장 후 BFS를 활용하여 부모 노드 정보와 자식 노드들 정보를 업데이트 한다
 * 인구 수 내림차순 정렬 후 노드를 순회하며 우수 마을을 선정해나간다
 * 우수 마을은 인접한 마을의 선정을 방해하므로 인구 수가 같은 노드는 인접한 마을 수가 적은 것이 우선 순위가 높아야 한다
 * ---------------------------------------------------------------------------
 * 이 문제는 그리디로 접근 시 최적의 답을 보장할 수 없는 것 같다
 * 어떤 노드를 루트 노드로 했을 때 서브 트리 중 우수 노드들의 인구 수 합을 다이나믹 프로그래밍을 이용해서 구해야 한다
 * 어떤 노드가 우수 마을이라면 자식 노드들은 우수 마을이어서는 안된다
 * 어떤 노드가 우수 마을이 아니라면 자식 노드들은 우수 마을일 수도 있고 우수 마을이 아닐 수도 있다
 */

public class Main {

	static int N;
	static boolean[] visited; // DFS 방문 여부
	static Node[] nodes;
	static List<Integer>[] connections;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		nodes = new Node[N + 1];
		connections = new ArrayList[N + 1];

		// 노드 배열 및 연결 정보 인접 리스트 초기화
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			connections[i] = new ArrayList();
			nodes[i] = new Node();
			nodes[i].population = Integer.parseInt(st.nextToken());
		}

		// 연결 정보 저장
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			connections[node1].add(node2);
			connections[node2].add(node1);
		}

		// DFS를 통해 dp 테이블을 만든다
		visited = new boolean[N + 1];
		dp = new int[N + 1][2]; // 0열은 해당 노드가 우수 마을이 아닐 때, 1열은 해당 노드가 우수 마을일 때
		DFS(1); // 전체 트리의 루트 노드는 1이라고 가정
		System.out.println(Math.max(dp[1][0], dp[1][1])); // 완성 된 dp 테이블에서 우수 마을 인구 수 합의 최댓값을 구한다
	}

	static void DFS(int start) {
		int current = start;
		visited[current] = true;

		// 자식 노드가 있으면 깊숙히 탐색한다
		for (int next : connections[current]) {
			if (visited[next] == false) {
				DFS(next);
			}
		}
		// 자식 노드 탐색을 마치고 dp 테이블을 갱신한다
		// 현재 노드가 우수 마을이라면
		int ifSelected = nodes[current].population;
		for (int next : connections[current]) {
			ifSelected += dp[next][0];
		}
		dp[current][1] = ifSelected;

		// 현재 노드가 우수 마을이 아니라면
		int ifNotSelected = 0;
		for (int next : connections[current]) {
			ifNotSelected += Math.max(dp[next][0], dp[next][1]);
		}
		dp[current][0] = ifNotSelected;
	}

}

class Node {

	int population = 0;
	List<Integer> children = new ArrayList<>();

}