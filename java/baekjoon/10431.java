import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 줄세기우기 방법이 삽입 정렬 그 자체이다
 * 삽입 정렬을 구현하고 원소가 뒤로 물러나는 수를 센다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int P = Integer.parseInt(in.readLine());
		for (int t = 1; t <= P; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			int tNum = Integer.parseInt(st.nextToken());
			int[] line = new int[20];
			int stepBack = 0;

			for (int i = 0; i < 20; i++) {
				line[i] = Integer.parseInt(st.nextToken());
				for (int j = i - 1; j >= 0; j--) {
					if (line[j] > line[j + 1]) {
						stepBack++;
						int temp = line[j];
						line[j] = line[j + 1];
						line[j + 1] = temp;
					} else {
						break;
					}
				}
			}

			sb.append(tNum).append(" ").append(stepBack).append("\n");
		}

		System.out.println(sb.toString());
	}
}
