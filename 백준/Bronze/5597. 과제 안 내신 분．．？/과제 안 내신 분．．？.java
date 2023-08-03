import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] arr = new int[31];
		for (int i = 0; i < 28; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())]++;
		}
		for (int i = 1; i < 31; i++) {
			if (arr[i] == 0) {
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}
}
