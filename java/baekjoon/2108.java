
/*
 * 산술 평균: 입력 받으면서 합을 구하고 N으로 나눈다.
 * 중앙 값: 정렬 후 가운데 인덱스를 활용한다.
 * 최빈 값: 수의 빈도를 세는 배열을 만들고 MAX를 찾는다.
 * 범위: 정렬 후 인덱스 0과 마지막 인덱스를 활용한다
 */

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int[] freqOfNums = new int[8001];
		int[] nums = new int[N];
		for (int n = 0; n < N; n++) {
			int num = Integer.parseInt(br.readLine());
			nums[n] = num;
			sum += num;
			if (num <= 0) {
				freqOfNums[-num]++;
			} else {
				freqOfNums[num + 4000]++;
			}
		}
		Arrays.sort(nums);

		int min = nums[0];
		int max = nums[nums.length - 1];
		int mid = nums[nums.length / 2];

		int maxFreqAt = 0;
		int maxFreq = 0;
		for (int i = 0; i < 8001; i++) {
			if (freqOfNums[i] > maxFreq) {
				maxFreq = freqOfNums[i];
			}
		}
		int count = 0;
		for (int i = 4000; i >= 0; i--) {
			if (freqOfNums[i] == maxFreq) {
				if (count == 2) {
					break;
				}
				count++;
				maxFreqAt = -i;
			}
		}
		for (int i = 4001; i < 8001; i++) {
			if (freqOfNums[i] == maxFreq) {
				if (count == 2) {
					break;
				}
				count++;
				maxFreqAt = i - 4000;
			}
		}

		System.out.println(Math.round(sum * 1.0 / N));
		System.out.println(mid);
		System.out.println(maxFreqAt);
		System.out.println(max - min);
	}

}
