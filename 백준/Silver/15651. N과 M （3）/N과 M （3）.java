import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static StringBuilder sb = new StringBuilder();
	static boolean[] used;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M + 1];
		used = new boolean[N + 1];

		tracking(0);

		System.out.println(sb);
	} // main

	static void tracking(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
//			if (used[i]) {
//				return;
//			}
			used[i] = true; // 사용처리
			arr[idx] = i; // idx번째 수를 i로 기록
			tracking(idx + 1); // 다음 자리 찾기
			used[i] = false; // 다음 수로 시작하는 수열 탐색을 위해 다시 초기화
		}
	}
}
