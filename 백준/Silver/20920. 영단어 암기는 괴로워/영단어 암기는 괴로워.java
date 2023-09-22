import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Word {
		String name;
		int length, cnt;

		public Word(String name, int length) {
			super();
			this.name = name;
			this.length = length;
			this.cnt = 1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 길이가 M 이상인 단어만 저장하되
		// 1. 자주 나오는 단어 (빈도)
		// 2. 단어의 길이가 길수록
		// 3. 사전순

		int N = Integer.parseInt(st.nextToken()); // 단어 수
		int M = Integer.parseInt(st.nextToken()); // 단어 길이

		// pq vs list 저장 후 1회 정렬

		Map<String, Word> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			int length = input.length();
			if (length < M)
				continue;
			Word tmp = new Word(input, length);
			if (map.containsKey(input)) {
				++map.get(input).cnt;
				continue;
			}
			map.put(input, tmp);
		}

		List<Word> list = new ArrayList<>();

		for (String i : map.keySet()) {
			list.add(map.get(i));
		}

		Collections.sort(list, new Comparator<Word>() {

			@Override
			public int compare(Main.Word o1, Main.Word o2) {
				if (o1.cnt != o2.cnt) {
					return o2.cnt - o1.cnt;
				} else if (o1.length != o2.length) {
					return o2.length - o1.length;
				} else {
					return o1.name.compareTo(o2.name);
				}
			}
		});

		for (Word w : list) {
			sb.append(w.name).append("\n");
		}

		System.out.println(sb);
	}// main

}