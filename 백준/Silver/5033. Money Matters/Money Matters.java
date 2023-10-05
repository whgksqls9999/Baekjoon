import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Person {
		Person rep;
		int cost;
		List<Person> connected;

		public Person(int cost) {
			super();
			this.rep = this;
			this.cost = cost;
			this.connected = new ArrayList<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 사람 수 (노드)
		int M = Integer.parseInt(st.nextToken()); // 친구 관계 수 (간선)

		Person[] persons = new Person[N];

		for (int i = 0; i < N; i++) {
			int cost = Integer.parseInt(br.readLine());
			persons[i] = new Person(cost);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Person aa = persons[a];
			Person bb = persons[b];

			aa = find(aa);
			bb = find(bb);

			union(aa, bb);
		}
		for (int i = 0; i < N; i++) {
			find(persons[i]);
		}

		for (int i = 0; i < N; i++) {
			if (persons[i].rep.cost != 0) {
				System.out.println("IMPOSSIBLE");
				return;
			}
		}

		System.out.println("POSSIBLE");
	}

	static void union(Person a, Person b) {
		if (a.rep == b.rep)
			return;

		b.rep = a;
		a.cost += b.cost;
	}

	static Person find(Person a) {
		if (a == a.rep)
			return a;

		return a.rep = find(a.rep);
	}
}