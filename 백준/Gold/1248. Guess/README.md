# [Gold III] Guess - 1248 

[문제 링크](https://www.acmicpc.net/problem/1248) 

### 성능 요약

메모리: 13032 KB, 시간: 7068 ms

### 분류

브루트포스 알고리즘, 백트래킹

### 제출 일자

2025년 7월 20일 00:23:15

### 문제 설명

<p>Given a sequence of integers, a<sub>1</sub>, a<sub>2</sub>, …, a<sub>n</sub>, we define its sign matrix S such that, for 1 ≤ i ≤ j ≤ n, S<sub>ij</sub>="+" if a<sub>i</sub> + … + a<sub>j</sub> > 0; S<sub>ij</sub>="−" if a<sub>i</sub> + … + a<sub>j</sub> < 0; and S<sub>ij</sub>="0" otherwise. </p>

<p>For example, if (a<sub>1</sub>, a<sub>2</sub>, a<sub>3</sub>, a<sub>4</sub>)=( −1, 5, −4, 2), then its sign matrix S is a 4×4 matrix: </p>

<table class="table table-bordered" style="width:15%">
	<thead>
		<tr>
			<th style="width:3%"> </th>
			<th style="width:3%">1</th>
			<th style="width:3%">2</th>
			<th style="width:3%">3</th>
			<th style="width:3%">4</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>1</th>
			<td>-</td>
			<td>+</td>
			<td>0</td>
			<td>+</td>
		</tr>
		<tr>
			<th>2</th>
			<td> </td>
			<td>+</td>
			<td>+</td>
			<td>+</td>
		</tr>
		<tr>
			<th>3</th>
			<td> </td>
			<td> </td>
			<td>-</td>
			<td>-</td>
		</tr>
		<tr>
			<th>4</th>
			<td> </td>
			<td> </td>
			<td> </td>
			<td>+</td>
		</tr>
	</tbody>
</table>

<p>We say that the sequence (−1, 5, −4, 2) generates the sign matrix. A sign matrix is valid if it can be generated by a sequence of integers. </p>

<p>Given a sequence of integers, it is easy to compute its sign matrix. This problem is about the opposite direction: Given a valid sign matrix, find a sequence of integers that generates the sign matrix. Note that two or more different sequences of integers can generate the same sign matrix. For example, the sequence (−2, 5, −3, 1) generates the same sign matrix as the sequence (−1,5, −4,2). </p>

<p>Write a program that, given a valid sign matrix, can find a sequence of integers that generates the sign matrix. You may assume that every integer in a sequence is between −10 and 10, both inclusive. </p>

### 입력 

 <p>The first line contains an integer n(1 ≤ n ≤ 10), where n is the length of a sequence of integers. The second line contains a string of n(n+1)/2 characters such that the first n characters correspond to the first row of the sign matrix, the next n−1 characters  to the second row, ..., and the last character to the n-th row. </p>

### 출력 

 <p>Output exactly one line containing a sequence of n integers which generates the sign matrix. If more than one sequence generates the sign matrix, you may output any one of them. Every integer in the sequence must be between −10 and 10, both inclusive.</p>

