import java.util.*;
import java.io.*;

/*
 * Set 컬렉션을 활용한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(in.readLine());
		HashSet<Integer> set = new HashSet<>();
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			String cmd = st.nextToken();
			if("add".equals(cmd)) {
				set.add(Integer.parseInt(st.nextToken()));
			} else if("remove".equals(cmd)) {
				set.remove(Integer.parseInt(st.nextToken()));
			}  else if("check".equals(cmd)) {
				if(set.contains(Integer.parseInt(st.nextToken()))) {
					sb.append(1);
				} else {
					sb.append(0);
				}
				sb.append("\n");
			}  else if("toggle".equals(cmd)) {
				int num = Integer.parseInt(st.nextToken());
				if(set.contains(num)) {
					set.remove(num);
				} else {
					set.add(num);
				}
			}  else if("all".equals(cmd)) {
				for(int j=1; j<=20; j++) {
					set.add(j);
				}
			}  else if("empty".equals(cmd)) {
				set.clear();
			} 
		}
		System.out.println(sb.toString());
	}

}