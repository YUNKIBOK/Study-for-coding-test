import java.util.Scanner;

/*
 * 문제에서 나온대로 시뮬레이션한다
 * 배열을 4등분하고 Z모양 탐색하여 적절한 값을 채운다
 * 이러한 과정에서 재귀를 사용하는데
 * 재귀 종료 조건은 2 * 2 배열인 경우로 한다 
 * 2 ^ 15 = 약 30,000이므로 배열은 int 타입으로 선언한다
 * 입력이 3개이므로 형변환에 유리한 Scanner를 사용한다
 * 출력은 하나이므로 표준 출력을 사용한다
 * -----------------------------------------------------
 * int 타입 32 비트가 (2 ^ 15) * (2 ^ 15)개 있으면 27,000 MB이므로 메모리 초과가 발생한다
 * r행 c열 값만 구하면 되므로 배열을 선언하지 않고 수학적으로 접근한다
 * -----------------------------------------------------
 * (2 ^ 15) * (2 ^ 15)는 약 9억이므로 4등분 분할정복을 모두 수행하는 것은 시간 초과가 발생한다
 * r행 c열 값만 구하면 되므로 불필요한 부분은 생략한다
 */

public class Main {

	static int count;
	static int result;
	static int r;
	static int c;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		r = in.nextInt();
		c = in.nextInt();
		int initialSize = (int) Math.pow(2, N);

		// 4등분 분할정복
		z(0, 0, initialSize);
	}

	static void z(int startR, int startC, int size) {
		if (size == 2) {
			// 2 * 2 배열에 r행 c열이 포함되면 시작 점에서부터의 차이 만큼 더한 값을 출력하고 프로그램을 종료한다
			if (startR <= r && r < startR + 2 && startC <= c && c < startC + 2) {
				int rowDiff = r - startR;
				int colDiff = c - startC;
				result = count + (2 * rowDiff + colDiff);
				System.out.println(result);
				System.exit(0);
			}
			// 2*2 배열에 r행 c열이 포함되지 않으면 4개를 탐색한 것으로 치고 넘어간다
			count += 4;
			return;
		}

		int halfSize = size / 2;

		// 0번 구역에 찾고자 하는 행과 열이 있을 때만 직접 탐색한다
		if (startR <= r && r < startR + halfSize && startC <= c && c < startC + halfSize) {
			z(startR, startC, halfSize);
		}
		// 0번 구역에 찾고자 하는 행과 열이 없다면 탐색한 것으로 치고 넘어간다
		count += halfSize * halfSize;

		// 1번 구역에 찾고자 하는 행과 열이 있을 때만 직접 탐색한다
		if (startR <= r && r < startR + halfSize && startC + halfSize <= c && c < startC + halfSize + halfSize) {
			z(startR, startC + halfSize, halfSize);
		}
		// 1번 구역에 찾고자 하는 행과 열이 없다면 탐색한 것으로 치고 넘어간다
		count += halfSize * halfSize;

		// 2번 구역에 찾고자 하는 행과 열이 있을 때만 직접 탐색한다
		if (startR + halfSize <= r && r < startR + halfSize + halfSize && startC <= c && c < startC + halfSize) {
			z(startR + halfSize, startC, halfSize);
		}
		// 2번 구역에 찾고자 하는 행과 열이 없다면 탐색한 것으로 치고 넘어간다
		count += halfSize * halfSize;

		// 3번 구역에 찾고자 하는 행과 열이 있을 때만 직접 탐색한다
		if (startR + halfSize <= r && r < startR + halfSize + halfSize && startC + halfSize <= c
				&& c < startC + halfSize + halfSize) {
			z(startR + halfSize, startC + halfSize, halfSize);
		}
	}

}