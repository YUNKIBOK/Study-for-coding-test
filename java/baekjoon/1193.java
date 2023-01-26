
/*
 * 0. 지그재그가 아닌 남서쪽으로 하강하는 대각선이라고 생각한다.
 * 1. 대각선의 합이 같고 남동쪽으로 갈수록 대각선의 합은 1씩 증가한다.
 * 2. 한 대각선에서 남서쪽으로 갈수록 분자는 1만큼 커지고 분모는 1만큼 작아진다.
 * 3. 1행을 기준으로 N번째 분수가 위치할 대각선을 찾는다. 
 * 4. 1행에서부터 분자와 분모를 조정하여 N번째 분수를 찾는다.
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 대각선의 1행이 몇번째인지 의미하는 변수
		int count = 0;
		// 인접한 1행의 요소들은 delta번째 차이가 난다.
		int delta = 1;
		int sonPlusMom = 1;
		while (count < N) {
			count += (delta++);
			sonPlusMom++;
		}
		// N번째 분수가 위치할 대각선 1행 요소는 startNum번째 분수이다.
		int startNum = count - delta + 2;
		int startSon = 1;
		int startMom = sonPlusMom - 1;
		int diff = N - startNum;
		// 대각선의 합이 홀수인 경우는 남서쪽으로 하강하는 대각선이고 짝수인 경우는 북동쪽으로 상승하는 대각선이다.
		if (sonPlusMom % 2 == 1) {
			System.out.println((startSon + diff) + "/" + (startMom - diff));
		} else {
			System.out.println((startMom - diff) + "/" + (startSon + diff));
		}
	}
}
