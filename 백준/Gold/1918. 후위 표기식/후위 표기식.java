import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		Map<Character, Integer> map = new HashMap<>();

		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('(', 0);
		map.put(')', 0);

		char[] charArr = st.nextToken().trim().toCharArray();
		for (char i : charArr) {
			if (!map.containsKey(i)) { // 연산자가 아닐 때
				sb.append(i);
			} else { // 연산자일 때
				if (i == '(') { // 여는 괄호일 때
					stack.push(i);
				} else if (i == ')') { // 닫는 괄호일 때
//					if (!stack.isEmpty() && stack.peek() != '(') { // 여는 괄호가 나올때 까지
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.pop(); // 여는 괄호는 제거한다.
//					}
				} else { // 다른 연산자들일 때
					if (stack.isEmpty() || map.get(stack.peek()) < map.get(i)) {
						stack.push(i);

					} else if (!stack.isEmpty() && map.get(stack.peek()) >= map.get(i)) {
						while (!stack.isEmpty() && map.get(stack.peek()) >= map.get(i)) {
							sb.append(stack.pop());
						}
						stack.push(i);
					}
				}
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);

	}// main
}