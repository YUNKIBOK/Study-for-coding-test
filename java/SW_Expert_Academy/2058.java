import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		String T;
		T=sc.nextLine();
        char[] nums = T.toCharArray();

        int sum = 0;
		for(char num: nums) {
            sum += (num - '0');
        }
        System.out.print(sum);
	}
}
