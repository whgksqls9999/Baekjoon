import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= arr.length; i++) {
			DFS(arr, 0, 0, i, S);
		}
		System.out.println(answer);
	}

	public static void DFS(int[] arr, int sum, int cur, int limit, int S) {
		if (cur == limit) {
			if (sum == S) {
				answer++;
			}
			return;
		}

		for (int i = cur; i < arr.length; i++) {
			DFS(arr, sum + arr[i], i + 1, limit, S);

		}
	}
}