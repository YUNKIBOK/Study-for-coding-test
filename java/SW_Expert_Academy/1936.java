import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
		String result;
		if (A > B && A - B != 2) {
			result = "A";
		} else {
			result = "B";
		}
		System.out.print(result);
	}
}
