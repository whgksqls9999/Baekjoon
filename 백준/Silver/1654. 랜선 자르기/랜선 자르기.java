import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K, N;
	static long ans;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// K : 이미 가지고 있는 랜선의 개수
		K = Integer.parseInt(st.nextToken(" "));
		// N : 필요한 랜선의 개수
		N = Integer.parseInt(st.nextToken(" "));

		// arr : 가지고 있는 랜선의 길이를 저장할 배열
		arr = new int[K];
		for (int i = 0; i < arr.length; ++i) {

			// 배열에 랜선 길이 정보 저장
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 매개 변수 탐색
		// 1
		parametricSearch(0, Integer.MAX_VALUE);

		System.out.println(ans);
	}

	static void parametricSearch(long start, long end) {
		// length : 새로 자른 랜선의 길이
		long length = (start + end) / 2;
//		System.out.println(start + " : " + end + " : " + length);

		// 탐색이 모두 끝났을 때
		if (start > end) {

			// ans : 출력할 정답. 현재의 길이를 저장한다.
			ans = length;
			return;
		}

		// cnt : 새로 자른 랜선의 길이로 만들 수 있는 새 랜선의 개수
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {

			// 가지고 있는 랜선의 길이를 length로 나누어주며 개수를 센다.
			cnt += arr[i] / length;
		}

		// 새로 자른 랜선의 개수 < 필요한 랜선의 개수 --> 길이를 줄여 더 많은 랜선을 만들어야 한다.
		if (cnt < N) {

			// 길이를 짧게 하는 방향으로 탐색
			parametricSearch(start, length - 1);
		}
		// 새로 자른 랜선의 개수 >= 필요한 랜선의 개수 --> 길이를 늘려 더 적은 랜선을 만들어야 한다.
		// 개수가 같을 때는 최대한 그 길이를 늘려야 한다.
		else if (cnt >= N) {

			// 길이를 늘리는 방향으로 탐색
			parametricSearch(length + 1, end);
		}
	}
}
