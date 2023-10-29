import java.util.*;
import java.io.*;

/*
 * BFS 또는 DFS 탐색 후 탐색 횟수를 카운트한다.
 */
public class Main {

	public static int N, M, answer;
	public static List<Integer>[] connections;
	public static boolean[] isVisited;
	public static Queue<Integer> queue;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		isVisited = new boolean[N + 1];
		connections = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			isVisited[i] = false;
			connections[i] = new ArrayList<>();
		}

		int com1, com2;
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			com1 = Integer.parseInt(st.nextToken());
			com2 = Integer.parseInt(st.nextToken());
			connections[com1].add(com2);
			connections[com2].add(com1);
		}

//		for (int i = 1; i <= N; i++) {
//			System.out.println(Arrays.toString(connections[i].toArray()));
//		}

		// BFS
		answer = 0;
		queue = new ArrayDeque<>();
		queue.add(1);
		isVisited[1] = true;
		int current;

		while (!queue.isEmpty()) {
			current = queue.poll();
			for (int next : connections[current]) {
				if (isVisited[next] == false) {
					queue.add(next);
					isVisited[next] = true;
					answer++;
				}
			}
		}

		System.out.println(answer);

	}
}