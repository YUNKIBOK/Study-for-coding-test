import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * 원소 제거에 용이한 LinkedList에 사람들을 넣는다
 * 1명이 남을 때까지 3번째 사람을 제거한다
 * 사람들이 원으로 앉아있기 때문에 나머지 연산자를 활용하여 제거한다
 * 입력이 적으므로 형변환에 유리한 Scanner를 사용한다
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int K = in.nextInt();
		
		List<Integer> people = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			people.add((i));
		}
		
		int removedAt = 0;
		sb.append("<");
		while (people.size() > 0) { // 모두 제거될 때까지
			// K번째 사람을 제거한다
			removedAt = (removedAt + (K-1)) % people.size();
			int removed = people.remove(removedAt);
			sb.append(removed);
			if (people.size() > 0) {
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb.toString());
	}

}