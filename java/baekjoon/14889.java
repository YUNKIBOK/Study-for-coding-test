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
 * 정확하게 N / 2 명을 뽑아 팀을 만들어야 하므로 조합을 사용한다
 * 최악의 경우 20C10 = 약 17만이다
 * 조합이 마무리 된 시점에 두 팀의 능력치를 계산하고 차이의 최소값을 갱신한다
 */
public class Main {

	static int N;
	static int min;
	static int[][] abilities;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		N = Integer.parseInt(in.readLine());
		min = Integer.MAX_VALUE;
		abilities = new int[N][N];

		// 능력치 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				abilities[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 조합 후 두 팀 능력치 차이의 최솟값 갱신
		combination(0, 0, new int[N / 2]);
		System.out.println(min);
	}

	static void combination(int count, int start, int[] selection) {
		if (count >= N / 2) {
			int team1 = 0;
			int team2 = 0;

			// 팀 1의 능력치 계산
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					if (i != j) {
						team1 += abilities[selection[i]][selection[j]];
					}
				}
			}

			// 팀 2가 될 선수 표시
			boolean[] selected = new boolean[N];
			for (int i = 0; i < N / 2; i++) {
				selected[selection[i]] = true;
			}

			// 팀 2의 능력치 계산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j && selected[i] == false && selected[j] == false) {
						team2 += abilities[i][j];
					}
				}
			}

			min = Math.min(min, Math.abs(team1 - team2));

			return;
		}

		for (int i = start; i < N; i++) {
			selection[count] = i;
			combination(count + 1, i + 1, selection);
		}
	}

}