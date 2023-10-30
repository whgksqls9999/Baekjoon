import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Map<String, Integer> map = new TreeMap<>();

		while (N-- > 0) {
			String[] input = br.readLine().split("\\.");
			map.put(input[1], map.getOrDefault(input[1], 0) + 1);
		}

		StringBuilder sb = new StringBuilder();

		for (String s : map.keySet()) {
			sb.append(s).append(" ").append(map.get(s)).append("\n");
		}

		System.out.print(sb);
	}
}