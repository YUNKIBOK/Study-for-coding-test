
import java.util.*;
import java.io.*;

/*
 * 순서에 따라 시뮬레이션한다
 */
public class Main {

	static int N, K;
	static int[] belts; // 벨트 내구도
	static boolean[] isExist; // 로봇 존재 여부
	static int step; // 진행 단계
	static int zeroCount; // 내구도 0인 칸 개수

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belts = new int[2 * N + 1];
		isExist = new boolean[N + 1];
		step = 0;
		zeroCount = 0;

		// 벨트 내구도 저장
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= 2 * N; i++) {
			belts[i] = Integer.parseInt(st.nextToken());
		}

		// 시뮬레이션
		while (true) {
			step++;

			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
			int temp = belts[2 * N];
			for (int i = 2 * N; i >= 2; i--) {
				belts[i] = belts[i - 1];
				if (i <= N) {
					isExist[i] = isExist[i - 1];
				}
			}
			belts[1] = temp;
			isExist[1] = false;
			isExist[N] = false;

			// 2. 로봇 이동
			for (int i = N - 1; i >= 1; i--) {
				if (isExist[i] == true) {
					if (isExist[i + 1] == false && belts[i + 1] > 0) {
						isExist[i + 1] = true;
						isExist[i] = false;
						belts[i + 1]--;
						if (belts[i + 1] == 0) {
							zeroCount++;
						}
					}
				}
			}

			// 3. 로봇 올리기
			if (belts[1] > 0) {
				isExist[1] = true;
				belts[1]--;
				if (belts[1] == 0) {
					zeroCount++;
				}
			}

			// 4. 종료 검사
			if (zeroCount >= K) {
				break;
			}
		}

		System.out.println(step);
	}
}
