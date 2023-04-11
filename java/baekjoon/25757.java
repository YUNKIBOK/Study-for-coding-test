import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 게임을 희망하는 유저 수를 구한다
 * 임스는 반드시 플레이 유저에 포함되어야 하므로
 * 1, 2, 3 명의 추가 플레이어가 필요하다
 * 인원이 부족하면 게임할 수 없으니
 * 플레이어 수를 각 수로 나눈 몫만큼만 게임할 수 있다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());

		char type = st.nextToken().charAt(0);
		Set<String> users = new HashSet<>();
		for (int i = 0; i < N; i++) {
			users.add(in.readLine());
		}

		switch (type) {
		case 'Y':
			System.out.println(users.size() / 1);
			break;
		case 'F':
			System.out.println(users.size() / 2);
			break;
		case 'O':
			System.out.println(users.size() / 3);
			break;
		}
	}

}
