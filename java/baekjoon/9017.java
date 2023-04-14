import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 시뮬레이션한다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			// 변수 초기화
			int N = Integer.parseInt(in.readLine());
			int[] teamMembers = new int[200 + 1];
			int[] teamScore = new int[200 + 1];
			int[] fifthScore = new int[200 + 1];

			// 주자 순서 저장
			st = new StringTokenizer(in.readLine(), " ");
			int[] orders = new int[N];
			for (int n = 0; n < N; n++) {
				int team = Integer.parseInt(st.nextToken());
				orders[n] = team;
			}

			// 팀 별 주자 수 카운트
			for (int n = 0; n < N; n++) {
				teamMembers[orders[n]]++;
			}

			// 제외할 팀 선정
			Set<Integer> badTeam = new HashSet<>();
			for (int n = 1; n <= 200; n++) {
				if (teamMembers[n] > 0 && teamMembers[n] < 6) {
					badTeam.add(n);
				}
				teamMembers[n] = 0;
			}
//			System.out.println(Arrays.toString(badTeam.toArray()));

			// 제외되지 않은 팀 점수 계산
			int score = 1;
			for (int n = 0; n < N; n++) {
				int team = orders[n];
				if (badTeam.contains(team)) {
					continue;
				}
				teamMembers[team]++;
				if (teamMembers[team] <= 4) {
					teamScore[team] += score;
				} else if (teamMembers[team] == 5) {
					fifthScore[team] = score;
				}
				score++;
			}

			// 우승 팀 선정
			int bestTeam = 0;
			int bestScore = Integer.MAX_VALUE;
			int bestFifthScore = Integer.MAX_VALUE;
			for (int n = 1; n <= 200; n++) {
				if (teamMembers[n] != 6) {
					continue;
				}
				if (bestScore > teamScore[n]) {
					bestTeam = n;
					bestScore = teamScore[n];
					bestFifthScore = fifthScore[n];
				} else if (bestScore == teamScore[n]) {
					if (bestFifthScore > fifthScore[n]) {
						bestTeam = n;
						bestScore = teamScore[n];
						bestFifthScore = fifthScore[n];
					}
				}
			}

			// 출력
			sb.append(bestTeam).append("\n");
		}

		System.out.println(sb.toString());
	}

}
