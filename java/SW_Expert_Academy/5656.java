import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 중복 순열로 구슬을 쏠 열 번호 N개를 정한다
 * 중복 순열대로 구슬을 쏘는 시뮬레이션을 하고 최소 잔여 벽돌 개수를 갱신한다
 * 구슬을 맞췄을 때 벽돌이 제거되는 것은 BFS로 구현한다
 * 구슬을 맞추고 나서 잔여 벽돌들로 맵이 재구성한다
 */

public class Solution {

	static int N, W, H;
	static int minLeftBlockCnt;
	static int[] dc = { 0, 0, -1, 1 };
	static int[] dr = { -1, 1, 0, 0 };
	static int[][] map; // 초기 맵
	static int[][] tempMap; // 시뮬레이션용 임시 맵

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			minLeftBlockCnt = Integer.MAX_VALUE;
			map = new int[H][W];

			// 초기 맵 저장
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 중복 순열 시뮬레이션
			duplicatePermutation(0, new int[N]);
			sb.append(minLeftBlockCnt).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void duplicatePermutation(int count, int[] order) {
		if (count >= N) {

//			System.out.println("시뮬레이션 시작");
//			System.out.println(Arrays.toString(order));

			// 임시 맵 생성
			tempMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, W);
			}

			// 구슬 N번 쏘기
			for (int i = 0; i < N; i++) {
				int target = order[i];

//				System.out.println("구슬 쏘기 전");
//				for (int p = 0; p < H; p++) {
//					System.out.println(Arrays.toString(tempMap[p]));
//				}

				// 구슬 쏠 행 구하기
				int startRow = H - 1;
				for (int r = 0; r < H; r++) {
					if (tempMap[r][target] != 0) {
						startRow = r;
						break;
					}
				}

//				System.out.println(startRow+"행을 쏜다");

				// 구슬 쏘기
				BFS(new Block(startRow, target, tempMap[startRow][target]));

//				System.out.println("구슬 쏜 후");
//				for (int p = 0; p < H; p++) {
//					System.out.println(Arrays.toString(tempMap[p]));
//				}

				// 사라진 블록에 의한 맵 수정
				for (int c = 0; c < W; c++) {
					Queue<Integer> leftBlocks = new ArrayDeque<>();
					for (int r = H - 1; r >= 0; r--) {
						if (tempMap[r][c] != 0) {
							leftBlocks.add(tempMap[r][c]);
							tempMap[r][c] = 0;
						}
					}
					int r = H - 1;
					while (leftBlocks.size() > 0) {
						tempMap[r--][c] = leftBlocks.poll();
					}
				}

//				System.out.println("맵 바꾼 후");
//				for (int p = 0; p < H; p++) {
//					System.out.println(Arrays.toString(tempMap[p]));
//				}
			}

			// 잔여 블록 개수 계산
			int leftBlockCnt = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (tempMap[r][c] != 0)
						leftBlockCnt++;
				}
			}

			// 최소 잔여 블록 개수 갱신
			if (leftBlockCnt < minLeftBlockCnt) {
				minLeftBlockCnt = leftBlockCnt;
			}

			return;
		}

		// 중복 순열 생성
		for (int i = 0; i < W; i++) {
			order[count] = i;
			duplicatePermutation(count + 1, order);
		}
	}

	static void BFS(Block start) {
		Queue<Block> queue = new ArrayDeque<>();
		queue.add(start);
		tempMap[start.r][start.c] = 0;

		while (queue.size() > 0) {
			Block current = queue.poll();
			for (int i = 1; i < current.num; i++) {
				for (int j = 0; j < 4; j++) {
					int nr = current.r + dr[j] * i;
					int nc = current.c + dc[j] * i;
					if (0 <= nr && nr < H && 0 <= nc && nc < W && tempMap[nr][nc] != 0) {
						queue.add(new Block(nr, nc, tempMap[nr][nc]));
						tempMap[nr][nc] = 0;
					}
				}
			}
		}
	}

}

class Block {

	int r, c, num;

	public Block(int r, int c, int num) {
		this.r = r;
		this.c = c;
		this.num = num;
	}

}