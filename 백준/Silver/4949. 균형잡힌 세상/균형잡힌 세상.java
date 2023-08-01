import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());

		while (true) {
			String line = br.readLine();
			if (line.equals(".")) {
				break;
			}
			Stack<String> stack = new Stack<>();
			for (String a : line.split("")) {
				if (a.equals("(") || a.equals(")") || a.equals("[") || a.equals("]")) {
					if (stack.isEmpty()) {
						stack.push(a);
					} else {
						if ((stack.peek() + a).equals("()") || (stack.peek() + a).equals("[]")) {
							stack.push(a);
							stack.pop();
							stack.pop();
						} else {
							stack.push(a);
						}
					}
				}
			}
			if (stack.isEmpty()) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
}
