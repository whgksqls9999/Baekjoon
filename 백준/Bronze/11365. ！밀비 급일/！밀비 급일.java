import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		String input = "";
		StringBuilder ans = new StringBuilder();

		while (!("END".equals(input = br.readLine()))) {
			sb = new StringBuilder();
			sb.append(input);
			sb.reverse();
			ans.append(sb).append("\n");
		}

		System.out.println(ans);
	}
}