import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 첫째 줄에는 스위치 개수
 * 둘째 줄에는 스위치 상채
 * 남학생은 1, 여학생은 2
 * 스위치는 한 줄에 20개씩 출력
 * 남학생은 자신이 받은 수의 배수인 스위치의 상태를 바꾸고
 * 여학생은 자신이 받은 수와 같은 번호가 붙은 스위치 중심으로 좌우가 대칭인 구간에 속한 스위치의 상태를 모두 바꾼다
 * 
 * 학생 수만큼 제귀를 반복한다
 * 매개변수는 현재 학생 수, 학생 배열, 스위치
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		int[] switches = new int[N + 1];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++)
			switches[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(in.readLine());
		Student[] students = new Student[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			students[i] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
//		System.out.println(Arrays.toString(students));
		changeSwitch(0, students, switches);
		int count = 0;
		for (int i = 1; i <= N; i++) {
			count++;
			System.out.print(switches[i]);
			if (count == 20) {
				count = 0;
				System.out.print("\n");
			} else {
				System.out.print(" ");
			}
		}
	}

	public static void changeSwitch(int count, Student[] students, int[] switches) {
//		System.out.println(Arrays.toString(switches));
		if (count >= students.length)
			return;
		Student s = students[count];
		int gender = s.getGender();
		int number = s.getNumber();
		if (gender == 1) {
			for (int i = 1; i < switches.length; i++) {
				if (i % number == 0)
					switches[i] = reverse(switches[i]);
			}
		} else if (gender == 2) {
			int limit = Math.min(number - 1, switches.length - 1 - number);
			int step = 0;
			for(int i=1; i<=limit;i++) {
				if(switches[number-i]==switches[number+i]) {
					step = i;
				} else {
					break;
				}
			}
//			System.out.println(step);
			switches[number] = reverse(switches[number]);
			for (int i = 1; i <= step; i++) {
				switches[number - i] = reverse(switches[number - i]);
				switches[number + i] = reverse(switches[number + i]);
			}
		}
		changeSwitch(count + 1, students, switches);
	}

	public static int reverse(int num) {
		return num == 0 ? 1 : 0;
	}

}

class Student {
	private int gender;
	private int number;

	public Student(int gender, int number) {
		this.gender = gender;
		this.number = number;
	}

	public int getGender() {
		return gender;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Student [gender=" + gender + ", number=" + number + "]";
	}

}
