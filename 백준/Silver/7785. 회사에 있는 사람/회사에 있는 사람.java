import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 출입 기록 수

		Set<String> set = new TreeSet<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String type = st.nextToken();

			if (type.equals("enter")) {
				set.add(name);
			} else {
				set.remove(name);
			}
		}

		Iterator<String> itr = set.iterator();

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

	} // main
}