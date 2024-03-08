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

		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> list = new ArrayList<>();

		int max = 0;
		for (int i : arr) {
			if (i > max) {
				list.add(i);
				max = i;
			} else if (i < max) {
				int lo = 0, hi = list.size() - 1;

				while (lo < hi) {
					int mid = (lo + hi) / 2;

					if (i <= list.get(mid)) {
						hi = mid;
					} else {
						lo = mid + 1;
					}
				}
				if (list.get(hi) == max) {
					max = i;
				}
				list.set(hi, i);
			}
		}
		System.out.print(list.size());
	}
}
