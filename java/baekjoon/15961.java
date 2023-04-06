import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * K개 접시를 연속 선택하는 경우와 연속 선택하지 않는 경우가 있다
 * K개의 접시를 연속 선택하지 않는 경우는 회전 초밥의 종류를 세고 먹을 수 있는 최대 종류 값을 구하면 된다
 * --------------------------------------------------------------
 * 문제를 잘 읽으면 이벤트에 참여했을 때 가장 많이 먹을 수 있는 초밥 종류를 구해야 한다
 * 따라서 K개 접시를 연속으로 선택하는 경우만 고려한다
 * 이 문제는 3,000,000 * 3,000 이 굉장히 크므로 시간 최적화를 생각해야 한다
 * 연속으로 먹기 시작하는 위치에서 k개를 먹었을 때 종류를 일일이 세면 시간 초과가 발생할 것이다
 * K개의 접시를 연속 선택하는 경우는 슬라이딩 윈도우를 활용한다 -> 시간 복잡도를 낮출 수 있다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// 초밥 저장
		int[] sushis = new int[N];
		for (int i = 0; i < N; i++) {
			sushis[i] = Integer.parseInt(in.readLine());
		}

		// 슬라이딩 윈도우를 위한 변수 초기화
		int answer = 0; // 최종 답
		int front = 0; // 윈도우 앞 포인터
		int rear = k - 1; // 윈도우 뒤 포인터
		int[] eatCnt = new int[d + 1]; // i번째 초밥을 먹은 개수
		int current = 0; // 현재 윈도우에서 먹을 수 있는 초밥 종류 수
		boolean isPlus = false; // 쿠폰 초밥 추가 여부

		// 첫 슬라이드 계산
		for (int i = front; i <= rear; i++) {
			if (eatCnt[sushis[i]] == 0) {
				current++;
			}
			eatCnt[sushis[i]] += 1;
		}
		if (eatCnt[c] == 0) {
			isPlus = true;
			current++;
		}
		answer = Math.max(answer, current);
		if (isPlus) {
			current--;
			isPlus = false;
		}

		// 나머지 슬라이드 계산
		for (int i = 0; i < N; i++) {
			// 앞 포인터 밀기
			eatCnt[sushis[front]] -= 1;
			if (eatCnt[sushis[front]] == 0) {
				current -= 1;
			} else if (eatCnt[sushis[front]] < 0) {
				eatCnt[sushis[front]] = 0;
			}
			front++;
			front %= N;

			// 뒤 포인터 밀기
			rear++;
			rear %= N;
			if (eatCnt[sushis[rear]] == 0) {
				current += 1;
			}
			eatCnt[sushis[rear]] += 1;

			// 쿠폰 초밥 고려하기
			if (eatCnt[c] == 0) {
				current++;
				isPlus = true;
			}
			answer = Math.max(answer, current);
			if (isPlus) {
				current--;
				isPlus = false;
			}
		}

		System.out.println(answer);
	}
}
