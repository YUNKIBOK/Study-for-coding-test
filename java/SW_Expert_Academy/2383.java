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

import javax.swing.plaf.synth.SynthSpinnerUI;

/*
 * 부분 조합으로 각 사람이 이용하는 계단을 나눈다
 * 그리고 계단을 이용해 내려가는 시뮬레이션을 한다
 * 각 시뮬레이션에서 최소 시간을 갱신한다
 */

public class Solution {

	static int N;
	static int P; // 전체 사람 수
	static int min; // 최소 시간
	static int stair1R, stair1C, stair1K; // 계단 1 정보
	static int stair2R, stair2C, stair2K; // 계단 2 정보
	static int stair1[]; // 계단 1을 이용 중인 사람들 현황
	static int stair2[]; // 계단 2를 이용 중인 사람들 현환
	static List<Person> peopleList;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			// 변수 초기화
			N = Integer.parseInt(in.readLine());
			min = Integer.MAX_VALUE;
			stair1K = 0;
			stair2K = 0;
			peopleList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int c = 0; c < N; c++) {
					int value = Integer.parseInt(st.nextToken());
					if (value == 1) {
						peopleList.add(new Person(r, c));
					} else if (value > 1) {
						if (stair1K == 0) {
							stair1R = r;
							stair1C = c;
							stair1K = value;
						} else if (stair2K == 0) {
							stair2R = r;
							stair2C = c;
							stair2K = value;
						}
					}
				}
			}
			P = peopleList.size();

//			System.out.println(stair1K + " "+stair2K);

//			Person[] people = new Person[peopleList.size()];
//			for (int i = 0; i < peopleList.size(); i++) {
//				people[i] = peopleList.get(i);
//			}

			// 부분 조합으로 계단 이용자를 나누고 시뮬레이션하여 최소 시간 갱신
			powerSet(0, new boolean[P]);
			sb.append(min).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void powerSet(int count, boolean[] selected) {
		if (count >= P) {// && selected[0] == true && selected[1] == true && selected[2] == true &&
							// selected[3] == true
			// && selected[4] == false && selected[5] == false) {
			PriorityQueue<Integer> waiting1 = new PriorityQueue<>();
			PriorityQueue<Integer> waiting2 = new PriorityQueue<>();

			// 계단 이용자들이 계단에 도착하는 시간순으로 저장
			for (int i = 0; i < P; i++) {
				if (selected[i] == true) {
					waiting1.add(Math.abs(stair1R - peopleList.get(i).r) + Math.abs(stair1C - peopleList.get(i).c));
				} else {
					waiting2.add(Math.abs(stair2R - peopleList.get(i).r) + Math.abs(stair2C - peopleList.get(i).c));
				}
			}

			// 계단 1 이용자가 모두 내려가는 시간 구하기
			stair1 = new int[3];
			Arrays.fill(stair1, -1);
			int time1 = waiting1.isEmpty() ? 0 : waiting1.peek();
			while (waiting1.size() > 0) {
				for (int i = 0; i < 3; i++) {
					if (stair1[i] == -1) {
						if (!waiting1.isEmpty() && waiting1.peek() + 1 <= time1) {
							waiting1.poll();
//							if (waiting1.isEmpty()) {
//								break;
//							}
//							System.out.println(time1 + "초에 계단1 내려가기 시작");
							stair1[i] = 0;
						}
					} else {
						stair1[i] += 1;
						if (stair1[i] >= stair1K) {
//							System.out.println(time1 + "초에 이동 완료");
							stair1[i] = -1;
							if (!waiting1.isEmpty() && waiting1.peek() + 1 <= time1) {
								waiting1.poll();
//								System.out.println(time1 + "초에 계단1 내려가기 시작");
//								if (waiting1.isEmpty()) {
//									break;
//								}
								stair1[i] = 0;
							}
						}
					}
				}
				time1++;
			}
			time1 += stair1K - 1;

			// 계단 2 이용자가 모두 내려가는 시간 구하기
			stair2 = new int[3];
			Arrays.fill(stair2, -1);
			int time2 = waiting2.isEmpty() ? 0 : waiting2.peek();
			while (waiting2.size() > 0) {
				for (int i = 0; i < 3; i++) {
					if (stair2[i] == -1) {
						if (!waiting2.isEmpty() && waiting2.peek() + 1 <= time2) {
							waiting2.poll();
							stair2[i] = 0;
//							System.out.println(time2 + "초에 계단2 내려가기 시작");
//							if (waiting2.isEmpty()) {
//								break;
//							}
						}
					} else {
						stair2[i]++;
						if (stair2[i] >= stair2K) {
							stair2[i] = -1;
//							System.out.println(time2 + "초에 이동 완료");
							if (!waiting2.isEmpty() && waiting2.peek() + 1 <= time2) {
								waiting2.poll();
//								System.out.println(time2 + "초에 계단2 내려가기 시작");
//								if (waiting2.isEmpty()) {
//									break;
//								}
								stair2[i] = 0;
							}
						}
					}
				}
				time2++;
			}
			time2 += stair2K - 1;

//			System.out.println(time1 + " " + time2);
			min = Math.min(min, Math.max(time1, time2));
//			System.out.println(min);
			return;
		}
//		} else if (count >= P) {
//			return;
//		}

		selected[count] = true;
		powerSet(count + 1, selected);

		selected[count] = false;
		powerSet(count + 1, selected);
	}

}

class Person {

	int r, c;

	public Person(int r, int c) {
		this.r = r;
		this.c = c;
	}

}
