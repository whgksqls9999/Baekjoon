import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<Character, char[]> nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		nodes = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char parents = st.nextToken().charAt(0);
			char low1 = st.nextToken().charAt(0);
			char low2 = st.nextToken().charAt(0);

			nodes.put(parents, new char[] { low1, low2 });
		}
		preorder('A');
		System.out.println();
		inorder('A');
		System.out.println();
		postorder('A');

	}// main

	static void preorder(char now) {
		if (nodes.get(now)[0] == '.' && nodes.get(now)[1] == '.') {
			System.out.print(now);
			return;
		}
		System.out.print(now);
		if (nodes.get(now)[0] != '.') {
			preorder(nodes.get(now)[0]);
		}
		if (nodes.get(now)[1] != '.') {
			preorder(nodes.get(now)[1]);
		}
	}

	static void inorder(char now) {
		if (nodes.get(now)[0] == '.' && nodes.get(now)[1] == '.') {
			System.out.print(now);
			return;
		}
		if (nodes.get(now)[0] != '.') {
			inorder(nodes.get(now)[0]);
		}
		System.out.print(now);
		if (nodes.get(now)[1] != '.') {
			inorder(nodes.get(now)[1]);
		}
	}

	static void postorder(char now) {
		if (nodes.get(now)[0] == '.' && nodes.get(now)[1] == '.') {
			System.out.print(now);
			return;
		}
		if (nodes.get(now)[0] != '.') {
			postorder(nodes.get(now)[0]);
		}
		if (nodes.get(now)[1] != '.') {
			postorder(nodes.get(now)[1]);
		}
		System.out.print(now);
	}
}