import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] numArr;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
//		StringTokenizer st;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numArr = new int[N];

		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numArr);

		perm(0, 0);

		System.out.println(sb);
	}

	static void perm(int idx, int depth) {
		if (depth == M) {
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		int pre = 0;
		for (int i = idx; i < N; i++) {
			if (pre != numArr[i]) {
				arr[depth] = numArr[i];
				perm(i + 1, depth + 1);
			}
			pre = arr[depth];
		}
	}
}