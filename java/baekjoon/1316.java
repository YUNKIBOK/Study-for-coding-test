/*
 * 1. 단어를 입력 받고 즉시 그룹 단어인지 체크한다. 
 * 2. 단어의 문자를 순회할 때 이전에 등장했던 문자가 다시 등장하면 그룹 단어가 아니다. 
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int groupWordCount = N;

		for (int n = 0; n < N; n++) {
			String word = br.readLine();
			String beforeLetter = word.substring(0, 1);
			String memory = beforeLetter;
			boolean isGroupWord = true;

			for (int i = 1; i < word.length(); i++) {
				String currentLetter = word.substring(i, i + 1);
				if (beforeLetter.equals(currentLetter)) {
					continue;
				} else {
					if (memory.contains(currentLetter)) {
						isGroupWord = false;
						break;
					} else {
						beforeLetter = currentLetter;
						memory += currentLetter;
					}
				}
			}

			if (!isGroupWord)
				groupWordCount--;
		}
		System.out.println(groupWordCount);
	}
}
