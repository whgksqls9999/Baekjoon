import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		Set<String> set = new LinkedHashSet<>();

		while (L-- > 0) {
			String input = br.readLine();
			if (set.contains(input)) {
				set.remove(input);
			}
			set.add(input);
		}

		for (String i : set) {
			sb.append(i).append("\n");
			--K;
			if (K == 0)
				break;
		}
		System.out.print(sb);
	}
}