import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr, nums;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M]; // 수열을 담을 arr
		nums = new int[N]; // 숫자를 담을 nums
		used = new boolean[N]; // 사용 여부 체크할 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums); // 오름차순 정렬
		perm(0);
		System.out.println(sb);

	}// main

	static void perm(int depth) {
		// 1. 기저 조건
		if (depth == M) {
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		// 2. 재귀 조건
		int beforeNum = -1; // 전에 사용했던 수를 기록
		for (int i = 0; i < N; i++) {
			if (used[i]) // 사용했던 숫자면 넘긴다
				continue;
			if (!(beforeNum == nums[i])) { // 바로 전에 사용했던 숫자와 같지 않을때만 사용
				used[i] = true; // 사용 처리하기
				beforeNum = nums[i]; // 사용한 숫자의 중복 체크를 위해 저장
				arr[depth] = nums[i]; // 출력 대상 배열에 저장
				perm(depth + 1);
				used[i] = false;
			}
		}
	}
}