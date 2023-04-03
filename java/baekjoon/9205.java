import java.util.*;
import java.io.*;

/*
 * 집과 락 페스티벌 사이의 경로 하나만 찾으면 된다
 * 한 번 편의점을 들리면 최대 1000미터를 이동할 수 있다
 * 시작점이 집, 도착점이 락 페스티벌로 정해져 있고 가중치는 맨해튼 거리(양수)이므로 다익스트라 알고리즘을 활용한다
 * 집, 편의점, 락 페스티벌 사이에 모든 경로가 존재하므로 간선은 2차원 배열에 저장한다(경로가 많기 때문에 2차원 배열에 저장하여도 공간 낭비가 없다고 할 수 있다)
 * 한 테스트 케이스 당 약 2MB를 사용할 수 있는데 간선 2차원 배열에 약 4*100*100/1000 = 40KB, 거리 배열에 약 4*100/1000 = 0.4KB이므로 공간이 충분하다고 판단했다
 * 인접 행렬을 활용한 다익스트라 알고리즘에서 시간 복잡도는 O(N^2)이고 주어진 테스트 케이스를 모두 수행하더라도 시간은 충분하다
 */
public class Main {

	static final int INF = 987_654_321; // 도출될 수 없는 가중치 값을 무한으로 활용한다

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(in.readLine());
		for (int i = 0; i < t; i++) {
			// 편의점 개수 저장
			int n = Integer.parseInt(in.readLine());

			// 집 저장
			Position home = new Position(0, 0);
			st = new StringTokenizer(in.readLine(), " ");
			home.x = Integer.parseInt(st.nextToken());
			home.y = Integer.parseInt(st.nextToken());

			// 0: 집, 1 ~ n: 편의점, n + 1: 락 페스티벌 배열 선언
			Position[] places = new Position[n + 2];
			places[0] = home;

			// 편의점 저장
			int x, y;
			for (int j = 1; j <= n; j++) {
				st = new StringTokenizer(in.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				places[j] = new Position(x, y);
			}

			// 락 페스티벌 저장
			Position festival = new Position(0, 0);
			st = new StringTokenizer(in.readLine(), " ");
			festival.x = Integer.parseInt(st.nextToken());
			festival.y = Integer.parseInt(st.nextToken());
			places[n + 1] = festival;

			// 간선을 인접 행렬 방식으로 저장
			int[][] edges = new int[n + 2][n + 2];
			int val;
			for (int p = 0; p < n + 2; p++) {
				for (int q = 0; q < n + 2; q++) {
					val = Math.abs(places[p].x - places[q].x) + Math.abs(places[p].y - places[q].y);
					edges[p][q] = val;
					edges[q][p] = val;
				}
			}

			// 집에서 각 장소까지의 거리 배열 초기화
			int[] distances = new int[n + 2];
			for (int p = 0; p < n + 2; p++) {
				distances[p] = INF;
			}

			// 다익스트라 알고리즘 시작
			PriorityQueue<Edge> pQueue = new PriorityQueue<>();
			pQueue.add(new Edge(0, 0));
			distances[0] = 0;

			boolean isPossible = false; // 락 페스티벌까지 도착 가능 여부
			while (pQueue.size() > 0) {
				Edge current = pQueue.poll();

				// 락 페스티벌에 도착한 경우
				if (places[current.next].x == festival.x && places[current.next].y == festival.y) {
					isPossible = true;
					break;

				}

				if (current.d > distances[current.next]) {
					continue;
				}

				for (int k = 1; k <= n + 1; k++) {
					if (k != current.next && edges[k][current.next] <= 1000 // 간선의 가중치가 1000 이하인 것만 활용
							&& distances[k] > edges[k][current.next] + current.d) {
						distances[k] = edges[k][current.next] + current.d;
						pQueue.add(new Edge(k, distances[k]));
					}
				}
			}

			// 결과 도출
			if (isPossible) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
		}

		System.out.println(sb.toString());
	}

}

// 좌표
class Position {
	int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// 간선과 거리
class Edge implements Comparable<Edge> {
	int next, d;

	public Edge(int next, int d) {
		this.next = next;
		this.d = d;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.d, o.d);
	}
}