import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// N, C 입력
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// 입력된 순서를 보장받는 LinkedHashMap 선언
		Map<Integer, Integer> map = new LinkedHashMap<>();

		// N개의 수를 해시맵에 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int n = Integer.parseInt(st.nextToken());
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		// arr : [0] = 입력받은 수, [1] = 빈도수
		int[][] arr = new int[N][2];

		// itr : 해시맵의 key값들을 순차적으로 순회할 iterator
		Iterator itr = map.keySet().iterator();

		for (int i = 0; itr.hasNext(); ++i) {
			int key = (Integer) itr.next();
			arr[i][0] = key;
			arr[i][1] = map.get(key);
		}

		// 빈도수 순으로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});

		for (int[] i : arr) {
			while (i[1]-- > 0) {
//			for (int j = 0; j < i[1]; ++j) {
				sb.append(i[0]).append(" ");
			}
			if (i[0] == 0 && i[1] == 0) {
				break;
			}
		}

		System.out.println(sb);
	}
}
