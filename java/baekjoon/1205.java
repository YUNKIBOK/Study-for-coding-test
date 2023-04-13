import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 뒤에서부터 몇등인지 세어 본다
 * 점수는 20억보다 작거나 같으므로 int 자료형을 사용한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		// 변수 초기화
		int N = Integer.parseInt(st.nextToken());
		int taesu = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		// 랭킹이 존재하지 않는 겨우
		if (N == 0) {
			System.out.println(1);
			System.exit(0);
		}

		// 랭킹 저장
		int[] ranking = new int[N];
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			ranking[i] = Integer.parseInt(st.nextToken());
		}

		// 랭킹 및 점수 개수 카운트
		int rank = 1;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (ranking[i] > taesu) {
				rank++;
				cnt++;
			} else if (ranking[i] == taesu) {
				cnt++;
			} else {
				break;
			}
		}

		// 출력
		if (cnt >= P) {
			System.out.println(-1);
		} else {
			System.out.println(rank);
		}
	}

}
