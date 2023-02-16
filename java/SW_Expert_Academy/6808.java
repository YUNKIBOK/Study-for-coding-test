import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 인영이가 카드를 내는 9! 가지의 경우에 대해 시뮬레이션한다
 * 9! = 약 30만이므로 10개의 테스트케이스를 수행하더라도 6초 안에 수행할 수 있다
 */

public class Solution {

	static int winCount;
	static int loseCount;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			boolean[] isSelected = new boolean[18 + 1];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int[] man = new int[9];
			int[] girl = new int[9];
			// 규영이의 카드 생성
			for (int i = 0; i < 9; i++) {
				int selection = Integer.parseInt(st.nextToken());
				isSelected[selection] = true;
				man[i] = selection;
			}
			// 인영이의 카드 생성
			int index = 0;
			for (int i = 1; i <= 18; i++) {
				if (isSelected[i] == false) {
					girl[index++] = i;
				}
			}
//			System.out.println(Arrays.toString(man));
//			System.out.println(Arrays.toString(girl));
			permutation(man, girl, 0, new int[9], new boolean[9]);
			sb.append(String.format("#%d %d %d\n", t, winCount, loseCount));
			winCount = 0;
			loseCount = 0;
		}

		System.out.println(sb.toString());
	}

	static void permutation(int[] man, int[] girl, int count, int[] temp, boolean[] visited) {
		if (count >= 9) {
			int score = 0;
			for (int i = 0; i < 9; i++) {
				if (man[i] > temp[i]) {
					score += (man[i] + temp[i]);
				} else {
					score -= (man[i] + temp[i]);
				}
			}

			if (score > 0) {
				winCount++;
			} else if (score < 0) {
				loseCount++;
			}

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				temp[count] = girl[i];
				permutation(man, girl, count + 1, temp, visited);
				visited[i] = false;
			}
		}
	}

}