import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 커서 뒤쪽 문자를 저장할 queue
		Stack<Character> post = new Stack<>();

		// 커서 앞쪽 문자를 저장할 stack
		Stack<Character> pro = new Stack<>();

		String input = st.nextToken();
		for (int i = 0; i < input.length(); i++) {
			pro.add(input.charAt(i));
		}

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			switch (type) {
			case 'P':
				char insert = st.nextToken().charAt(0);
				pro.add(insert);
				break;
			case 'L':
				if (pro.isEmpty())
					continue;
				post.add(pro.pop());
				break;
			case 'D':
				if (post.isEmpty())
					continue;
				pro.add(post.pop());
				break;
			case 'B':
				if (pro.isEmpty())
					continue;
				pro.pop();
				break;
			}
		}
		while (!pro.isEmpty()) {
			post.add(pro.pop());
		}

		while (!post.isEmpty()) {
			sb.append(post.pop());
		}
		System.out.println(sb);

	} // main
}