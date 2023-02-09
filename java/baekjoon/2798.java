import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 3개의 카드를 뽑는 조합을 구한다
 * 기저 조건은 카드를 3개를 뽑는 경우, 합이 M을 넘어선 경우이다
 */

public class Main {

	static int N;
	static int M;
	static int max = Integer.MIN_VALUE;
	static int[] cards;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new int[N];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		playBlackJack(0, 0, 0);
		System.out.println(max);
	}

	public static void playBlackJack(int count, int start, int sum) {
		if (count >= 3) {
			if (sum <= M) {
				max = Math.max(max, sum);
			}
			return;
		} else if (sum > M) {
			return;
		}

		for (int i = start; i < N; i++) {
			playBlackJack(count + 1, i + 1, sum + cards[i]);
		}
	}

}