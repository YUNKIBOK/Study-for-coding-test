import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 모든 비트는 0인 것에서 시작한다
 * 원재는 원래 메모리 값의 왼쪽 비트부터 오른쪽 비트까지 보면서
 * 이전 비트와 비교했을 때 0 -> 1 또는 1 -> 0로 바뀌는 구간의 수만큼 수정해야한다
 * 시간은 2초인데 메모리의 길이는 최대 50이므로 시간 복잡도를 고려하지 않아도 괜찮다
 * 입력 값이 많으므로 BufferedReader를 사용한다
 * 출력 값이 많으므로 BufferedWriter를 사용한다
 * 모든 비트가 0인 상태에서 시작하므로 직전 비트 또한 0으로 초기화하여 메모리를 검사한다 
 */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 표준 입력을 파일로 대체
//		System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		// 테스트 케이스 수만큼 반복
		for(int t = 1; t <=T; t++) {
			String originalMemory = in.readLine();
			char prevBit = '0';
			int editCount = 0;
			// 메모리 길이만큼 반복
			for(int i=0; i<originalMemory.length();i++) {
				if(prevBit !=originalMemory.charAt(i)) {
					editCount+=1;
					prevBit = originalMemory.charAt(i);
				}
			}
			// 테스트 케이스 출력
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			sb.append(editCount);
			sb.append("\n");
			out.append(sb.toString());
			out.flush();
			sb.setLength(0);
		}
		
	}
}
