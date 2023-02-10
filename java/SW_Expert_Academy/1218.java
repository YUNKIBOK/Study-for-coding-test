import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 여는 괄호를 만나면 스택에 넣는다
 * 닫는 괄호를 만나면 스택에서 뺀다
 * 스택이 비었을 때 뺄 수 없음에 주의한다
 * 스택에서 뺐는데 괄호 쌍이 완성되지 않으면 유효하지 않다
 * 모든 괄호를 처리하고 스택이 비었으면 유효하다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String openMarks = "({[<";
		String closeMarks = ")}]>";

		for (int t = 1; t <= 10; t++) {
			sb.append("#").append(t).append(" ");
			int length = Integer.parseInt(in.readLine());
			boolean isOkay = true;
			String marks = in.readLine();
			Stack<Character> stack = new Stack<Character>();
			
			for (int i = 0; i < length; i++) {
				char current = marks.charAt(i);
				if (openMarks.contains(current + "")) {
					stack.push(current);
				} else {
					if (stack.isEmpty()) {
						isOkay = false;
						break;
					}
					char top = stack.peek();
					if (closeMarks.contains(top + "")) {
						isOkay = false;
						break;
					} else if (closeMarks.indexOf(current) != openMarks.indexOf(top)) {
						isOkay = false;
						break;
					}
					stack.pop();
				}
			}

			if (isOkay) {
				sb.append("1");
			} else {
				sb.append("0");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}