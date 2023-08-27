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
		// 기저조건
		if (idx == M) {
			// 출력할 것 sb에 넣기
			for (int i = 0; i < M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		// 일반
		for (int i = 1; i <= N; i++) { // 1부터 N까지의 숫자를 고르는 for
			if (used[i]) { // 이미 고른적 있는 숫자 : 취소
				continue;
			}
			used[i] = true; // 해당 숫자 사용처리
			arr[idx] = i; // idx번째 수 = i로 설정하기
			tracking(idx + 1); // idx+1 번째 자리 수를 구하러 간다
			used[i] = false; // 다음 for문을 위해 사용처리 한 것을 취소하기
		}
	}
}
