import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * D개에서 0, 1, 2, ... 개를 뽑는 조합을 구하고
 * 각 조합에서 부분조합을 사용해 약품 A를 투입할 것인지 약품 B를 투입할 것인지 정한다
 * 만약 열을 순회하면서 성능 검사를 통과한다면 테스트 케이스를 종료한다 
 */

public class Solution {

	static int D, W, K;
	static int[][] layers; // 최초 보호 필름
	static int[][] tempLayers; // 시뮬레이션용 임시 보호 필름
	static boolean isPass; // 성능검사 통과 여부
	static Queue<int[]> queue; // 용액을 투입할 막 조합 정보
	static List<boolean[]> powerSet; // 투입할 용액에 대한 부분 조합

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");

			// 변수 초기화
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			layers = new int[D][W];
			isPass = true;
			queue = new ArrayDeque();
			tempLayers = new int[D][W];

			// 초기 보호 필름 정보 저장
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					layers[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 초기 성능검사
			for (int c = 0; c < W; c++) {
				int prev = layers[0][c];
				int continued = 1;
				boolean colPass = false;
				for (int r = 1; r < D; r++) {
					if (prev == layers[r][c]) {
						continued++;
					} else {
						prev = layers[r][c];
						continued = 1;
					}
					if (continued >= K) { // 열에 대한 성능검사 통과 시
						colPass = true;
						isPass = true;
						break;
					}
				}
				if (colPass == false) { // 하나의 열이라도 성능검사에 통과하지 못하면 성능검사 불통
					isPass = false;
					break;
				}
			}

			// 초기 성능검사에서 통과한 경우
			if (isPass) {
				sb.append(0).append("\n");
				continue;
			}

			// 용액을 주입하며 성능검사를 실시
			int injectionCnt = 1;
			loop: while (true) {
				powerSet = new ArrayList<>();
				combination(0, injectionCnt, 0, new int[injectionCnt]);
				powerSet(0, injectionCnt, new boolean[injectionCnt]);

				while (queue.size() > 0) {
					int[] changedLayers = queue.poll(); // 용액을 투입할 막 번호
					for (int i = 0; i < powerSet.size(); i++) {
						// 임시 보호 필름 생성
						for (int k = 0; k < D; k++) {
							System.arraycopy(layers[k], 0, tempLayers[k], 0, W);
						}

						// 용액 투입
						boolean[] pSet = powerSet.get(i);
						for (int j = 0; j < injectionCnt; j++) {
							if (pSet[j] == false) {
								Arrays.fill(tempLayers[changedLayers[j]], 0);
							} else {
								Arrays.fill(tempLayers[changedLayers[j]], 1);

							}
						}

//						if(injectionCnt == 1) {
//							for(int p=0; p<D; p++) {
//								System.out.println(Arrays.toString(tempLayers[p]));
//							}
//							
//						}
//						System.out.println();

						// 용액 투입 후 성능검사
						for (int c = 0; c < W; c++) {
							int prev = tempLayers[0][c];
							int continued = 1;
							boolean colPass = false;
							for (int r = 1; r < D; r++) {
								if (prev == tempLayers[r][c]) {
									continued++;
								} else {
									prev = tempLayers[r][c];
									continued = 1;
								}
								if (continued >= K) {
//									if(injectionCnt == 2) {
//										System.out.println(r);
//									}
									colPass = true;
									isPass = true;
									break;
								}
							}
							if (colPass == false) { // 열 통과 시
								isPass = false;
								break;
							}
						}
//						if(injectionCnt==2)
//							System.out.println();
						if (isPass == true) { // 성능검사 통과 시
							break loop;
						}

					}
				}
				injectionCnt++; // 성능검사 불통 시 용액 투입 횟수 증가
			}

			sb.append(injectionCnt).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void combination(int count, int target, int start, int[] order) {
		if (count >= target) {
//			if(target==1)
//				System.out.println(Arrays.toString(order));
			queue.add(Arrays.copyOf(order, target));
			return;
		}

		for (int i = start; i < D; i++) {
			order[count] = i;
			combination(count + 1, target, i + 1, order);
		}
	}

	static void powerSet(int count, int target, boolean[] selection) {
		if (count >= target) {
//			if(target==1)
//				System.out.println(Arrays.toString(selection));
			powerSet.add(Arrays.copyOf(selection, target));
			return;
		}

		selection[count] = true;
		powerSet(count + 1, target, selection);

		selection[count] = false;
		powerSet(count + 1, target, selection);
	}

}