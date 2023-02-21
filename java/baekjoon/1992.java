import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 4분할을 이용한다
 * 재귀 종료 조건은 크기가 1인 경우이다
 */

public class Main {

	static int N;
	static char[][] video;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		video = new char[N][N];
		// 비디오 데이터 저장한다
		for (int i = 0; i < N; i++) {
			String row = in.readLine();
			video[i] = row.toCharArray();
		}
		// 비디오를 압축한다
		compress(0, 0, N);
		System.out.println(sb.toString());
	}

	static void compress(int r, int c, int size) {
		if (size == 1) {
			if (video[r][c] == '0') {
				sb.append(0);
			} else {
				sb.append(1);
			}
			return;
		}

		int zeroCount = 0;
		int oneCount = 0;
		
		exit: for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (video[i][j] == '0') {
					zeroCount++;
				} else {
					oneCount++;
				}
				// 0과 1이 섞여 있는 경우 비디오 탐색을 종료한다
				if (zeroCount != 0 && oneCount != 0) {
					break exit;
				}
			}
		}

		if (zeroCount == 0 || oneCount == 0) {
			if (zeroCount == 0) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			return;
		} else { // 0과 1이 섞여 있는 경우 4등분하여 압축한다
			int half = size / 2;
			sb.append("(");
			compress(r, c, half);
			compress(r, c + half, half);
			compress(r + half, c, half);
			compress(r + half, c + half, half);
			sb.append(")");
		}
	}

}