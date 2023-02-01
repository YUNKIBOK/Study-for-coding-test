import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 벨트의 흐름에 따라 k개를 연속으로 먹는 경우를 모두 탐색한다
 * 중복을 허용하지 않는 Set을 활용하여 경우마다 먹을 수 있는 초밥의 종류를 구한다
 * d의 활용 방안을 잘 모르겠다
 * 먹을 수 있는 초밥의 가짓수의 최대값은 k + 1개이므로 만약 탐색 중 max가 k + 1이되면 탐색을 종료한다
 */

public class Main {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] sushis = new int[N];
		for(int i=0; i<N; i++) {
			sushis[i] = Integer.parseInt(in.readLine());
		}
		
		int max = Integer.MIN_VALUE;
		Set<Integer> sushiSequence = new HashSet<Integer>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<k; j++) {
				sushiSequence.add(sushis[(i+j)%N]);
			}
			sushiSequence.add(c);
			max = Math.max(max, sushiSequence.size());
			if(max == k +1)
				break;
			sushiSequence.clear();
		}
		System.out.println(max);
	}
}
