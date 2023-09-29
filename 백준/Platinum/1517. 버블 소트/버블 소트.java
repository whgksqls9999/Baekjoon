import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] arr, index, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 숫자의 개수 입력받기
		N = Integer.parseInt(st.nextToken());

		// 입력받은 숫자를 저장할 배열 arr 선언
		arr = new long[N];

		// 선언한 배열 arr에 입력받은 숫자 저장하기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 입력받은 숫자의 값을 key로 하여 인덱스를 뽑아올 수 있는 map 선언
		// 중복값은 같은 index로 저장되게 된다
		Map<Long, Integer> pos = new HashMap<>();
		for (int i = 0; i < N; i++) {
			pos.put(arr[i], i);
		}

		// 입력받은 값들을 복사하여 오름차순으로 정렬하기
		index = arr.clone();
		Arrays.sort(index);

		// 트리 배열 선언
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);
		tree = new long[size];

		// 정답을 담아줄 ans 변수 선언
		long ans = 0;

		for (int i = 0; i < N; i++) {

			// 값을 오름차순으로 정렬한 배열에서 제일 낮은 숫자부터 꺼내온다
			// 해당 값의 인덱스를 가져와 idx에 저장
			// 중복 값은 같은 인덱스를 가져오게 된다
			int idx = pos.get(index[i]);

			// 해당 값의 뒤쪽 ~ 끝 구간까지 탐색하여 구간합 가져오기
			ans += sum(1, 0, N - 1, idx + 1, N - 1);

			// 해당 값에 1씩 더해 업데이트시키기
			update(1, 0, N - 1, idx, 1);

		}
		System.out.println(ans);
	}

	// 해당 구간의 구간합을 구하여 출력
	static long sum(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;

		if (left <= start && end <= right)
			return tree[node];

		return sum(node * 2, start, (start + end) / 2, left, right)
				+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}

	// 해당 인덱스의 값을 업데이트
	static void update(int node, int start, int end, int idx, int diff) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			tree[node] = diff;
			return;
		}

		update(node * 2, start, (start + end) / 2, idx, diff);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

}