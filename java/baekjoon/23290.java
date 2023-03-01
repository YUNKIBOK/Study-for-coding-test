import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 */
public class Main {

	static int M;
	static int S;

	// 물고기 움직임 모음
	static int[] dfx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dfy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	// 물고기들
	static Queue<Fish> fishes;
	// 좌표별 물고기 개수
	static int[][] fishCounts;

	// 상어 좌표
	static int sharkX;
	static int sharkY;
	// 상어 움직임 모음
	static int[] dsx = { 0, -1, 0, 1, 0 };
	static int[] dsy = { 0, 0, -1, 0, 1 };
	// 마법을 부리면서 상어의 움직임
	static int[] sharkMovements;

	// 마법을 부리면서 생기는 물고기 냄새
	static boolean[][] fishSmell;
	// 1 연습 전 물고기 냄새
	static boolean[][] fishSmell1;
	// 2 연습 전 물고기 냄새
	static boolean[][] fishSmell2;

	// 마법을 부리면서 제거할 수 있는 가장 많은 물고기 수
	static int maxKill;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		fishCounts = new int[4 + 1][4 + 1];
		fishes = new ArrayDeque();
		fishSmell1 = new boolean[4 + 1][4 + 1];
		fishSmell2 = new boolean[4 + 1][4 + 1];

		// 물고기들 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fishes.add(new Fish(fx, fy, d));
			fishCounts[fx][fy] += 1;
		}
		// 상어 좌표 저장
		st = new StringTokenizer(in.readLine(), " ");
		sharkX = Integer.parseInt(st.nextToken());
		sharkY = Integer.parseInt(st.nextToken());

		// 마법 부리기
		for (int i = 0; i < S; i++) {
			magic();
		}

		// 남은 물고기 수 세기
		int count = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				count += fishCounts[i][j];
			}
		}

		System.out.println(count);
	}

	static void magic() {
		// 1. 물고기 복제
		Queue<Fish> copyedFishes = new ArrayDeque();
		for (int i = 0; i < fishes.size(); i++) {
			Fish current = fishes.poll();
			copyedFishes.add(new Fish(current.x, current.y, current.d));
			fishes.add(new Fish(current.x, current.y, current.d));
		}

//		System.out.println("이동 전 물고기 현황");
//		System.out.println(Arrays.toString(fishes.toArray()));

		// 2. 모든 물고기 한 칸 이동
		for (int i = 0; i < fishes.size(); i++) {
			Fish current = fishes.poll();
			boolean isMove = false;
//			System.out.println(current);
			fishCounts[current.x][current.y] -= 1;
			for (int j = 0; j < 8; j++) {
				int nx = current.x + dfx[current.d];
				int ny = current.y + dfy[current.d];
//				System.out.println(nx + " "+ ny);
				if (1 <= nx && nx <= 4 && 1 <= ny && ny <= 4 && (nx != sharkX || ny != sharkY)
						&& fishSmell1[nx][ny] == false && fishSmell2[nx][ny] == false) {
					fishes.add(new Fish(nx, ny, current.d));
					fishCounts[nx][ny] += 1;
					isMove = true;
					break;
				}
				current.d -= 1;
				if (current.d == 0) {
					current.d = 8;
				}
			}
			if (isMove == false) {
				fishCounts[current.x][current.y] += 1;
				fishes.add(new Fish(current.x, current.y, current.d));
			}
		}

//		System.out.println("이동 후 물고기 현황");
//		System.out.println(Arrays.toString(fishes.toArray()));
//		System.out.println("이동 후 물고기 개수");
//		for(int i=1; i<=4;i++) {
//			System.out.println(Arrays.toString(fishCounts[i]));
//		}

		// 3. 상어 연속 3칸 이동
		maxKill = Integer.MIN_VALUE;
		fishSmell = new boolean[4 + 1][4 + 1];
		sharkMovements = new int[3];
		nHr(0, new int[3]);
//		System.out.println("상어 이동 경로");
//		System.out.println(Arrays.toString(sharkMovements));
		for (int i = 0; i < 3; i++) {
			sharkX += dsx[sharkMovements[i]];
			sharkY += dsy[sharkMovements[i]];
			int temp = fishes.size();
			for (int j = 0; j < temp; j++) {
				Fish current = fishes.poll();
				if (current.x != sharkX || current.y != sharkY) {
					fishes.add(new Fish(current.x, current.y, current.d));
				} else {
					fishCounts[current.x][current.y] -= 1;
					fishSmell[current.x][current.y] = true;
				}
			}
		}

		// 4. 두번 전 연습 물고기 냄새 제거
		for (int i = 1; i <= 4; i++) {
			System.arraycopy(fishSmell1[i], 1, fishSmell2[i], 1, 4);
			System.arraycopy(fishSmell[i], 1, fishSmell1[i], 1, 4);
		}

		// 5. 물고기 복제 마법 완료
		fishes.addAll(copyedFishes);
		while (copyedFishes.size() > 0) {
			Fish current = copyedFishes.poll();
			fishCounts[current.x][current.y] += 1;
		}

//		System.out.println("복제 후 물고기 개수");
//		for(int i=1; i<=4;i++) {
//			System.out.println(Arrays.toString(fishCounts[i]));
//		}
//		System.out.println();
	}

	static void nHr(int count, int[] commands) {
		if (count >= 3) {
//			System.out.println(Arrays.toString(commands));
			int tempKill = 0;
			boolean[][] visited = new boolean[4 + 1][4 + 1];
			int nx = sharkX;
			int ny = sharkY;
			for (int i = 0; i < 3; i++) {
				nx += dsx[commands[i]];
				ny += dsy[commands[i]];
//				System.out.println(nx + " "+ny);
				if (1 <= nx && nx <= 4 && 1 <= ny && ny <= 4) {
					if (visited[nx][ny] == false) {
						visited[nx][ny] = true;
						tempKill += fishCounts[nx][ny];
					}
				} else {
					return;
				}
			}
			if (maxKill < tempKill) {
//				System.out.println(maxKill + " "+tempKill);
				maxKill = tempKill;
				sharkMovements = Arrays.copyOf(commands, 3);
			}
			return;
		}
		for (int i = 1; i <= 4; i++) {
			commands[count] = i;
			nHr(count + 1, commands);
		}
	}

}

class Fish {

	int x, y, d;

	public Fish(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	@Override
	public String toString() {
		return "Fish [x=" + x + ", y=" + y + ", d=" + d + "]";
	}

}