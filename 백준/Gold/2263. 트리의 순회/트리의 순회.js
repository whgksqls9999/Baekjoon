const input = require('fs')
  .readFileSync(0)
  .toString()
  .trim()
  .split('\n');

function solution() {
  let idx = 0;
  const N = Number(input[idx++]);

  const inOrder = input[idx++].split(' ').map(Number);
  const postOrder = input[idx++].split(' ').map(Number);

  const answer = [];

  const stack = [];
  stack.push([0, N - 1, 0, N - 1]); // inL, inR, postL, postR

  while (stack.length !== 0) {
    let [inL, inR, postL, postR] = stack.pop();
    if (inL > inR || postL > postR) {
      continue;
    }

    const root = postOrder[postR];
    answer.push(root);

    let rootIndex;
    for (let i = inL; i <= inR; i++) {
      if (inOrder[i] === root) {
        rootIndex = i;
        break;
      }
    }

    stack.push([rootIndex + 1, inR, postL + rootIndex - inL, postR - 1]);
    stack.push([inL, rootIndex - 1, postL, postL + rootIndex - 1 - inL]);
  }

  return answer.join(' ');
}

function getPreOrder(answer, inOrder, postOrder, left, right) {
  if (left >= right) {
    answer.push(postOrder[right]);
    return;
  }

  const root = postOrder[right];
  const rootIndex = inOrder.indexOf(root);

  answer.push(root);

  if (left < rootIndex - 1) {
    getPreOrder(answer, inOrder, postOrder, left, rootIndex - 1);
  } else {
    answer.push(postOrder[rootIndex - 1]);
  }

  if (rootIndex + 1 < right - 1) {
    getPreOrder(answer, inOrder, postOrder, rootIndex + 1, right - 1);
  } else {
    answer.push(postOrder[right - 1]);
  }
}

console.log(solution());
