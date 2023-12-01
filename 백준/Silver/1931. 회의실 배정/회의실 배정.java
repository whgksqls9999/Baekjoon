import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][2];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[i] = new int[] { a, b };
		}

		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int res = 0;

				if (o1[1] == o2[1]) {
					res = o1[0] - o2[0];
				} else {
					res = o1[1] - o2[1];
				}

				return res;
			}
		});

		int selected = 0;
		int answer = 0;

		for (int i = 0; i < N; ++i) {
			if (arr[i][0] >= selected) {
				++answer;
				selected = arr[i][1];
			}
		}
		System.out.println(answer);
	}
}