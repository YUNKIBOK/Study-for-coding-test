import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 해당 좌표보다 작은 서로 다른(중복 제거) 좌표의 개수를 구한다
 * N이 최대 1,000,000이므로 O(NlogN)까지 가능하다
 * 중복 제거는 Set을 활용한다
 * 정렬을 하고 정렬된 원소를 set에 넣으며 압축을 적용한 좌표 값을 저장해둔다
 * 원래 좌표들을 순회하며 압축이 적용된 좌표 값을 찾는다
 * BufferedReader와 StringBuilder를 활용한다
 */

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		Number[] numbers = new Number[N];
		int[] compressedNumbers = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			numbers[i] = new Number(Integer.parseInt(st.nextToken()), i);
		}

		Arrays.sort(numbers);

		int prev = Integer.MIN_VALUE;
		int count = 0;

		for (int i = 0; i < N; i++) {
			if (i > 0 && prev == numbers[i].value) {
				compressedNumbers[numbers[i].order] = compressedNumbers[numbers[i - 1].order];
				continue;
			}
			compressedNumbers[numbers[i].order] = count++;
			prev = numbers[i].value;
		}
		
		for(int i=0; i<N; i++) {
			sb.append(compressedNumbers[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}

class Number implements Comparable<Number> {
	
	int value;
	int order;

	public Number(int value, int order) {
		this.value = value;
		this.order = order;
	}

	@Override
	public int compareTo(Number o) {
		if (this.value > o.value)
			return 1;
		return -1;
	}
}