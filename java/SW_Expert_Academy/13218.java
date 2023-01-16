import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount = Integer.parseInt(br.readLine());
		for (int i = 1; i < testCaseCount + 1; i++) {
			int result = Integer.parseInt(br.readLine()) / 3;
			System.out.println("#" + i + " " + result);
		}
	}
}
