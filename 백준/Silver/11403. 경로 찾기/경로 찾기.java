import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[][] answer, adjArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		adjArr = new int[N][N];
		answer = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
				answer[i][j] = adjArr[i][j];
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer[i][j] = answer[i][k] + answer[k][j] == 2 ? 1 : answer[i][j];
				}
			}
		}

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res.append(answer[i][j]).append(" ");
			}
			res.append("\n");
		}
		System.out.println(res);
	}

}