import java.util.*;
import java.io.*;
import java.math.BigInteger;

/*
 * 주어진 범위는 샘터의 범위이지 집의 범위가 아님에 주의
 */
public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] isVisited;
	static Queue<Integer> queue;
	static int[] dr = { -1, 1 };
	static int distance, count;
	static BigInteger answer;
	static int N, K;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		distance = 0;
		isVisited = new boolean[200_200_000 + 1];
		answer = new BigInteger("0");
		st = new StringTokenizer(br.readLine());
		queue = new ArrayDeque<>();
		for (int n = 0; n < N; n++) {
			int sam = Integer.parseInt(st.nextToken());
			sam += 100_100_000;
			isVisited[sam] = true;
			queue.add(sam);
		}

		distance = 0;
		count = 0;
		while (!queue.isEmpty()) {
			distance++;
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int current = queue.poll();
//				System.out.println("current: " + current);
				for (int d = 0; d < 2; d++) {
					int next = current + dr[d];
					if (isVisited[next] == false) {
						isVisited[next] = true;
						answer = answer.add(new BigInteger(distance + ""));
						queue.add(next);
//						System.out.println(next);
						count++;
						if (count == K) {
							System.out.println(answer);
							System.exit(0);
						}
					}
				}
			}
		}
	}
}
