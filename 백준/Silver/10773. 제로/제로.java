import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<Integer> stack = new Stack<>();
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if (a == 0) {
				stack.pop();
			} else {
				stack.push(a);
			}
		}
		int sum = 0;
		if (stack.isEmpty()) {
			System.out.println(0);
		} else {
			while (!(stack.isEmpty())) {
				sum += stack.pop();
			}
			System.out.println(sum);
		}
	}
}
