import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[] arr = new int[] { 1, 1, 2, 2, 2, 8 };
		int[] input = new int[6];
		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			sb.append(arr[i] - input[i]).append(" ");
		}
		System.out.println(sb);
	}
}
