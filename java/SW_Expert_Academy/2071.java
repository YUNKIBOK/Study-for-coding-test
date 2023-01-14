import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int testCaseCount = sc.nextInt();
		
		for(int i = 0; i<testCaseCount; i++) {
			int sum = 0;
			for(int j=0; j<10; j++) {
				sum += sc.nextInt();
			}
			System.out.println("#"+(i+1)+" "+Math.round(sum/10.0));
		}
	}
}
