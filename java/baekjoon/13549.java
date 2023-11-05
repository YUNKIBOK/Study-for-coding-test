import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int N, K;
	static boolean[] isVisited;
	static Queue<Position> queue;
	static int[] time;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		isVisited = new boolean[10_0000 + 1];
		time = new int[100_000 + 1];
		for (int i = 0; i <= 100_000; i++) {
			time[i] = 100_000;
		}
		queue = new PriorityQueue<>();

		queue.add(new Position(0, N));
		isVisited[N] = true;
		time[N] = 0;

		while (!queue.isEmpty()) {
//			System.out.println(queue.toString());

			Position current = queue.poll();
//			if (current.value == K) {
//				System.out.println(current.time);
//				System.exit(0);
//			}

			if (current.value * 2 <= 100_000 // && isVisited[current.value * 2] == false
					&& time[current.value * 2] > current.time) {
//				isVisited[current.value * 2] = true;
				time[current.value * 2] = current.time;
				queue.add(new Position(current.time, current.value * 2));
			}

			if (current.value + 1 <= 100_000 // && isVisited[current.value + 1] == false
					&& time[current.value + 1] > current.time + 1) {
//				isVisited[current.value + 1] = true;
				time[current.value + 1] = current.time + 1;
				queue.add(new Position(current.time + 1, current.value + 1));
			}

			if (current.value - 1 >= 0 // && isVisited[current.value - 1] == false
					&& time[current.value - 1] > current.time + 1) {
//				isVisited[current.value - 1] = true;
				time[current.value - 1] = current.time + 1;
				queue.add(new Position(current.time + 1, current.value - 1));
			}
		}

		System.out.println(time[K]);
	}
}

class Position implements Comparable<Position> {
	int time, value;

	public Position(int time, int value) {
		this.time = time;
		this.value = value;
	}

	public String toString() {
		return "(" + time + "," + value + ")";
	}

	@Override
	public int compareTo(Position o) {
		// TODO Auto-generated method stub
		if (this.time > o.time) {
			return 1;
		} else if (this.time == o.time) {
			return 0;
		} else {
			return -1;
		}
	}
}