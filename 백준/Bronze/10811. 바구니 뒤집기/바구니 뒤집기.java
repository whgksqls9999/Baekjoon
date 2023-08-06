import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int start;
	static int end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// N과 M을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			if (start == end)
				continue;

			int[] tmp = new int[end - start + 1];
			int idx = 0;
			for (int j = end - 1; j >= start - 1; j--) {
				tmp[idx++] = arr[j];
			}
			idx = 0;
			for (int j = start - 1; j < end; j++) {
				arr[j] = tmp[idx++];
			}
		}
		for (int i : arr) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
