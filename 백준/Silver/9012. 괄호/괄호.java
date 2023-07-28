import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		String PS = "()";

		for (int i = 0; i < N; i++) {
			Stack<String> stack = new Stack<>();
			st = new StringTokenizer(br.readLine());
			String[] a = st.nextToken().split("");
			for (String j : a) {
				if (!(stack.isEmpty())) {
					if ((stack.peek()+j).equals(PS)) {
						stack.push(j);
						stack.pop();
						stack.pop();
					} else {
						stack.push(j);
					}
				} else {
					stack.push(j);

				}
			}
			System.out.println(stack.isEmpty() ? "YES" : "NO");
		}

	}
}
