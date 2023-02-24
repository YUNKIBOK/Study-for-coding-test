import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * N이 125로 큰 편이다
 * 간선에 음의 정수가 없다
 * 그리디로 접근 시 최소 금액을 구할 수 없음에 주의한다
 * 다익스트라 알고리즘을 활용한다
 */

public class Main {

	// 무한을 나타내는 상수
	static final int INF = 987_654_321;

	static byte N;
	static byte[][] map;
	static int[][] cost;

	// 상 하 좌 우
	static byte[] dr = { -1, 1, 0, 0 };
	static byte[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = 0;
		while (true) {
			T++;
			N = Byte.parseByte(in.readLine());

			// N이 0이면 출력 후 프로그램 종료
			if (N == 0) {
				System.out.println(sb.toString());
				System.exit(0);
			}

			// 동굴의 도둑 루피 정보 저장
			map = new byte[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Byte.parseByte(st.nextToken());
				}
			}

			// 동굴의 도둑 루피 누적 정보 초기화
			cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				// 무한을 나타내는 값으로 초기화
				Arrays.fill(cost[i], INF);
			}

			// 다익스트라로 최소 도둑루피 계산
			findMinRupee((byte) 0, (byte) 0);
			sb.append("Problem").append(" ").append(T).append(":").append(" ").append(cost[N - 1][N - 1]).append("\n");
		}
	}

	static void findMinRupee(byte startR, byte startC) {
		PriorityQueue<Position> pQueue = new PriorityQueue<>();
		cost[startR][startC] = map[startR][startC];
		pQueue.add(new Position(startR, startC, cost[startR][startC]));

		while (pQueue.size() > 0) {
			Position current = pQueue.poll();

			// 더 큰 도둑루피는 탐색할 필요가 없다
			if (current.cost > cost[current.r][current.c])
				continue;

			// 상 하 좌 우 탐색
			for (int d = 0; d < 4; d++) {
				byte nr = (byte) (current.r + dr[d]);
				byte nc = (byte) (current.c + dc[d]);
				if (0 <= nr && nr < N && 0 <= nc && nc < N) {
					if (cost[current.r][current.c] + map[nr][nc] < cost[nr][nc]) {
						cost[nr][nc] = cost[current.r][current.c] + map[nr][nc];
						pQueue.add(new Position(nr, nc, cost[nr][nc]));
					}
				}
			}
		}
	}

}

class Position implements Comparable<Position> {

	byte r, c;
	int cost;

	public Position(byte r, byte c, int cost) {
		this.r = r;
		this.c = c;
		this.cost = cost;
	}

	@Override
	public int compareTo(Position o) {
		if (this.cost < o.cost) {
			return -1;
		}
		return 1;
	}

}