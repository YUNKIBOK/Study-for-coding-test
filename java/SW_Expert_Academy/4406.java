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
            String str = br.readLine();
            str = str.replace("a", "");
            str = str.replace("e", "");
            str = str.replace("i", "");
            str = str.replace("o", "");
            str = str.replace("u", "");
            System.out.println("#" + t + " " + str);
        }
    }
}