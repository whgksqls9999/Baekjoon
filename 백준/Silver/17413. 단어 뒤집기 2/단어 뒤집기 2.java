import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		String input = br.readLine();
		boolean isBracket = false;
		for (int i = 0; i < input.length(); i++) {
			char a = input.charAt(i);
			if (a == ' ') {
				if (isBracket) {
					sb.append(a);
				} else {
					while (!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					sb.append(a);
				}
			} else if (a == '<') {
				isBracket = true;
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(a);
			} // 여는 괄호 처리
			else if (a == '>') {
				isBracket = false;
				sb.append(a);
			} else if (isBracket) {
				sb.append(a);
			} else {
				stack.add(a);
			}
			// < => stack에 있는거 다 꺼내서 sb에 저장 후 <를 sb에 넣기
			// >가 나올때까지 그대로 sb에 저장 (boolean으로 관리)
			// 공백 나오면, 스택에 넣었던 거 다 꺼내서 sb에 저장 (boolean <는 제외)
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	} // main
}