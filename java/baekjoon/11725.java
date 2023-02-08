import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 양방향 그래프를 만든다
 * 루트인 1에서부터 깊이우선탐색(DFS)한다
 * 어떤 노드를 방문하기 직전의 노드가 그 노드의 부모이다
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		List<List<Integer>> nodes = new ArrayList<List<Integer>>();
		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<Integer>());
		}

		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			nodes.get(v1).add(v2);
			nodes.get(v2).add(v1);
		}

//		for(int i=1; i<=N;i++) {
//			System.out.println(Arrays.toString(nodes.get(i).toArray()));
//		}

		boolean[] isVisited = new boolean[N + 1];
		int[] parents = new int[N + 1];
		Stack<Integer> stack = new Stack<Integer>();

		stack.push(1);
		isVisited[1] = true;

		DFS(nodes, isVisited, stack, parents);
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		out.append(sb.toString());
		out.flush();
	}

	public static void DFS(List<List<Integer>> nodes, boolean[] isVisited, Stack<Integer> stack, int[] parents) {
//		System.out.println(stack.toString());
		if (stack.size() == 0) {
			return;
		}

		int current = stack.pop();
		List<Integer> connections = nodes.get(current);
		for (int n : connections) {
			if (!isVisited[n]) {
				isVisited[n] = true;
				stack.push(n);
				parents[n] = current;
			}
		}
		DFS(nodes, isVisited, stack, parents);
	}

}
