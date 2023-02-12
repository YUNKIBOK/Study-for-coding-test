import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
 * comparator를 활용한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		String[] words = new String[N];
		
		for (int i = 0; i < N; i++) {
			words[i] = in.readLine();
		}
		
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() < o2.length()) {
					return -1;
				} else if (o1.length() == o2.length()) {
					if (o1.compareTo(o2) > 0) {
						return 1;
					} else {
						return -1;
					}
				}
				return 1;
			}

		});
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			if (i > 0 && words[i].equals(words[i - 1])) {
				continue;
			}
			sb.append(words[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}