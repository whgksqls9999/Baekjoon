import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int lo = 0, hi = arr.length - 1;

		int[] answer = new int[2];
//		answer[0] = arr[0];
//		answer[1] = arr[arr.length - 1];
		int min = Integer.MAX_VALUE;
		
		while (lo < hi) {
			int cur = arr[lo] + arr[hi];

			if (Math.abs(cur) < min) {
				min = Math.abs(cur);
				answer[0] = arr[lo];
				answer[1] = arr[hi];
			}

			if (cur < 0) {
				lo++;
			} else if (cur >= 0) {
				hi--;
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}
}
