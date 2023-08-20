import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int [N];
		
		for (int i = 0; i < N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = Integer.MIN_VALUE;
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j == i) continue;
				for (int k = 0; k < N; k++) {
					if (max == M) break outer;
					if (k == j || k == i) continue;
					int sum = arr[i] + arr[j] + arr[k] <= M? arr[i] + arr[j] + arr[k]: 0;
					max = Math.max(max, sum);
				}
			}
		}
		System.out.println(max);
		
	}
}
