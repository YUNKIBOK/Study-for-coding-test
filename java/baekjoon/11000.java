import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 한 강의실에서 최대한 많은 강의를 해야 한다
 * 수업을 종료 시각 오름차순으로 정렬하고 강의실에 최대한 많이 배정한다
 * 모든 수업을 가능하게 되었을 때의 강의실 수를 구한다
 * --------------------------------------------
 * 시간 복잡도를 줄이기 위해 우선순위큐를 사용한다
 * 우선순위에는 각 강의실별 마지막 강의의 종료 시각이 들어간다
 * 한 강의실에 강의들을 차곡차곡 진행하기 위해 강의는 시작시각, 종료시각 순으로 정렬한다
 * 강의를 어느 강의실에서 진행할지는 우선순위 큐의 첫 요소를 통해 판별한다
 * 첫 요소보다 시작 시각이 빠르다면 다른 강의실들에서도 강의를 시작할 수 없고 새로운 강의실이 필요하다는 의미이다
 * 첫 요소보다 시작 시각이 느리거나 같다면 해당 강의실에서 수업하는 것이 차곡차곡 쌓는 최선의 방법이다
 */

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(in.readLine());

		List<Lesson> lessons = new ArrayList<Lesson>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			lessons.add(new Lesson(startTime, endTime));
		}
		Collections.sort(lessons);

//		for (int i = 0; i < N; i++) {
//			out.append(lessons.get(i).toString() + "\n");
//			out.flush();
//		}

		PriorityQueue<Integer> lastEndTimeOfClasses = new PriorityQueue<Integer>();
		lastEndTimeOfClasses.add(0);
		for (Lesson lesson : lessons) {
			int fastestEndTime = lastEndTimeOfClasses.peek();
			if (fastestEndTime <= lesson.getStartTime()) {
				lastEndTimeOfClasses.poll();
			}
			lastEndTimeOfClasses.add(lesson.getEndTime());
//			out.append(lastEndTimeOfClasses.toString()+"\n");
//			out.flush();
		}
		out.append(lastEndTimeOfClasses.size() + "");
		out.flush();
	}

}

class Lesson implements Comparable<Lesson> {
	private int startTime;
	private int endTime;

	public Lesson(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public int getStartTime() {
		return startTime;
	}

	@Override
	public int compareTo(Lesson o) {
		if (startTime < o.getStartTime()) {
			return -1;
		} else if (startTime == o.getStartTime()) {
			if (endTime < o.getEndTime())
				return -1;
			return 1;
		}
		return 1;
	}

	@Override
	public String toString() {
		return "Lesson [startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
