import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * x, y를 멤버 변수로  가지는 position 클래스를 생성한다
 * 정렬을 하기 위해서 Comparable 인터페이스를 구현한다
 * 제한 시간이 1초이고 N은 최대 100,000이므로 O(NlogN) 시간 복잡도의 정렬을 사용한다
 * 좌표의 범위에 따라 int 타입을 사용한다
 */

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(in.readLine());
		List<Position> positions = new ArrayList<Position>();
		
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			positions.add(new Position(x, y));
		}
		
		Collections.sort(positions);
		
		for (Position p : positions) {
			out.append(p.getX() + " " + p.getY() + " \n");
			out.flush();
		}
	}

}

class Position implements Comparable<Position> {

	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int compareTo(Position o) {
		if (x < o.getX()) {
			return -1;
		} else if (x == o.getX()) {
			if (y < o.getY())
				return -1;
			else
				return 1;
		}
		return 1;
	}

}
