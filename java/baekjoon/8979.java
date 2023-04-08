import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 국가를 기준에 따라 정렬한다
 * 그리고 순위를 부여한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 올림픽 결과 저장
		List<Nation> nations = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			nations.add(new Nation(num, gold, silver, bronze));
		}

		// 올림픽 결과 정렬
		Collections.sort(nations);

		// 순위 부여
		int[] ranks = new int[N + 1];
		int rank = 1;
		for (int i = 0; i < N; i++) {
			int num = nations.get(i).num;
			if (i == 0) {
				ranks[num] = rank;
			} else {
				if (nations.get(i).isSame(nations.get(i - 1))) { // 공동 순위 처리
					ranks[num] = rank;
				} else {
					ranks[num] = rank = i + 1;
				}
			}
		}

		System.out.println(ranks[K]);
	}
}

class Nation implements Comparable<Nation> {
	int num, gold, silver, bronze;

	public Nation(int num, int gold, int silver, int bronze) {
		this.num = num;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}

	public int compareTo(Nation o) {
		if (this.gold > o.gold) {
			return -1;
		} else if (this.gold == o.gold) {
			if (this.silver > o.silver) {
				return -1;
			} else if (this.silver == o.silver) {
				if (this.bronze > o.bronze) {
					return -1;
				} else if (this.bronze == o.bronze) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	public boolean isSame(Nation o) {
		if (this.gold == o.gold && this.silver == o.silver && this.bronze == o.bronze) {
			return true;
		}
		return false;
	}
}
