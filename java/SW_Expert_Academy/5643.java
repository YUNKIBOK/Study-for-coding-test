import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 자신이 몇 번째인지 알기 위해서는 자신 앞에 몇 명이 있는지, 자신 뒤에 몇명이 있는지 정확하게 알아야 한다
 * 유향그래프에서 각 정점에서 탐색을 하고 자신 앞에 있는 개수를 센다
 * 다른 정점에서 출발해 도착한 횟수를 센다 -> 자신 뒤에 몇명이 있는지
 * 시간 복잡도는 최악의 경우에도 500(정점 수) * 500(탐색할 정점 수) * 15(테스트 케이스 수) 로 시간 안에 해결 가능하다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			// 변수 초기화
			int N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());
			int[] inGoing = new int[N + 1]; // 나보다 작은 키 수
			int[] outGoing = new int[N + 1]; // 나보다 큰 키 수

			// 그래프 초기화 및 저장
			List<Integer>[] graph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}

			// 각 정점에서 BFS 수행
			for (int start = 1; start <= N; start++) {
				boolean[] isVisited = new boolean[N + 1];
				isVisited[start] = true;
				Queue<Integer> queue = new ArrayDeque<>();
				queue.add(start);
				int count = 0;
				while (queue.size() > 0) {
					int current = queue.poll();
					for (int next : graph[current]) {
						if (isVisited[next] == false) {
							count++;
							isVisited[next] = true;
							inGoing[next]++;
							queue.add(next);
						}
					}
				}
				outGoing[start] = count;
			}

//			System.out.println(Arrays.toString(outGoing));
//			System.out.println(Arrays.toString(inGoing));

			// 결과 출력
			int result = 0;
			for (int i = 1; i <= N; i++) {
				if (inGoing[i] + outGoing[i] + 1 == N) {
					result++;
				}
			}
			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
	}

}