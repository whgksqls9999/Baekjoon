import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, k;
	static long[] arr;
	static List<Long> minList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		k = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (int) Math.pow(2, k) * 2;
		arr = new long[size];

		// 초기화
		for (int i = 1; i <= N; i++) {
			arr[(int) Math.pow(2, k) + i - 1] = Long.parseLong(br.readLine());
		}
		for (int i = size - 1; i > 1; i -= 2) {
			arr[i / 2] = Math.min(arr[i], arr[i - 1]);
			if (arr[i / 2] == 0) {
				arr[i / 2] = Math.max(arr[i], arr[i - 1]);
			}
		}

		for (int i = 0; i < M; i++) {
			minList = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int aIdx = a + (int) Math.pow(2, k) - 1;
			int bIdx = b + (int) Math.pow(2, k) - 1;
			compare(aIdx, bIdx);
		}
	} // main

	static void compare(int a, int b) {
		if (a > b) {
			long min = 1000000001;
			for (int i = 0; i < minList.size(); i++) {
				if (minList.get(i) < min) {
					min = minList.get(i);
				}
			}
			System.out.println(min);
			return;
		}
		if (a % 2 == 1) {
			minList.add(arr[a]);
		}
		if (b % 2 == 0) {
			minList.add(arr[b]);
		}
		compare((a + 1) / 2, (b - 1) / 2);
	}

}
