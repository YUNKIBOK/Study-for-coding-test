import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

/*
 * 순수 시뮬레이션을 한다
 * 사람이 대기해야 하는 구간은 큐로 만든다
 * 창구는 배열로 만들어 처리한다
 * 모든 사람들이 설문 조사를 마칠 때까지 반복하고 반복할 때마다 시간을 1만큼 증가시킨다
 * 설문 조사를 마친 사람들 목록을 순회하며 접수 및 정비 창구 번호가 일치하는 사람들의 번호를 합한다
 */

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int[] reception = new int[N + 1]; // 접수 창구별 소요 시간
			int[] repair = new int[M + 1]; // 정비 창구별 소요 시간

			// 접수 창구별 소요 시간 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				reception[i] = Integer.parseInt(st.nextToken());
			}

			// 정비 창구별 소요 시간 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				repair[i] = Integer.parseInt(st.nextToken());
			}

			PriorityQueue<Customer> customers = new PriorityQueue<>(); // 고객들
			PriorityQueue<Customer> arrived = new PriorityQueue<>(new Comparator<Customer>() { // 도착한 고객들

				@Override
				public int compare(Customer o1, Customer o2) {
					return Integer.compare(o1.num, o2.num);
				}
			});
			PriorityQueue<Customer> recepted = new PriorityQueue<>(new Comparator<Customer>() { // 접수를 마친 고객들

				@Override
				public int compare(Customer o1, Customer o2) {
					if (o1.receptAt < o2.receptAt) {
						return -1;
					} else if (o1.receptAt == o2.receptAt) {
						if (o1.receptFrom < o2.receptFrom) {
							return -1;
						} else {
							return 1;
						}
					}
					return 1;
				}
			});
			Queue<Customer> repaired = new ArrayDeque<>(); // 정비를 마친 고객들

			int[] recepting = new int[N + 1]; // 접수 창구 현황(0이면 빈 창구, 숫자는 접수 마무리까지 남은 시간)
			int[] repairing = new int[M + 1]; // 정비 창구 현황(0이면 빈 창구, 숫자는 접수 마무리까지 남은 시간)

			// 고객 저장
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= K; i++) {
				customers.add(new Customer(i, Integer.parseInt(st.nextToken())));
			}

			// 시간에 따른 시뮬레이션
			int time = 0;
			while (repaired.size() < K) { // 모든 고객이 정비를 마치면 종료
				// 도착한 고객 갱신
				while (customers.size() > 0 && customers.peek().arriveAt <= time) {
					Customer current = customers.poll();
					arrived.add(current);
				}

				// 접수 창구 갱신
				for (int i = 1; i <= N; i++) {
					if (recepting[i] == 0) {
						if (arrived.size() > 0) {
							Customer current = arrived.poll();
							current.receptFrom = i;
							current.receptAt = time + reception[i];
							recepted.add(current);
							recepting[i] = reception[i];
						}
					} else {
						recepting[i] -= 1;
						if (recepting[i] == 0) {
							if (arrived.size() > 0) {
								Customer current = arrived.poll();
								current.receptFrom = i;
								current.receptAt = time + reception[i];
								recepted.add(current);
								recepting[i] = reception[i];
							}
						}
					}
				}

				// 정비 창구 갱신
				for (int i = 1; i <= M; i++) {
					if (repairing[i] == 0) {
						if (recepted.size() > 0 && recepted.peek().receptAt <= time) {
							Customer current = recepted.poll();
							current.repairFrom = i;
							current.repairAt = time + repair[i];
							repaired.add(current);
							repairing[i] = repair[i];
						}
					} else {
						repairing[i] -= 1;
						if (repairing[i] == 0) {
							if (recepted.size() > 0 && recepted.peek().receptAt <= time) {
								Customer current = recepted.poll();
								current.repairFrom = i;
								current.repairAt = time + repair[i];
								repaired.add(current);
								repairing[i] = repair[i];
							}
						}
					}
				}

				time++;
			}

			// A 접수 창구, B 정비 창구를 이용한 고객 번호 합 구하기
			int sum = 0;
			while (repaired.size() > 0) {
				Customer current = repaired.poll();
				if (current.receptFrom == A && current.repairFrom == B) {
					sum += current.num;
				}
			}

			// 출력
			if (sum != 0)
				sb.append(sum);
			else
				sb.append(-1);
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}

class Customer implements Comparable<Customer> {

	int num;
	int arriveAt;
	int receptFrom;
	int receptAt;
	int repairFrom;
	int repairAt;

	public Customer(int num, int arriveAt) {
		this.num = num;
		this.arriveAt = arriveAt;
	}

	@Override
	public int compareTo(Customer o) {
		return Integer.compare(this.arriveAt, o.arriveAt);
	}

	@Override
	public String toString() {
		return "Customer [num=" + num + ", arriveAt=" + arriveAt + ", receptFrom=" + receptFrom + ", receptAt="
				+ receptAt + ", repairFrom=" + repairFrom + ", repairAt=" + repairAt + "]";
	}

}
