import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] a = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		int start = 0;
		int end = a.length - 1;
		int sum;
		int cnt = 0;
		Arrays.sort(a);

		while (start < end) {
			sum = a[start] + a[end];
			if (sum == M) {
				cnt++;
				start++;
				end--;
			} else if (sum > M) {
				end--;
			} else {
				start++;
			}
		}

		System.out.println(cnt);
	}
}
