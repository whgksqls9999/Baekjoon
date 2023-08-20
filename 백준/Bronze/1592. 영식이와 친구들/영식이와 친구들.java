import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int N;
	static int M;
	static int L;
	static int cnt;
	static int target;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[N];
		while (throwss(target));
		System.out.println(cnt - 1);

	}// main

	static boolean throwss(int idx) {
		++cnt;
		if (arr[idx] % 2 == 1) {
			target = idx + L;
			if (target >= N) {
				target -= N;
			}
			++arr[target];
		} else {
			target = idx - L;
			if (target < 0) {
				target += N;
			}
			++arr[target];
		}
		return arr[target] < M;

	}
}
