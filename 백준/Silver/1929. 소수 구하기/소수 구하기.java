import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[M+1];
		for (int i = 0; i <= M; i++) {
			arr[i] = i;
		}
		arr[1] = 0;
		for (int i = 2; arr[i] <= Math.sqrt(M); i++) {
			if(arr[i] == i) {
				for (int j = i+i; j <= M; j+=i) {
					arr[j] = 0;
				}
			}
		}
		for (int i : arr) {
			if (i != 0 && i >= N && i <= M) {
				sb.append(i+"\n");
			}
		}
		System.out.println(sb);
	}
}
