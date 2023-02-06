import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

/*
 * 1. 맨 아래 원판을 제외한 N - 1개의 원판을 출발 기둥도 최종 목적 기둥도 아닌 나머지 한 기둥으로 옮긴다
 * 2. 맨 아래 원판을 최종 목적 기둥으로 옮긴다
 * 3. 나머지 N-1개의 원판을 최종 목적 기둥으로 옮긴다
 * N이 20 초과인 경우 원판 이동 횟수만 필요하므로 3번을 수행하지 않고 1번의 결과에 곱하기 2를 한다
 */

public class Main {

	public static final int SUM_OF_TOWERS = 1 + 2 + 3;
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static BigInteger count = new BigInteger("0");
	public static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		N = Integer.parseInt(in.readLine());
		playHanoiTower(N, 1, 3);
		out.append(count.toString() + "\n");
		out.flush();

		if (N <= 20) {
			out.append(sb.toString());
			out.flush();
			sb.setLength(0);
		}
	}

	public static void playHanoiTower(int leftPlates, int start, int end) {
		if (leftPlates <= 1) {
			count = count.add(BigInteger.valueOf(1));
			if (N <= 20) {
				sb.append(start);
				sb.append(" ");
				sb.append(end);
				sb.append("\n");
			}
			return;
		}

		playHanoiTower(leftPlates - 1, start, (SUM_OF_TOWERS - start - end));
		count = count.add(BigInteger.ONE);
		if (N <= 20) {
			sb.append(start);
			sb.append(" ");
			sb.append(end);
			sb.append("\n");
			playHanoiTower(leftPlates - 1, (SUM_OF_TOWERS - start - end), end);
		} else {
			count = count.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE);
		}
	}

}
