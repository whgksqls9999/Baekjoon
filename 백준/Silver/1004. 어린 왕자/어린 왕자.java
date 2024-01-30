import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int[] start = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			int[] end = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			int n = Integer.parseInt(br.readLine());

			int res = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int[] point = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()) };

				if (check(point, start) + check(point, end) == 1) {
					res++;
				}
			}

			answer.append(res).append("\n");
		}

		System.out.print(answer);
	}

	public static int check(int[] point, int[] arr) {
		int res = 0;

		int xDist = Math.abs(point[0] - arr[0]);
		int yDist = Math.abs(point[1] - arr[1]);

		if (Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2)) < point[2]) {
			res = 1;
		}

		return res;
	}
}