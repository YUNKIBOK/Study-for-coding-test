import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * 맨 윗 줄은 한 번 출력한다
 * "재귀함수가 뭔가요?"와 "잘 들어보게. ~~~"을 출력하고 재귀한다
 * 재귀를 마치고 돌아와서 "라고 답변하였지."를 출력한다 
 * 입력이 하나이므로 형변환이 편한 Scanner를 사용한다
 * N이 50인 경우에 출력이 많으므로 BufferedWriter를 사용한다
 * 시간 복잡도는 굉장히 넉넉하므로 고려하지 않아도 된다
 */

public class Main {
	public static Scanner in = new Scanner(System.in);
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String args[]) throws IOException {

		// 입력
		int N = in.nextInt();

		// 출력
		out.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		out.flush();
		// 재귀 시작
		whatIsRecursiveFunctionint(N, 0);
		out.flush();
	}

	public static void whatIsRecursiveFunctionint(int N, int count) throws IOException {
		if (count >= N) {
			for (int i = 0; i < count; i++)
				out.append("____");
			out.append("\"재귀함수가 뭔가요?\"\n");
			for (int i = 0; i < count; i++)
				out.append("____");
			out.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			for (int i = 0; i < count; i++)
				out.append("____");
			out.append("라고 답변하였지.\n");
			return;
		}

		for (int i = 0; i < count; i++)
			out.append("____");
		out.append("\"재귀함수가 뭔가요?\"\n");
		for (int i = 0; i < count; i++)
			out.append("____");
		out.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		for (int i = 0; i < count; i++)
			out.append("____");
		out.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		for (int i = 0; i < count; i++)
			out.append("____");
		out.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");

		whatIsRecursiveFunctionint(N, count + 1);

		for (int i = 0; i < count; i++)
			out.append("____");
		out.append("라고 답변하였지.\n");
	}

}
