import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 큐에 숫자를 넣고 빼며 시뮬레이션한다
 * 입력이 많으므로 BufferedReader를 사용한다
 * 출력이 많으므로 StringBuilder를 사용하고 마지막에 한 번 출력한다
 * 각 수는 integer 범위를 넘지 않으므로 int 타입을 사용한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String strT;
		while ((strT = in.readLine()) != null) {
			st = new StringTokenizer(in.readLine(), " ");
			Queue<Integer> queue = new ArrayDeque<Integer>();
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}

			int count = 1;
			while (true) {
				int front = queue.poll();
				front -= count;
				if (front <= 0) {
					queue.add(0);
					break;
				}
				queue.add(front);
				count %= 5;
				count += 1;
			}
			sb.append("#").append(strT).append(" ");
			for (int i = 0; i < 8; i++)
				sb.append(queue.poll()).append(" ");
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}