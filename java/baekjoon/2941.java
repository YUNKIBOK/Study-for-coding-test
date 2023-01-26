
/*
 * 1. 문자열을 입력 받는다
 * 2. 문자열의 처음부터 끝까지 길이 2 또는 길이 3의 서브 문자열을 만들며 크로아티아 알파벳이 되는지 체크한다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {

		String[] croatiaApphabets = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String wordStr = br.readLine();
		int alphabetCnt = 0;
		int continueCnt = 0;

		for (int i = 0; i < wordStr.length(); i++) {
			if (continueCnt > 0) {
				continueCnt--;
				continue;
			}
			// 길이 2의 서브 문자열 체크
			if (i + 2 <= wordStr.length()) {
				String subStr2 = wordStr.substring(i, i + 2);
				for (int j = 0; j < croatiaApphabets.length; j++) {
					if (subStr2.equals(croatiaApphabets[j])) {
						alphabetCnt++;
						continueCnt = 1;
						break;
					}
				}
				if (continueCnt > 0) {
					continue;
				}
			}
			// 길이 3의 서브 문자열 체크
			if (i + 3 <= wordStr.length()) {
				String subStr3 = wordStr.substring(i, i + 3);
				for (int j = 0; j < croatiaApphabets.length; j++) {
					if (subStr3.equals(croatiaApphabets[j])) {
						alphabetCnt++;
						continueCnt = 2;
						break;
					}
				}
				if (continueCnt > 0) {
					continue;
				}
			}
			// 한 글자 알파벳인 경우
			alphabetCnt++;
		}
		System.out.println(alphabetCnt);
	}
}
