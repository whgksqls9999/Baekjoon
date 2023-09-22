import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Student {
		String name;
		int kor, eng, math;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		Map<String, Integer> map = new HashMap<>();
		int N = Integer.parseInt(st.nextToken());

		Student[] students = new Student[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Student tmp = new Student();
			tmp.name = st.nextToken();
			tmp.kor = Integer.parseInt(st.nextToken());
			tmp.eng = Integer.parseInt(st.nextToken());
			tmp.math = Integer.parseInt(st.nextToken());
			students[i] = tmp;
			map.put(tmp.name, i);
		}

		Arrays.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				int kor1 = o1.kor, kor2 = o2.kor;
				int eng1 = o1.eng, eng2 = o2.eng;
				int math1 = o1.math, math2 = o2.math;

				int res = 0;
				if (kor1 != kor2) {
					res = kor2 - kor1;
				} else if (eng1 != eng2) {
					res = eng1 - eng2;
				} else if (math1 != math2) {
					res = math2 - math1;
				} else {
					res = o1.name.compareTo(o2.name);
				}
				return res;
			}
		});

		for (Student s : students) {
			sb.append(s.name).append("\n");
		}
		System.out.println(sb);
	}// main

}