import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int MAX = -1_000_000_000, MIN = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] operators = new int[4];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		cal(0, arr, operators, new ArrayList<>());

		System.out.print(MAX + "\n" + MIN);
	}

	public static void cal(int depth, int[] arr, int[] operators, List<Integer> choose) {
		if (depth == arr.length - 1) {

			int cur = arr[0];
			for (int i = 0; i < choose.size(); i++) {
				switch (choose.get(i)) {
				case 0:
					cur += arr[i + 1];
					break;
				case 1:
					cur -= arr[i + 1];
					break;
				case 2:
					cur *= arr[i + 1];
					break;
				case 3:
					cur /= arr[i + 1];
					break;
				}
			}
			MAX = Math.max(MAX, cur);
			MIN = Math.min(MIN, cur);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				choose.add(i);
				operators[i]--;
				cal(depth + 1, arr, operators, choose);
				operators[i]++;
				choose.remove(choose.size() - 1);
			}
		}
	}
}