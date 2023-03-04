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
 * 연산자 배치 순서에 의미가 있고 연산자 개수가 10개 이하이므로 순열 시뮬레이션한다
 * 음수가 나올 수 있고 연산에 '-'가 존재하므로 가지치기 할 수 없다
 */
public class Main {

	static int N;
	static int min, max;
	static int[] numbers;
	static char[] commands;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		numbers = new int[N - 1];
		commands = new char[N - 1];

		// 피연산자 저장
		st = new StringTokenizer(in.readLine(), " ");
		int tempResult = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N - 1; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(numbers));
//		System.out.println(tempResult);

		// 연산자 저장
		st = new StringTokenizer(in.readLine(), " ");
		int index = 0;
		int count = Integer.parseInt(st.nextToken());
		for (int i = 0; i < count; i++) {
			commands[index++] = '+';
		}
		count = Integer.parseInt(st.nextToken());
		for (int i = 0; i < count; i++) {
			commands[index++] = '-';
		}
		count = Integer.parseInt(st.nextToken());
		for (int i = 0; i < count; i++) {
			commands[index++] = '*';
		}
		count = Integer.parseInt(st.nextToken());
		for (int i = 0; i < count; i++) {
			commands[index++] = '/';
		}
//		System.out.println(Arrays.toString(commands));

		// 순열로 시뮬레이션
		permutation(0, tempResult, new boolean[N - 1]);
		sb.append(max).append("\n").append(min);
		System.out.println(sb.toString());
	}

	static void permutation(int count, int result, boolean[] used) {
		if (count >= N - 1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}

		for (int i = 0; i < N - 1; i++) {
			if (used[i] == false) {
				used[i] = true;
				switch (commands[i]) {
				case '+':
					permutation(count + 1, result + numbers[count], used);
					break;
				case '-':
					permutation(count + 1, result - numbers[count], used);
					break;
				case '*':
					permutation(count + 1, result * numbers[count], used);
					break;
				case '/':
					if (result < 0) {
						permutation(count + 1, -1 * (-1 * result / numbers[count]), used);
					} else {
						permutation(count + 1, result / numbers[count], used);
					}
					break;
				}
				used[i] = false;
			}
		}
	}

}