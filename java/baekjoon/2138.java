
import java.util.*;
import java.io.*;

/*
 * 완전탐색은 시간초과가 나는 것을 파악했으나 다른 방법이 생각나지 않아 답을 봄
 * ---------------------------------------------------
 * 처음 버튼을 누르는 경우와 누르지 않는 경우를 모두 수행한다
 * 앞에서부터 버튼을 눌러가며 목표 상태가 되는지 확인한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(in.readLine());
		char[] original = in.readLine().toCharArray();
		char[] goal = in.readLine().toCharArray();

		int min = Integer.MAX_VALUE;

		char[] current = Arrays.copyOf(original, len);
		int count = 0;
		for (int i = 1; i < len; i++) {
			if (current[i - 1] != goal[i - 1]) {
				count++;
				current[i - 1] = current[i - 1] == '0' ? '1' : '0';
				current[i] = current[i] == '0' ? '1' : '0';
				if (i + 1 < len) {
					current[i + 1] = current[i + 1] == '0' ? '1' : '0';
				}
			}
		}

//		System.out.println(count);
		if (current[len - 1] == goal[len - 1]) {
			min = Math.min(count, min);
		}

		current = Arrays.copyOf(original, len);
		count = 1;
		current[0] = current[0] == '0' ? '1' : '0';
		current[1] = current[1] == '0' ? '1' : '0';
//		System.out.println(Arrays.toString(current));
		for (int i = 1; i < len; i++) {
			if (current[i - 1] != goal[i - 1]) {
				count++;
				current[i - 1] = current[i - 1] == '0' ? '1' : '0';
				current[i] = current[i] == '0' ? '1' : '0';
				if (i + 1 < len) {
					current[i + 1] = current[i + 1] == '0' ? '1' : '0';
				}
			}
		}

//		System.out.println(count);
		if (current[len - 1] == goal[len - 1]) {
			min = Math.min(count, min);
		}

		if (min != Integer.MAX_VALUE) {
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	}

}
