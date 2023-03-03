import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 초마다 먹을 수 있는 물고기를 찾는다
 * 먹고 걸린 시간을 누적하고 상어 크기를 갱신한 다음 상어 위치를 바꾼다
 * 더 이상 먹을 수 없으면 종료한다
 * 물고기 탐색은 상, 좌, 우, 하 순으로 하고 먹을 수 있는 것을 발견하면 바로 먹는다
 * -------------------------------------------------------------------------------
 * BFS 에서 물고기를 바로 먹으면 가까운 물고기가 거리 2의 물고기가 있는데 거리 3의 물고기를 먹는 경우가 생길 수 있고
 * 같은 거리에 있더라도 가장 위쪽, 왼쪽 물고기를 먹지 못할 수 있다
 * 초마다 먹을 수 있게 된 물고기 목록을 만들고 그 목록에서 가장 위쪽, 왼쪽에 있는 물고기를 먹어야 한다
 */

public class Main {

	static int N;
	static int secondSum; // 누적 시간
	static int eatCount; // 현재 크기에서 먹은 개수
	static int sharkSize; // 현재 상어 크기
	static int sharkR; // 상어 행 위치
	static int sharkC; // 상어 열 위치
	static int dr[] = { -1, 0, 0, 1 };
	static int dc[] = { 0, -1, 1, 0 };
	static int[][] sea; // 바다 공간 상태
	static boolean[][] visited; // BFS 시 방문 여부

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		secondSum = 0;
		eatCount = 0;
		sharkSize = 2;
		sea = new int[N][N];

		// 공간 저장 및 상어 위치 설정
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < N; c++) {
				sea[r][c] = Integer.parseInt(st.nextToken());
				if (sea[r][c] == 9) {
					sharkR = r;
					sharkC = c;
					sea[r][c] = 0;
				}
			}
		}

		// 상어는 물고기를 먹으며 이동한다
		while (true) {
			int second = eatFish(new Position(sharkR, sharkC));
			// 엄마 상어에게 도움을 요청하면 멈춘다
			if (second == 0) {
				break;
			}
			secondSum += second;
		}

		System.out.println(secondSum);
	}

	// 물고기 찾고 먹기 -> 먹으면 시간 반환, 못 먹으면 0 반환
	static int eatFish(Position start) {
		Queue<Position> queue = new ArrayDeque<>();
		visited = new boolean[N][N];
		visited[start.r][start.c] = true;
		queue.add(start);
		int second = 0;
//		System.out.println(start.r + " "+start.c);

		while (queue.size() > 0) {
			int qSize = queue.size();
			second++;
			// 매 초마다 접근할 수 있는 물고기 큐를 만든다
			while (qSize-- > 0) {
				Position current = queue.poll();
				int nr, nc;
//				System.out.println(current.r + " " + current.c + " 에서 찾기");
				for (int d = 0; d < 4; d++) {
					nr = current.r + dr[d];
					nc = current.c + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N && sea[nr][nc] <= sharkSize) {
						if (visited[nr][nc] == false) {
//							System.out.println(nr + " " + nc);
							visited[nr][nc] = true;
							queue.add(new Position(nr, nc));
						}
					}
				}
			}

			// 물고기 목록을 만들고 행 오름차순, 열 오름차순 정렬한다
			Position[] fishes = new Position[queue.size()];
			int fishCount = queue.size();
			for (int i = 0; i < fishCount; i++) {
				fishes[i] = queue.poll();
				queue.add(fishes[i]);
			}
			Arrays.sort(fishes);

			// 먹을 수 있는 물고기가 있는지 확인한다
			for (Position p : fishes) {
//				System.out.println(p.r + " " + p.c + " 탐색");
				if (sea[p.r][p.c] > 0 && sea[p.r][p.c] < sharkSize) {
					eatCount++;
					sharkR = p.r;
					sharkC = p.c;
					sea[p.r][p.c] = 0;
					if (eatCount == sharkSize) {
						eatCount = 0;
						sharkSize++;
					}
//					System.out.println(p.r + " " + p.c + " 먹기");
//					System.out.println(sharkSize);
					return second;
				}
			}
		}
		return 0;
	}

}

class Position implements Comparable<Position> {
	int r, c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int compareTo(Position o) {
		if (this.r < o.r) {
			return -1;
		} else if (this.r == o.r) {
			if (this.c < o.c) {
				return -1;
			}
			return 1;
		}
		return 1;
	}
}