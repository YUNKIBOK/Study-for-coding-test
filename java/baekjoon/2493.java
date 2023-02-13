import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N이 최대 500,000이므로 O(NlogN)의 시간 복잡도로 해결해야 한다
 * 현재 탑 기준으로 선행 탑이 자신보다 높으면 선행 탑이 수신한다
 * 현재 탑 기준으로 선행 탑이 작으면 선행 탑의 신호를 수신하는 탑을 살펴본다
 * 선행 탑의 신호를 수신하는 탑이 현재 탑보다 크면 그 탑이 현재 탑의 신호를 수신한다
 * 선행 탑의 신호를 수신하는 탑마저 현재 탑보다 작으면 그 탑의 신호를 수신하는 탑을 또 살펴본다
 * 이러한 방법을 이용하면 불필요한 탑을 검사하는 과정을 줄일 수 있다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int[] towers = new int[N + 1];
		int[] receivers = new int[N + 1];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}

		sb.append(0).append(" ");
		for (int i = 2; i <= N; i++) {
			if (towers[i] <= towers[i - 1]) {
				receivers[i] = i - 1;
			} else {
				int prevReceiver = receivers[i - 1];
				while (prevReceiver > 0) {
					if (towers[prevReceiver] >= towers[i]) {
						break;
					} else {
						prevReceiver = receivers[prevReceiver];
					}
				}
				receivers[i] = prevReceiver;
			}
			sb.append(receivers[i]).append(" ");
		}

		System.out.println(sb.toString());
	}

}