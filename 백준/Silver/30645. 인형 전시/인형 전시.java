import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println(solution());
	}

	public static int solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int cnt = Math.min(C, N);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < C && i < N; i++) {
			list.add(arr[i]);
		}

		int idx = 0;
		int max = list.get(0);
		for (int i = C; i < N; i++) {
			if (arr[i] > max) {
				cnt++;
				list.add(arr[i]);
				max = list.get(++idx);
			}
		}

		if (cnt > R * C) {
			cnt = R * C;
		}
		return cnt;
	}
}