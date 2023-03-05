import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			PriorityQueue<Cell> queue = new PriorityQueue<>();
			Map<String, Boolean> map = new HashMap<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (value > 0) {
						map.put(sb2.append(i).append(" ").append(j).toString(), true);
						sb2.setLength(0);
						queue.add(new Cell(i, j, value, value, false));
					}
				}
			}

			while (K-- > 0) {
//				System.out.println(Arrays.toString(queue.toArray()));
//				System.out.println(Arrays.toString(map.keySet().toArray()));
				PriorityQueue<Cell> tempQueue = new PriorityQueue<>();
				while (queue.size() > 0) {
					Cell current = queue.poll();
					if (current.leftTime == current.lifePower && current.isActive) {
						int nr, nc;
						for (int d = 0; d < 4; d++) {
							nr = current.r + dr[d];
							nc = current.c + dc[d];
							String key = sb2.append(nr).append(" ").append(nc).toString();
							sb2.setLength(0);
							if (!map.containsKey(key)) {
								map.put(key, true);
//								System.out.println(nr + " " + nc);
								tempQueue.add(new Cell(nr, nc, current.lifePower, current.lifePower, false));
							}
						}
					}
					current.leftTime -= 1;
					if (current.leftTime == 0) {
						if (current.isActive == false) {
							current.leftTime = current.lifePower;
							current.isActive = true;
							tempQueue.add(current);
						} else {
//							System.out.println("*");
							continue;
						}
					} else {
						tempQueue.add(current);
					}
				}
				queue = tempQueue;
			}
			sb.append(queue.size()).append("\n");
//			System.out.println();

		}
		System.out.println(sb.toString());
	}

}

class Cell implements Comparable<Cell> {
	int r, c, lifePower, leftTime;
	boolean isActive;

	public Cell(int r, int c, int lifePower, int leftTime, boolean isActive) {
		this.r = r;
		this.c = c;
		this.lifePower = lifePower;
		this.leftTime = leftTime;
		this.isActive = isActive;
	}

	@Override
	public int compareTo(Cell o) {
		return Integer.compare(o.lifePower, this.lifePower);
	}

	@Override
	public String toString() {
		return "Cell [r=" + r + ", c=" + c + ", lifePower=" + lifePower + ", leftTime=" + leftTime + ", isActive="
				+ isActive + "]";
	}

}