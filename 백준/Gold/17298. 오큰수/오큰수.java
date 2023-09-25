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
		StringBuilder sb = new StringBuilder();

		Stack<Integer> stack = new Stack<>();

		int N = Integer.parseInt(st.nextToken(" "));

		int[] arr = new int[N];
		int[] seq = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken(" "));
		}

		for (int i = 0; i < N; i++) {

			// 스택이 비었다면 현재 인덱스 값을 넣어준다.
			if (stack.isEmpty()) {
				stack.push(i);
			}

			// 현재 인덱스의 값보다 스택의 top 인덱스 값이 더 크다면
			else if (arr[stack.peek()] >= arr[i]) {

				// 스택에 현재 인덱스를 넣는다
				stack.push(i);
			}

			// 현재 인덱스의 값보다 스택의 top 인덱스의 값이 더 작다면
			// 즉, 기존에 있던 값의 오른쪽에 있으며 더 큰 수중 가장 왼쪽이 되는 수가 현재라면
			else if (arr[stack.peek()] < arr[i]) {

				// 스택에서 현재 나보다 큰 값이 나올때 까지 빼면서
				while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {

					// 스택에서 뺀 인덱스에 해당하는 부분의 값을 현재 인덱스 값으로 바꾼다.
					seq[stack.pop()] = arr[i];
				}

				// 현재 인덱스 값을 스택에 넣어준다
				stack.push(i);
			}
		}

		// 마지막 남은 자리는 -1이 들어가야 한다
		while (!stack.isEmpty()) {
			seq[stack.pop()] = -1;
		}

		// 결과 출력
		for (int i = 0; i < arr.length; i++) {
			if (seq[i] == 0)
				seq[i] = -1;
			sb.append(seq[i]).append(" ");
		}
		System.out.println(sb.toString().trim());
	} // main

}