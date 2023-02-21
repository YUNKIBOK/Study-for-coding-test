import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 파이프 하나를 만드는데 3 ^ 500의 시간 비용이 든다
 * 파이프는 최대 500개 만들 수 있으므로 10,000 * 3 ^ 500은 상당히 큰 시간 비용이다
 * 백트래킹을 한다고 해도 시간을 얼마나 절약할 수 있을지 알 수 없다
 * 3개의 열로 맵을 잘게 나누고 그 3열에 대해서만 최대 몇개의 파이프를 생성할 수 있는지 측정한다
 * 이 경우 약 10,000 * 3 ^ 3 = 약 27만이다
 * 이는 문제를 1초 안에 해결 가능할 것으로 기대된다
 * ------------------------------------------------------------
 * 위의 방법대로 구현하려 했으나 계속된 실패로 방법을 바꾸었다
 * 파이프를 최대한 위쪽에 밀착하여 설치한다
 * 그렇기 때문에 대각선 위부터 탐색하고 한번 방향을 정하면 바꾸지 않는다(탐색 횟수 감소)
 * 막다른 길을 만나면 만들어 온 파이프를 지운다(백트래킹)
 */

public class Main {

	static int R;
	static int C;
	static char[][] map;
	static int count;
	static boolean isDone;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		// 지도 입력 받기
		for (int r = 0; r < R; r++) {
			String row = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r] = row.toCharArray();
			}
		}

		for (int r = 0; r < R; r++) {
			// 위쪽에 밀착해서 파이프 만들기
			isDone = false;
			checkPipeMaxCount(r, 0);
//			for (int p = 0; p < R; p++) {
//				System.out.println(Arrays.toString(map[p]));
//			}
//			System.out.println();
		}

		System.out.println(count);
	}

	static void checkPipeMaxCount(int row, int col) {
//		for (int r = 0; r < R; r++) {
//			System.out.println(Arrays.toString(map[r]));
//		}
//		System.out.println();

		if (col >= C - 1) {
//			System.out.println("파이프 완성!!!");
			count += 1;
			map[row][col] = 'o';
			isDone = true;
			return;
		}

		// 파이프를 만드는 과정에서 도착한 지점에 방문 표시
		map[row][col] = 'o';

		int available = 0;

		// 오른쪽 위 대각선 탐색
		if (!isDone && 0 <= row - 1 && map[row - 1][col + 1] == '.') {
			available++;
			checkPipeMaxCount(row - 1, col + 1);
		}

		// 오른쪽 탐색
		if (!isDone && map[row][col + 1] == '.') {
			available++;
			checkPipeMaxCount(row, col + 1);
		}

		// 오른쪽 아래 대각선 탐색
		if (!isDone && row + 1 < R && map[row + 1][col + 1] == '.') {
			available++;
			checkPipeMaxCount(row + 1, col + 1);
		}

		// 막다른 길을 만났다면 플래그를 바꾼다
		if (available == 0) {
			isDone = false;
		}

		// 플래그에 따라 파이프 지우기
//		if (isDone == false) {
//			map[row][col] = '.';
//		}
	}

}