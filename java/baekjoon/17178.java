import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 대기 공간은 스택으로 구현한다
 * 티켓 번호 순서대로 사람들에게 입장 순서를 부여한다
 * 대기줄이 아닌 곳에서 입장 가능하면 입장
 * 대기줄에서 입장 가능하면 입장
 * 둘 다 아니면 대기줄로 이동
 * 대기줄이 아닌 곳에 사람이 없다는 것은 모두 대기줄에 있다는 것
 * 입장 해야할 사람이 대기줄에서 나올 수 없다면 BAD
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		PriorityQueue<Person> sortedPeople = new PriorityQueue<Person>();
		Queue<Person> people = new LinkedList<Person>();
		Stack<Person> waitingPeople = new Stack<Person>();

		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				Person person = new Person(st.nextToken());
				sortedPeople.add(person);
				people.add(person);
			}
		}

		boolean isBad = false;
		while (sortedPeople.size() > 0) {
			Person topPriority = sortedPeople.peek();
			if (people.size() != 0) {
				Person front = people.peek();
				if (front == topPriority) {
					// 큐에서 빼기
					// 우선순위 큐에서 빼기
					// 컨티뉴
					people.poll();
					sortedPeople.poll();
//					System.out.println(topPriority.toString());
					continue;
				} else {
					if (waitingPeople.size() != 0) {
						Person top = waitingPeople.peek();
						if (top == topPriority) {
							// 스택에서 빼기
							// 우선순위 큐에서 빼기
							// 컨티뉴
							waitingPeople.pop();
							sortedPeople.poll();
//							System.out.println(topPriority.toString());
							continue;
						}
					}
					// 큐에서 스택으로 옮기기
					// 컨티뉴
					people.poll();
					waitingPeople.push(front);
					continue;
				}
			} else {
				Person top = waitingPeople.peek();
				if (top == topPriority) {
					// 스택에서 빼기
					// 우선순위 큐에서 빼기
					// 컨티뉴
					waitingPeople.pop();
					sortedPeople.poll();
//					System.out.println(topPriority.toString());
					continue;
				} else {
					System.out.println("BAD");
					isBad = true;
					break;
				}
			}
		}
		if (!isBad)
			System.out.println("GOOD");
	}

}

class Person implements Comparable<Person> {
	private char seatChar;
	private int seatNum;

	public Person(String ticket) {
		String[] strAndNum = ticket.split("-");
		this.seatChar = strAndNum[0].charAt(0);
		this.seatNum = Integer.parseInt(strAndNum[1]);
	}

	public char getSeatChar() {
		return seatChar;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	@Override
	public int compareTo(Person o) {
		if (seatChar < o.getSeatChar()) {
			return -1;
		} else if (seatChar == o.getSeatChar()) {
			if (seatNum < o.getSeatNum()) {
				return -1;
			}
			return 1;
		}
		return 1;
	}

	@Override
	public String toString() {
		return "Person [seatChar=" + seatChar + ", seatNum=" + seatNum + "]";
	}

}