import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[][] tree;
	static List<int[]> pos;
	static Map<Integer, Integer> comp;
	static int[] compOriginArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// N : 직사각형 개수
		N = Integer.parseInt(st.nextToken(" "));

		// pos : {x좌표, y좌표, 높이} 를 담아주는 List
		pos = new ArrayList<>();

		// 좌표를 받아 List에 2차원 배열 형태로 저장하기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken(" "));
			int x2 = Integer.parseInt(st.nextToken(" "));
			int y1 = Integer.parseInt(st.nextToken(" "));
			int y2 = Integer.parseInt(st.nextToken(" "));

			// y2 - y1 : 해당 x좌표에서 직사각형의 높이
			pos.add(new int[] { x1, y1, y2 }); // [1] : y1 / [2] : y2 => 직사각형의 시작지점
			pos.add(new int[] { x2, y2, y1 }); // [1] : y2 / [2] : y1 => 직사각형의 종료지점
		}

		// comp : 좌표 압축을 위한 HashMap
		comp = new HashMap<>();

		// compOriginArr : 압축 이전의 원래 좌표를 넣을 HashMap
		compOriginArr = new int[pos.size()];

		// y축 정렬
		Collections.sort(pos, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		// y축 압축
		int idx = -1;
		for (int i = 0; i < pos.size(); i++) {
			int[] cur = pos.get(i);

			if (!comp.containsKey(cur[1])) {
				comp.put(cur[1], ++idx);
			}
			compOriginArr[idx] = cur[1];
			
			cur[1] = comp.get(cur[1]);
		}

		// x축 정렬
		Collections.sort(pos, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

//		int h = (int) Math.ceil(Math.log(comp.size() - 1) / Math.log(2));
//		int size = 1_600_000;
		int h = (int) Math.ceil(Math.log(N*2) / Math.log(2));
		int size = 1 << h+1;

		tree = new long[size][2];

		long res = 0;
		int[] pre = new int[] { 0, 0, 0 };
		for (int i = 0; i < pos.size(); i++) {
			int[] cur = pos.get(i);

			// 압축된 높이들 가져오기
			int start = cur[1];
			int end = comp.get(cur[2]);

			res += tree[1][0] * (cur[0] - pre[0]);

			if (start > end) {
				update(1, 0, N * 2 - 1, end, start - 1, -1);
			} else {
				update(1, 0, N * 2 - 1, start, end - 1, 1);
			}

			pre = cur;
		}
		System.out.println(res);
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		// 찾고자 하는 범위를 벗어났다면 리턴
		if (end < left || right < start) {
			return;
		}

		// 중간 점 구하기
		int mid = (start + end) / 2;

		// 해당 구간 범위에 들어왔다면
		if (left <= start && end <= right) {

			// 해당 구간의 세로 선을 활성화시킨다.
			tree[node][1] += val;
		}

		// 범위에서 벗어났다면
		else {
			update(node * 2, start, mid, left, right, val);
			update(node * 2 + 1, mid + 1, end, left, right, val);
		}

		// 해당 구간에 세로 선이 하나 이상 활성화 됐다면
		if (tree[node][1] != 0) {

			// 해당 구간의 세로 길이를 저장한다.
			tree[node][0] = compOriginArr[end + 1] - compOriginArr[start];
		}

		// 해당 구간에 선이 모두 죽어있다면
		else {

			// 리프 노드일 때만
			if (start == end) {

				// 해당 구간의 선을 비활성화 시킨다(값을 초기화시킨다)
				tree[node][0] = 0;

			} else {

				// 리프노드가 아니라면 자손 노드의 값들을 더해준다.
				tree[node][0] = tree[node * 2][0] + tree[node * 2 + 1][0];
			}
		}

	}

}
