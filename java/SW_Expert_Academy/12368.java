import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int testCaseCount = sc.nextInt();
		for (int i = 1; i <= testCaseCount; i++) {
			byte A = sc.nextByte();
			byte B = sc.nextByte();
			byte result = (A + B) >= 24 ? (byte) ((A + B) % 24) : (byte) (A + B);
			System.out.println("#" + i + " " + result);
		}

	}
}
