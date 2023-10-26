import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int max = 0;
		Map<String, Integer> map = new TreeMap<>();
		while (N-- > 0) {
			String input = br.readLine();
			map.put(input, map.getOrDefault(input, 0) + 1);
			max = Math.max(max, map.get(input));
		}

		for (String s : map.keySet()) {
			if (map.get(s) == max) {
				System.out.println(s);
				return;
			}
		}
	}
}