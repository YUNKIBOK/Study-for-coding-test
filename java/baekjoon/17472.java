import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * DFS를 통해 섬을 찾는다
 * 섬마다 다리를 뻗어보고 다리가 만들어지면 우선순위 큐에 넣는다
 * 모든 섬이 이어질 때까지 우선순위 큐에서 다리를 꺼낸다 
 */
public class Main {

	static int N, M;
	static int islandCount; // 섬 개수
	static int[][] map; // 섬을 표시한 맵
	static int[][] inputMap; // 입력 맵
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] parents; // 섬들의 서로소 집합 부모
	static PriorityQueue<Bridge> pQueue;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		islandCount = 0;
		map = new int[N][M];
		inputMap = new int[N][M];
		pQueue = new PriorityQueue<>();

		// 맵 저장
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				inputMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// DFS로 섬 탐색(섬을 표시한 맵 생성)
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (inputMap[r][c] == 1) {
					islandCount++;
					DFS(r, c);
				}
			}
		}

		// 섬들의 서로소 집합 부모 초기화
		parents = new int[islandCount + 1];
		for (int i = 0; i <= islandCount; i++) {
			parents[i] = i;
		}

//		for (int r = 0; r < N; r++) {
//			System.out.println(Arrays.toString(map[r]));
//		}

		// 섬에서 뻗을 수 있는 다리 생성하여 우선순위 큐에 넣기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0) { // 섬인 경우 시도한다
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == 0) {
							int from = map[r][c];
							int len = 1;
							int to = 0;
							while (0 <= nr && nr < N && 0 <= nc && nc < M) {
								nr = nr + dr[d];
								nc = nc + dc[d];
								// 다리를 짓지 못하는 경우
								if (!(0 <= nr && nr < N && 0 <= nc && nc < M)) {
									break;
								}
								// 다리를 지을 수 있는 경우
								if (map[nr][nc] != 0) {
									to = map[nr][nc];
									break;
								}
								len++;
							}
							// 다리를 지을 수 있고 길이가 2 이상인 경우만 우선순위 큐에 넣는다
							if (to != 0 && len >= 2) {
								pQueue.add(new Bridge(from, to, len));
							}
						}
					}
				}
			}
		}

//		System.out.println(Arrays.toString(pQueue.toArray()));

		// 길이가 짧은 다리부터 섬을 이을 수 있는지 확인한다
		int bridgeCount = 0;
		int minLen = 0;
		while (pQueue.size() > 0) {
			Bridge b = pQueue.poll();
			int from = b.from;
			int to = b.to;
			if (union(from, to)) {
				bridgeCount++;
				minLen += b.len;
				// 모든 섬이 연결된 경우 종료한다
				if (bridgeCount == islandCount - 1)
					break;
			}
		}

		// 출력
		if (bridgeCount < islandCount - 1) {
			System.out.println(-1);
		} else {
			System.out.println(minLen);
		}
	}

	static void DFS(int row, int col) {
		inputMap[row][col] = 0;
		map[row][col] = islandCount;

		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			if (0 <= nr && nr < N && 0 <= nc && nc < M && inputMap[nr][nc] == 1) {
				DFS(nr, nc);
			}
		}
	}

	static int findParent(int v) {
		if (parents[v] == v)
			return v;
		return parents[v] = findParent(parents[v]);
	}

	static boolean union(int v1, int v2) {
		int p1 = findParent(v1);
		int p2 = findParent(v2);
		if (p1 == p2) {
			return false;
		}
		if (p1 > p2) {
			parents[p1] = p2;
		} else if (p2 > p1) {
			parents[p2] = p1;
		}
		return true;
	}

}

class Bridge implements Comparable<Bridge> {

	int from, to, len;

	public Bridge(int from, int to, int len) {
		this.from = from;
		this.to = to;
		this.len = len;
	}

	@Override
	public int compareTo(Bridge o) {
		return Integer.compare(this.len, o.len);
	}

	@Override
	public String toString() {
		return "Bridge [from=" + from + ", to=" + to + ", len=" + len + "]";
	}

}