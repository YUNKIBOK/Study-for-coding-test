import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * DFS 탐색을 통해 탐색의 깊이가 4 이상이 되는 곳이 있는지 확인한다
 * 시작점을 바꿔가며 시도해야 한다
 * 한번 방문한 곳은 방문하지 않아야 한다
 * 노드 수도 많지만 간선 수도 상당히 많다 그러므로 인접 행렬 방식을 채택한다
 * 간선은 양방향 간선(친구 관계)이며 가중치가 없으므로 boolean 타입의 배열에 저장한다
 * 2,000 * 2,000 = 4,000,000 bit = 0.5 MB이므로 공간 복잡도는 넉넉하다
 * ----------------------------------------------------------------------------------
 * 인접 행렬 방식 채택 시 시간 초과가 발생합니다
 * 친구 관계가 없는 것도 순회하기 때문입니다
 */

public class Main {

	static int N;
	static int M;
	static boolean[] visited;
	static List<Integer>[] friendship;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 값 할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		friendship = new ArrayList[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			friendship[i] = new ArrayList<>();
		}

		// 친구 관계 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int person1 = Integer.parseInt(st.nextToken());
			int person2 = Integer.parseInt(st.nextToken());

			friendship[person1].add(person2);
			friendship[person2].add(person1);
		}

		for (int i = 0; i < N; i++) {
			visited[i] = true;
			DFS(i, 0);
			visited[i] = false;
		}

		// A-B-C-D-E와 같은 친구 관계가 존재하지 않는 경우
		System.out.println(0);
	}

	static void DFS(int current, int depth) {
		// A-B-C-D-E와 같은 친구 관계가 존재하는 경우
		if (depth >= 4) {
			System.out.println(1);
			System.exit(0);
		}

		for (int next : friendship[current]) {
			if (visited[next] == false) {
				visited[current] = true;
				DFS(next, depth + 1);
				visited[current] = false;
			}
		}
	}

}