import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public static void main(String args[]) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			int count = 0;
			char beforeBit = '0';
			char[] bits = br.readLine().toCharArray();
			for (char bit : bits) {
				if (bit != beforeBit) {
					count++;
					beforeBit = bit;
				}
			}
			System.out.println("#" + t + " " + count);
		}
	}
}
