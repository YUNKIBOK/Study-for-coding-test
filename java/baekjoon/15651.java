import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 시간은 넉넉하다
 * 재귀를 활용한다
 */

public class Main {

	public static StringBuilder sb = new StringBuilder();
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		recursive(0, N, M);
		out.flush();
	}

	public static void recursive(int count, int N, int M) throws IOException {
		if(count == M) {
			sb.setLength(sb.length()-1);
			sb.append("\n");
			out.append(sb.toString());
//			out.flush();
			return;
		}
		
		for(int i=1; i<=N;i++) {
			sb.append(i).append(" ");
			recursive(count + 1, N, M);
			sb.setLength(sb.length() - 2);
		}
	}
	
}
