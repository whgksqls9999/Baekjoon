import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, k;
	static long[] tree;
	static long sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 데이터 수
		M = Integer.parseInt(st.nextToken()); // 변경 횟수
		K = Integer.parseInt(st.nextToken()); // 구간합 구하는 횟수

		k = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (int) (Math.pow(2, k) * 2);

		tree = new long[size];

		for (int i = (int) Math.pow(2, k); i < (int) Math.pow(2, k) + N; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		setSum();

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			sum = 0;
			if (a == 1) { // b번째 수를 c로 바꿈
				int aIdx = b + (int) Math.pow(2, k) - 1;
				long diff = c - tree[aIdx];
				change(aIdx, diff);
			} else { // b번째 수부터 c번째 수의 합 출력
				int bIdx = b + (int) Math.pow(2, k) - 1;
				int cIdx = (int) c + (int) Math.pow(2, k) - 1;
				sum(bIdx, cIdx);
			}
		}

	} // main

	static void setSum() {
		for (int i = tree.length - 1; i > 1; i--) {
			tree[i / 2] += tree[i];
		}
	}

	static void change(int a, long b) {
		if (a == 0) {
			return;
		}
		tree[a] += b;
		change(a / 2, b);
	}

	static void sum(int a, int b) {
		if (a > b) {
			System.out.println(sum);
			return;
		}
		if (a % 2 == 1) {
			sum += tree[a];
		}
		if (b % 2 == 0) {
			sum += tree[b];
		}
		sum((a + 1) / 2, (b - 1) / 2);
	}
}
