const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

(function solution() {
    const answer = [];
    for (let i = 0; i < input.length - 1; i++) {
        const word = input[i];
        const res = Array(4).fill(0);

        for (let j = 0; j < word.length; j++) {
            const code = word[j].charCodeAt(0);

            if (code >= 97) {
                res[0]++;
            } else if (code >= 65) {
                res[1]++;
            } else if (code >= 48) {
                res[2]++;
            } else {
                res[3]++;
            }
        }
        answer.push(res.join(" "));
    }
    console.log(answer.join("\n"));
})();
