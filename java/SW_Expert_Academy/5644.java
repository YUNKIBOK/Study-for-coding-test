import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 초마다 A와 B를 움직인다
 * 움직인 후 A와 B에게 가장 가까우면서 가장 성능이 좋은 BC를 찾는다
 * 최대한 BC를 공유하지 않는 것이 전체 충전한 양의 합에 대한 최댓값을 구하는데 유리하다
 * 만약 두 사람이 찾은 BC가 같다면 한 사람씩 다음으로 성능이 좋은 BC를 찾아본다
 * 만약 둘 다 동일한 BC밖에 접근을 못하면 공유하고 그렇지 않으면 절대 공유하지 않는다
 * 지도는 불필요하다(A와 B가 지도 밖으로 나갈 일도 없다)
 */

public class Solution {

	// 충전량 합
	static int sum;
	static int M;
	static int A;
	// 사람1의 현재 위치
	static int person1X;
	static int person1Y;
	// 사람2의 현재 위치
	static int person2X;
	static int person2Y;
	// 각 사람의 움직임
	static int[] person1;
	static int[] person2;
	// 움직임 처리를 위한 배열
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	// BC 모음
	static BatteryCharger[] chargers;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			person1 = new int[M];
			person2 = new int[M];
			chargers = new BatteryCharger[A];

			// 사람1 움직임 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < M; i++) {
				person1[i] = Integer.parseInt(st.nextToken());
			}
			// 사람2 움직임 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < M; i++) {
				person2[i] = Integer.parseInt(st.nextToken());
			}
			// BC 정보 저장
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				chargers[i] = new BatteryCharger(x, y, c, p);
			}
			// 충전 합의 최댓값을 구해야 하므로 BC는 성능 내림차순으로 정렬한다
			Arrays.sort(chargers);

			// 각 사람의 시작 위치를 설정한다
			person1X = 1;
			person1Y = 1;
			person2X = 10;
			person2Y = 10;

			// 0초부터 M초까지 반복한다
			for (int s = 0; s <= M; s++) {
				// 각 사람에게 가장 가깝고 성능 좋은 BC를 찾는다
				int person1BC = findBC(0, person1X, person1Y);
				int person2BC = findBC(0, person2X, person2Y);

				// 만약 두 사람 다 BC를 찾지 못했다면 다음 초로 넘어간다
				if (person1BC == -1 && person2BC == -1) {
					// 단, 마지막 초이면 반복을 끝낸다
					if (s == M) {
						break;
					}
					person1X += dx[person1[s]];
					person1Y += dy[person1[s]];
					person2X += dx[person2[s]];
					person2Y += dy[person2[s]];
					continue;
				}

				// 만약 두 사람이 찾은 BC가 다르면 각자 충전한다
				if (person1BC != person2BC) {
					if (person1BC != -1) {
						sum += chargers[person1BC].p;
					}
					if (person2BC != -1) {
						sum += chargers[person2BC].p;
					}
				} else { // 두 사람이 찾은 BC가 같다면 공유하지 않을 수 있는지 확인한다
					// 각자 다음으로 가깝고 성능 좋은 BC를 찾는다
					int nextPerson1BC = findBC(person1BC + 1, person1X, person1Y);
					int nextPerson2BC = findBC(person2BC + 1, person2X, person2Y);

					// 둘다 차선책이 없는 경우에만 공유해야 한다
					if (nextPerson1BC == -1 && nextPerson2BC == -1) {
						sum += chargers[person1BC].p;
					} else if (nextPerson1BC == -1) {
						// 사람2가 차선책이 있으면 사람2가 차선책으로 충전한다
						sum += chargers[person1BC].p;
						sum += chargers[nextPerson2BC].p;
					} else if (nextPerson2BC == -1) {
						// 사람1이 차선책이 있으면 사람1이 차선책으로 충전한다
						sum += chargers[person2BC].p;
						sum += chargers[nextPerson1BC].p;
					} else {
						// 둘다 차선책이 있으면 두 차선책 중 성능이 더 좋은 것 하나만 활용한다
						sum += chargers[person1BC].p;
						sum += Math.max(chargers[nextPerson1BC].p, chargers[nextPerson2BC].p);
					}
				}
				// 마지막 초이면 반복을 멈춘다
				if (s == M) {
					break;
				}
				// 다음 초를 위해 사람들을 움직인다
				person1X += dx[person1[s]];
				person1Y += dy[person1[s]];
				person2X += dx[person2[s]];
				person2Y += dy[person2[s]];
			}

			sb.append("#").append(t).append(" ").append(sum).append("\n");
			sum = 0;
		}
		System.out.println(sb.toString());
	}

	static int findBC(int start, int currentX, int currentY) {
		// 성능 내림차순으로 정렬된 BC에서 충전가능한 것을 찾는다
		for (int i = start; i < A; i++) {
			int BCX = chargers[i].x;
			int BCY = chargers[i].y;
			int BCC = chargers[i].c;

			int distance = Math.abs(BCX - currentX) + Math.abs(BCY - currentY);
			if (distance <= BCC) {
				return i;
			}
		}
		// 충전 가능한 BC를 찾지 못한 경우 -1을 반환한다
		return -1;
	}

}

class BatteryCharger implements Comparable<BatteryCharger> {

	int x;
	int y;
	int c;
	int p;

	public BatteryCharger(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}

	@Override
	public int compareTo(BatteryCharger o) {
		if (this.p > o.p)
			return -1;
		return 1;
	}

}