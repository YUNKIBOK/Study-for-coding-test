import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int W, H;
	static int[][] map;
	static int answer;
	static int temp, count;
	static boolean[][] isVisited;
	static Queue<Integer> queue;
	static int[] dr = { 0, 1, 1, 0, -1, -1 };
	static int[] dc = { 1, 1, 0, -1, 0, 1 };
	static int[] dr2 = { 0, 1, 1, 0, -1, -1 };
	static int[] dc2 = { 1, 0, -1, -1, -1, 0 };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		isVisited = new boolean[H][W];
		answer = 0;
		queue = new ArrayDeque<>();

		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
//				System.out.println(r + ", " + c + ", " + answer + " 시작");
				if (isVisited[r][c] == true) {
					continue;
				}
				temp = 0;
				count = 0;
				if (map[r][c] == 0) { // 건물이 없는 곳이면
					boolean isouter = false;
					queue.add(r * W + c);
					while (!queue.isEmpty()) {
						int current = queue.poll();
						temp += 6;
						int cr = current / W;
						int cc = current % W;
						if (0 == cr || cr == H - 1 || 0 == cc || cc == W - 1) {
							isouter = true;
						}
						isVisited[cr][cc] = true;
						if (cr % 2 == 0) {

							for (int d = 0; d < 6; d++) {
								int nr = cr + dr[d];
								int nc = cc + dc[d];
								if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == 0) {
									count++;
									if (isVisited[nr][nc] == false) {
										isVisited[nr][nc] = true;
										queue.add(nr * W + nc);
									}
								}
							}
						} else {
							for (int d = 0; d < 6; d++) {
								int nr = cr + dr2[d];
								int nc = cc + dc2[d];
								if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == 0) {
									count++;
									if (isVisited[nr][nc] == false) {
										isVisited[nr][nc] = true;
										queue.add(nr * W + nc);
									}
								}
							}
						}
					}
					if (isouter) {
//						System.out.println("안뺌");
					} else {
//						System.out.println("뺌");
						answer -= (temp - count);
					}
				} else { // 건물이 있는 곳이면
					queue.add(r * W + c);
					while (!queue.isEmpty()) {
						int current = queue.poll();
						temp += 6;
						int cr = current / W;
						int cc = current % W;
						isVisited[cr][cc] = true;
//						System.out.println(cr+" "+cc);
						if (cr % 2 == 0) {
							for (int d = 0; d < 6; d++) {
								int nr = cr + dr[d];
								int nc = cc + dc[d];
								if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == 1) {
									count++;
									if (isVisited[nr][nc] == false) {
										isVisited[nr][nc] = true;
										queue.add(nr * W + nc);
									}
								}
							}
						} else {

							for (int d = 0; d < 6; d++) {
								int nr = cr + dr2[d];
								int nc = cc + dc2[d];
								if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == 1) {
									count++;
									if (isVisited[nr][nc] == false) {
										isVisited[nr][nc] = true;
										queue.add(nr * W + nc);
									}
								}
							}
						}
					}
//					System.out.println("더함");
					answer += (temp - count);
//					System.out.println(answer + " 가 됨");
				}
			}
		}

		System.out.println(answer);
	}
}