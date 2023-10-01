import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static char[] arr, tree;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st;

			String input;

			while ((input = br.readLine()) != null) {

				String[] inputArr = input.split(" ");

				// 수열의 크기 N
				N = Integer.parseInt(inputArr[0]);

				// 라운드 수 K
				K = Integer.parseInt(inputArr[1]);

				// 초기 값 입력
				arr = new char[N];
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < N; i++) {
					int a = Integer.parseInt(st.nextToken(" "));
					if (a > 0) {
						arr[i] = '+';
					} else if (a == 0) {
						arr[i] = '0';
					} else if (a < 0) {
						arr[i] = '-';
					}
				}

				int h = (int) Math.ceil(Math.log(N) / Math.log(2));
				int size = 1 << (h + 1);

				tree = new char[size];

				init(1, 0, N - 1);

				for (int i = 0; i < K; i++) {
					st = new StringTokenizer(br.readLine());
					char type = st.nextToken(" ").charAt(0);

					int b = Integer.parseInt(st.nextToken(" "));

					if (type == 'C') {
						long c = Long.parseLong(st.nextToken(" "));
						char in = ' ';
						if (c > 0) {
							in = '+';
						} else if (c == 0) {
							in = '0';
						} else if (c < 0) {
							in = '-';
						}

						update(1, 0, N - 1, b - 1, in);

					} else if (type == 'P') {
						int c = Integer.parseInt(st.nextToken(" "));
						char res = query(1, 0, N - 1, b - 1, c - 1);

						sb.append(res);
					}
				}
				sb.append("\n");
			}
		} catch (Exception e) {

		} finally {
			System.out.println(sb);
		}
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);

		if (tree[node * 2] == '0' || tree[node * 2 + 1] == '0') {
			tree[node] = '0';
		} else if ((tree[node * 2] == '-' && tree[node * 2 + 1] == '+')
				|| tree[node * 2] == '+' && tree[node * 2 + 1] == '-') {
			tree[node] = '-';
		} else if (tree[node * 2] == tree[node * 2 + 1]) {
			tree[node] = '+';
		}
	}

	static void update(int node, int start, int end, int idx, char val) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			tree[node] = val;
			arr[idx] = val;
			return;
		}

		update(node * 2, start, (start + end) / 2, idx, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);

		if (tree[node * 2] == '0' || tree[node * 2 + 1] == '0') {
			tree[node] = '0';
		} else if ((tree[node * 2] == '-' && tree[node * 2 + 1] == '+')
				|| tree[node * 2] == '+' && tree[node * 2 + 1] == '-') {
			tree[node] = '-';
		} else if (tree[node * 2] == tree[node * 2 + 1]) {
			tree[node] = '+';
		}
	}

	static char query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return '+';
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		char a = query(node * 2, start, (start + end) / 2, left, right);
		char b = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

		if (a == '0' || b == '0') {
			return '0';
		} else if ((a == '+' && b == '-') || (a == '-' && b == '+')) {
			return '-';
		} else if (a == b) {
			return '+';
		}
		return '+';
	}
}