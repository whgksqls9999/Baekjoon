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
		long[] a = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);

		int start = 0;
		int end = a.length - 1;
		long sum;
		long diffVal = Integer.MAX_VALUE;

		long temp;
		String ab = "";

		while (start < end) {
			sum = a[start] + a[end];
			if (sum < 0)
				temp = sum * -1;
			else
				temp = sum;
			if (temp < diffVal) {
				diffVal = temp;
				ab = a[start] + " " + a[end];
			}

			if (sum > 0) {
				end--;
			} else if (sum < 0) {
				start++;
			} else {
				break;
			}
		}

		System.out.println(ab);
	}
}
