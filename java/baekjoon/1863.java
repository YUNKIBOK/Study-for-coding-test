import java.util.*;
import java.io.*;

/*
 * 스택을 활용한다
 * 새로운 건물이 등장하면 스택에 넣고(스택이 비었거나 더 높은 것을 만나면)
 * 건물이 완성되면 스택에서 뺀다(스택 탑보다 작은 것을 만났을 때) -> 탑이 만난 것보다 작거나 같아질 때까지 팝하며 건물 수를 센다
 * 건물을 세고 나서 스택이 비거나 더 낮은 것이 남아있다면 새로운 건물이 시작된 것으로 간주한다
 * 높이가 0인 건물은 없으므로 0은 스택에 넣지 안도록 주의한다
 * 모든 것을 끝내고 스택에 남은 건물들까지 합한 값이 최종 결과이다
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 변수 초기화
		int n = Integer.parseInt(in.readLine());
		Stack<Integer> stack = new Stack();

		int buildingCnt = 0;
		for (int i = 0; i < n; i++) {
//			System.out.println(buildingCnt);
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (stack.isEmpty() || y > stack.peek()) { // 새 건물 시작
				if (y != 0) {
					stack.push(y);
				}
			} else if (stack.peek() > y) { // 건물 완성된 것이 있는 경우
				while (!stack.isEmpty() && stack.peek() > y) { // 완성 건물 세기
					buildingCnt++;
					stack.pop();
				}

				if (stack.isEmpty() || stack.peek() < y) { // 완성 후 새로 시작된 것인지 확인
					if (y != 0) {
						stack.push(y);
					}
				}
			}
		}
//		System.out.println(buildingCnt);
		buildingCnt += stack.size(); // 마지막으로 건물 완성 시키기

//		System.out.println(Arrays.toString(stack.toArray()));
		System.out.println(buildingCnt);
	}

}