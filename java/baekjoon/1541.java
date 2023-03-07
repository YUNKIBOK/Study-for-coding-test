import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/*
 * 수가 양수로만 이루어져있으므로 괄호를 만들어서 뺄 수 있는 수를 모두 빼는 것이 식의 값을 최소로 만드는 방법이다
 * 첫 번째 마이너스 이후에 나오는 숫자들은 괄호를 적절히 사용하여 모두 뺄 수 있다
 * 첫 번째 마이너스를 만날때까지는 더하고 그 이후에는 모두 뺀다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 변수 초기화
		String str = in.readLine();
		int result = 0;
		boolean isMinus = false;

		// 수 추출 및 빼기 연산 등장 여부 판단
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != '+' && ch != '-') {
				sb.append(ch);
			} else {
				if (isMinus) {
					result -= Integer.parseInt(sb.toString());
				} else {
					result += Integer.parseInt(sb.toString());
				}
				if (ch == '-') {
					isMinus = true;
				}
				sb.setLength(0);
			}
		}

		// 마지막 수 연산하기
		if (isMinus) {
			result -= Integer.parseInt(sb.toString());
		} else {
			result += Integer.parseInt(sb.toString());
		}

		sb.setLength(0);
		System.out.println(result);
	}

}