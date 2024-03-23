const input = require("fs")
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n");

(function solution() {
    const T = Number(input[0]);

    const answer = [];
    for (let tc = 1; tc <= T; tc++) {
        const arr = input[tc].split(" ").map(Number).slice(1);
        const prefixSum = [0];

        for (let i = 0; i < arr.length; i++) {
            prefixSum.push(prefixSum[i] + arr[i]);
        }

        let result = "This sequence is anti-primed.";

        outer: for (let length = 2; length < prefixSum.length; length++) {
            let left = 0,
                right = length;
            while (right < prefixSum.length) {
                const cur = prefixSum[right] - prefixSum[left];

                if (primeCheck(cur)) {
                    result = `Shortest primed subsequence is length ${length}: `;
                    for (let i = left; i < right; i++) {
                        result += arr[i] + " ";
                    }
                    break outer;
                }

                left++;
                right++;
            }
        }
        answer.push(result.trim());
    }

    console.log(answer.join("\n"));
})();

function primeCheck(number) {
    let result = true;
    for (let i = 2; i <= Math.sqrt(number); i++) {
        if (number % i == 0) {
            result = false;
            break;
        }
    }
    return result;
}
