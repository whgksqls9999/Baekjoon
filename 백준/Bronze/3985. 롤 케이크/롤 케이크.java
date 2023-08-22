import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];

		int N = Integer.parseInt(br.readLine());
		int[] arr2 = new int[N + 1];
		int maxG = 0;
		int maxWho = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (b - a > maxG) {
				maxG = b - a;
				maxWho = i;
			}
			for (int j = a; j <= b; j++) {
				if (arr[j] == 0) {
					arr[j] = i;
					arr2[i]++;
				}
			}
		}
		System.out.println(maxWho);

		int max = 0;
		for (int i = 1; i < arr2.length; i++) {
			if (arr2[i] != 0 && arr2[i] > max) {
				max = arr2[i];
			}
		}
		for (int i = 1; i < arr2.length; i++) {
			if (max == arr2[i]) {
				System.out.println(i);
				break;
			}
		}

	}// main

}
// class