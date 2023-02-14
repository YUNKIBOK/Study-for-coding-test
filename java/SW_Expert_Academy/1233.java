import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 트리를 만들고 순회한다
 * 연산자의 자식 노드가 불완전하면 연산이 불가능하다
 * 불완전하다는 것은 자식이 없거나 하나인 경우를 말한다
 * 숫자는 연산자가 아니기 때문에 자식이 있어서는 안된다
 * 최대 200개의 노드를 순회해야 하며 시간은 넉넉하다
 */

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static char[] tree;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(in.readLine());
			tree = new char[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int index = Integer.parseInt(st.nextToken());
				char character = st.nextToken().charAt(0);
				tree[index] = character;
			}

			sb.append("#").append(t).append(" ");
			BFS(1);
		}

		System.out.println(sb.toString());
	}

	static void BFS(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);

		while (queue.size() > 0) {
			int current = queue.poll();
			if (tree[current] >= '0' && tree[current] <= '9') { // 현재 노드가 숫자이면
				if (current * 2 < tree.length || current * 2 + 1 < tree.length) { // 자식이 있으면 불가능한 연산이다
					sb.append(0).append("\n");
					return;
				}
			} else { // 현재 노드가 연산자이면
				if (current * 2 + 1 >= tree.length) { // 자식이 두개가 아니면 불가능한 연산이다
					sb.append(0).append("\n");
					return;
				}
				// 현재 노드가 연산자이고 자식이 두개있으면 자식을 순회하여 유효성 검사를 지속한다
				queue.add(current * 2);
				queue.add(current * 2 + 1);
			}
		}
		// 유효성 검사 통과
		sb.append(1).append("\n");
	}
}