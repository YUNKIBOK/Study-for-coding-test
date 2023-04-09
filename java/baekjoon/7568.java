import java.io.*;
import java.util.*;

/*
 * 기준에 따라 사람을 정렬한다
 * 그리고 순위를 부여한다
 * ------------------
 * 정렬은 앞뒤 사람과의 관계에 의해 이루어지므로 틀린다
 * 자신을 제외한 모든 사람들을 살펴보고 기준에 부합하는 사람 수를 세서 순위를 부여한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 변수 초기화
		int N = Integer.parseInt(in.readLine());

		// 입력 저장
		Person[] people = new Person[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			people[i] = new Person(i, x, y);
		}

		// 순위 부여
		int[] ranks = new int[N];
		for (int i = 0; i < N; i++) {
			int rank = 1;
			for (int j = 0; j < N; j++) {
				if (i != j && people[i].x < people[j].x && people[i].y < people[j].y) {
					rank++;
				}
			}
			ranks[people[i].n] = rank;
		}

		// 출력
		for (int i = 0; i < N; i++) {
			sb.append(ranks[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

}

class Person {
	int n, x, y;

	public Person(int n, int x, int y) {
		this.n = n;
		this.x = x;
		this.y = y;
	}
}
