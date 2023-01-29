
/*
 * 빨리 끝나는 회의를 선택해나간다.
 * 회의를 종료 시각, 시작 시각순으로 정렬한다.
 * 정렬된 회의를 순회하며 회의실을 사용할 수 있는지 체크한다
 * 시작 시각과 종료 시각은 2^31 - 1보다 작거나 같은 자연수 또는 0이므로 int 자료형을 사용한다.
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[N];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			meetings[n] = new Meeting(startTime, endTime);
		}
		Arrays.sort(meetings);

//		for (Meeting m : meetings) {
//			System.out.println(m.toString());
//		}

		int nextTime = 0;
		int count = 0;
		for (Meeting m : meetings) {
			if (m.getStartTime() >= nextTime) {
				count++;
				nextTime = m.getEndTime();
			}
		}

		System.out.println(count);
	}

}

class Meeting implements Comparable<Meeting> {

	private int startTime;
	private int endTime;

	public Meeting(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	@Override
	public int compareTo(Meeting o) {
		if (this.getEndTime() < o.getEndTime()) {
			return -1;
		} else if (this.getEndTime() == o.getEndTime()) {
			if (this.getStartTime() < o.getStartTime()) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return startTime + " " + endTime;
	}

}
