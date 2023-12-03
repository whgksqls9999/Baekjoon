import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] foods = new int[3];
		int[] drinks = new int[2];

		for (int i = 0; i < 3; ++i) {
			foods[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < 2; ++i) {
			drinks[i] = Integer.parseInt(br.readLine());
		}

		int answer = 3950;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 2; ++j) {
				int sum = foods[i] + drinks[j] - 50;
				if (sum < answer) {
					answer = sum;
				}
			}
		}

		System.out.println(answer);
	}
}