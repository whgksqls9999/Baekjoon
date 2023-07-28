import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		StringBuffer buffer = new StringBuffer();

		Stack<Integer> stack = new Stack<>();
		int num = 1;
		for (int i = 0; i < N; i++) {
			if (A[i] >= num) {
				while (A[i] >= num) {
					stack.push(num++);
					buffer.append("+\n");
				}
				stack.pop();
				buffer.append("-\n");
			} else {
				int pop = stack.pop();
				if (pop > A[i]) {
					buffer.replace(0, buffer.length(), "NO");
					break;
				}
				buffer.append("-\n");
			}
		}
		System.out.println(buffer);
	}
}
