import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 흰색 100 * 100 도화지에 스카프를 붙여나간다(false면 흰색 true면 검은색 스카프)
 * 검은색으로 칠해진 칸들을 순회하며 4방 탐색한다
 * 만약 탐색한 곳이 흰색이면 경계임을 의미하므로 둘레를 1만큼 더한다
 * 도화지 밖을 넘어선 부분은 탐색할 수 없지만 경계이므로 둘레를 1만큼 더한다 
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("1번_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		int N = Integer.parseInt(in.readLine());
		boolean[][] paper = new boolean[100][100]; // 흰색 도화지
		int[] dr = { -1, 1, 0, 0 }; // 행 4방 탐색 도우미
		int[] dc = { 0, 0, -1, 1 }; // 열 4방 탐색 도우미
		int meter = 0; // 둘레 길이

		// 스카프 배치하기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			for (int r = startR; r < startR + 10; r++) {
				for (int c = startC; c < startC + 10; c++) {
					paper[r][c] = true;
				}
			}
		}

// 스카프 배치 확인용 코드
//			for(int p=0; p<100;p++) {
//				for(int q=0; q<100; q++) {
//					if(paper[p][q]==false) {
//						System.out.print("x");
//					}
//					else {
//						System.out.print("o");
//					}
//				}
//				System.out.println();
//			}

		// 도화지 탐색하며 둘레 계산하기
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (paper[r][c] == true) { // 스카프가 붙여져 있으면 4방 탐색 후 둘레를 더한다
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						// 스카프가 도화지 끝에 딱 맞게 붙여진 경우 해당 방향에 대한 둘레를 더한다
						if (nr == -1 || nr == 100 || nc == -1 || nc == 100) {
							meter += 1;
						}
						// 스카프와 도화지의 경계를 만난 경우 둘레를 더한다
						if (0 <= nr && nr < 100 && 0 <= nc && nc < 100 && paper[nr][nc] == false) {
							meter += 1;
// 둘레 학인용 코드
//							System.out.println(nr  + " "+nc);
						}
					}
				}
			}
		}

		System.out.println(meter);
	}
}