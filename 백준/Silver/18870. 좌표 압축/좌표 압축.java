import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Map<Integer, Integer> map = new HashMap<>();

		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] oArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			oArr[i] = arr[i];
		}

		Arrays.sort(arr);

		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], idx++);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			sb.append(map.get(oArr[i])).append(" ");
		}
		System.out.println(sb);
	}

}