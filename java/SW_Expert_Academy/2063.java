import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		short n = sc.nextShort();
		int[] numArray = new int[n];
		for (short i = 0; i < n; i++) {
			numArray[i] = sc.nextInt();
		}
		Arrays.sort(numArray);
		System.out.print(numArray[n / 2]);
	}
}
