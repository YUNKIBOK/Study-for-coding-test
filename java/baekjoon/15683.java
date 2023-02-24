import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 */

public class Main {

	// 최소 사각지대 개수
	static int min;

	static int N;
	static int M;
	static int[][] map;

	// CCTV 타입, 좌표 정보
	static List<CCTV> CCTVs;

	// 상 하 좌 우
	static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	// 인덱스 0: 모드 모음, 인덱스 1: 모드(시계 방향 회전), 인덱스 2: 모드 별 시야 전진 방향
	static int[][][] oneDirections = { { d[3] }, { d[1] }, { d[2] }, { d[0] } };
	static int[][][] twoDirections = { { d[2], d[3] }, { d[0], d[1] }, { d[2], d[3] }, { d[0], d[1] } };
	static int[][][] threeDirections = { { d[0], d[3] }, { d[1], d[3] }, { d[1], d[2] }, { d[0], d[2] } };
	static int[][][] fourDirections = { { d[0], d[2], d[3] }, { d[0], d[1], d[3] }, { d[1], d[2], d[3] },
			{ d[0], d[1], d[2] } };
	static int[][][] fiveDirections = { { d[0], d[1], d[2], d[3] }, { d[0], d[1], d[2], d[3] },
			{ d[0], d[1], d[2], d[3] }, { d[0], d[1], d[2], d[3] } };

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		map = new int[N][M];
		CCTVs = new ArrayList<>();

		// 맵 저장 및 CCTV 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					CCTVs.add(new CCTV(map[i][j], i, j));
				}
			}
		}

//		System.out.println(Arrays.toString(CCTVs.toArray()));

		// 중복 조합 후 시뮬레이션
		nHr(0, new int[CCTVs.size()]);
		System.out.println(min);
	}

	static void nHr(int count, int[] modes) {
		// 중복 조합이 완성된 경우
		if (count >= CCTVs.size()) {
			// 맵 복사
			int[][] tempMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}

			// CCTV 별 시야 밝히기
			for (int i = 0; i < CCTVs.size(); i++) {
				CCTV current = CCTVs.get(i);
				int mode = modes[i];

				// CCTV 시야 전진 방향 가져오기
				int[][] movements = null;
				switch (current.type) {
				case 1:
					movements = oneDirections[mode];
					break;
				case 2:
					movements = twoDirections[mode];
					break;
				case 3:
					movements = threeDirections[mode];
					break;
				case 4:
					movements = fourDirections[mode];
					break;
				case 5:
					movements = fiveDirections[mode];
					break;
				}

//				for (int p = 0; p < movements.length; p++) {
//					System.out.println(Arrays.toString(movements[p]));
//				}

				// CCTV 시야 밝히기
				for (int[] d : movements) {
					int nr = current.r + d[0];
					int nc = current.c + d[1];
					// 맵을 벗어나거나 벽을 만날 때까지 반복한다
					while (0 <= nr && nr < N && 0 <= nc && nc < M && tempMap[nr][nc] != 6) {
//						System.out.println(nr + " " + nc);
						if (tempMap[nr][nc] == 0) {
							tempMap[nr][nc] = '#';
						}
						nr = nr + d[0];
						nc = nc + d[1];
					}
				}
			}

			// 사각지대 계산 후 갱신
			int inVisibleCount = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (tempMap[i][j] == 0) {
						inVisibleCount += 1;
					}
				}
			}
			min = Math.min(min, inVisibleCount);
			return;
		}

		// 중복 조합 생성 과정
		for (int i = 0; i < 4; i++) {
			modes[count] = i;
			nHr(count + 1, modes);
		}
	}

}

class CCTV {

	int type;
	int r;
	int c;

	public CCTV(int type, int r, int c) {
		this.type = type;
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "CCTV [type=" + type + ", r=" + r + ", c=" + c + "]";
	}

}