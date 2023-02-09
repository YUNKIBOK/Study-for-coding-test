import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DNA에 슬라이딩 윈도우를 만들고 이를 옮겨가며 검사한다
 * A, C, G, T의 개수를 세고 그 값을 저장해둔다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String DNA = in.readLine();
		
		int countA = 0;
		int countC = 0;
		int countG = 0;
		int countT = 0;
		
		st = new StringTokenizer(in.readLine(), " ");
		int boundA = Integer.parseInt(st.nextToken());
		int boundC = Integer.parseInt(st.nextToken());
		int boundG = Integer.parseInt(st.nextToken());
		int boundT = Integer.parseInt(st.nextToken());
		
		int count = 0;
		for (int i = 0; i < S; i++) {
			if (i >= P) {
				if (countA >= boundA && countC >= boundC && countG >= boundG && countT >= boundT) {
					count++;
				}
				if (DNA.charAt(i - P) == 'A')
					countA--;
				else if (DNA.charAt(i - P) == 'C')
					countC--;
				else if (DNA.charAt(i - P) == 'G')
					countG--;
				else if (DNA.charAt(i - P) == 'T')
					countT--;
			}
			if (DNA.charAt(i) == 'A')
				countA++;
			else if (DNA.charAt(i) == 'C')
				countC++;
			else if (DNA.charAt(i) == 'G')
				countG++;
			else if (DNA.charAt(i) == 'T')
				countT++;
		}
		if (countA >= boundA && countC >= boundC && countG >= boundG && countT >= boundT) {
			count++;
		}
		System.out.println(count);
	}

}