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

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/*
 * 시뮬레이션한다
 * 상어를 크기가 큰 것부터 이동시키고 격자에 상어 존재 표시를 한다
 * 나중에 크기가 작은 상어는 이동시 상어 존재 표시가 있으면 소멸시켜서 큰 상어가 작은 상어를 잡아 먹는 것을 구현한다
 */
public class Main {

	static int R, C, M;
	static int caughtSize; // 잡은 상어 크기의 합
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };
	static boolean[][] isShark; // 격자 내 상어 존재 여부
	static PriorityQueue<Shark> sharks; // 상어 크기 내림차순 목록

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		caughtSize = 0;
		isShark = new boolean[R + 1][C + 1];
		sharks = new PriorityQueue();

		// 상어 목록 저장
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark current = new Shark(r, c, s, d, z);
			isShark[r][c] = true;
			sharks.add(current);
		}

//		for (int i = 1; i <= R; i++) {
//			for (int j = 1; j <= C; j++) {
//				if (isShark[i][j] == true) {
//					System.out.print(1);
//				} else {
//					System.out.print(0);
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();

		// 낚시꾼을 옮기며 상어 잡기
		for (int c = 1; c <= C; c++) {
			// 특정 열의 행을 순회하며 가장 가까운 상어를 찾는다
			for (int r = 1; r <= R; r++) {
				// 가장 가까운 상어를 잡는다(잡은 상어 크기에 더하고 상어 목록에서 제거한다)
				if (isShark[r][c] == true) {
//					System.out.println(r);
					isShark[r][c] = false;
					int tempSize = sharks.size();
					PriorityQueue<Shark> tempSharks = new PriorityQueue<>();
					for (int i = 0; i < tempSize; i++) {
						Shark current = sharks.poll();
						if (current.r == r && current.c == c) {
							caughtSize += current.z;
						} else {
							tempSharks.add(current);
						}
					}
					sharks = tempSharks;
					break;
				}
			}

//			System.out.println(Arrays.toString(sharks.toArray()));

			// 상어 이동하기
			isShark = new boolean[R + 1][C + 1];
			int sharkCount = sharks.size();
			PriorityQueue<Shark> tempSharks = new PriorityQueue<>();
			for (int i = 0; i < sharkCount; i++) {
				Shark current = sharks.poll(); // 크기가 큰 상어부터 이동한다
				int nr = current.r;
				int nc = current.c;
				int speed = current.s;
				int direction = current.d;

				// 상어의 방향이 '상' 또는 '하'인 경우
				if (direction == 1 || direction == 2) {
					speed %= (R - 1) * 2;
					for (int j = 0; j < speed; j++) {
						nr = nr + dr[direction];
						if (nr < 1 || nr > R) {
							direction++;
							if (direction > 2)
								direction = 1;
							if (nr < 1)
								nr = 1 + 1;
							else if (nr > R)
								nr = R - 1;
						}
					}
					// 상어의 방향이 '좌' 또는 '우'인 경우
				} else if (direction == 3 || direction == 4) {
					speed %= (C - 1) * 2;
					for (int j = 0; j < speed; j++) {
						nc = nc + dc[direction];
						if (nc < 1 || nc > C) {
							direction++;
							if (direction > 4)
								direction = 3;
							if (nc < 1)
								nc = 1 + 1;
							else if (nc > C)
								nc = C - 1;
						}
					}
				}
				// 상어가 이동한 위치에 다른 상어가 없으면 위치시키고 아니면 제거한다
				if (isShark[nr][nc] == false) {
//					System.out.println(current.r + ", " + current.c + " -> " + nr + ", " + nc);
					isShark[nr][nc] = true;
					Shark moved = new Shark(nr, nc, current.s, direction, current.z);
					tempSharks.add(moved);
				}
			}
			sharks = tempSharks;

//			for (int i = 1; i <= R; i++) {
//				for (int j = 1; j <= C; j++) {
//					if (isShark[i][j] == true) {
//						System.out.print(1);
//					} else {
//						System.out.print(0);
//					}
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		System.out.println(caughtSize);
	}
}

class Shark implements Comparable<Shark> {

	int r, c, s, d, z;

	public Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}

	@Override
	public int compareTo(Shark o) {
		return Integer.compare(o.z, this.z);
	}

	@Override
	public String toString() {
		return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
	}

}