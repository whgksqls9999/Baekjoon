import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int max = 0;
		int[] count = new int[10001];

		int input = 0;
		for (int i = 0; i < N; i++) {
			input = Integer.parseInt(br.readLine());
			max = input > max ? input : max;
			count[input]++;
		} // 배열채우기 // 최대값 찾기 // 빈도수 체크
		for (int i = 0; i < count.length; i++) {
			while (count[i] != 0) {
				bw.write(i + "\n");
				count[i]--;
			}
		}
		bw.flush();
	}
}