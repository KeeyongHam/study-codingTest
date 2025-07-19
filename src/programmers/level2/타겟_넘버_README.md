## 🌵 문제 정보

- **문제명:** [타겟넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

```text
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
```

사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때
숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

**제한사항**

* 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
* 각 숫자는 1 이상 50 이하인 자연수입니다.
* 타겟 넘버는 1 이상 1000 이하인 자연수입니다.

---

## 🌵 알고리즘 설계

* **재귀**를 활용하여 모든 경우의 수를 탐색한다.

1. `numbers` 배열의 각 원소를 하나씩 선택하면서 + 또는 - 연산을 재귀적으로 수행한다.
2. 합과 깊이를 인자로 재귀호출한다.
3. 깊이가 `numbers` 배열의 크기와 같을 때 종료하며, 이 시점에 합이 `target`과 같으면 카운트한다.
4. 모든 경우의 수를 확인하고 카운트를 반환한다.


---

## 🌵 코드

```java
public class 타겟_넘버 {
    int cnt = 0;
    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        dfs(0, 0);

        return cnt;
    }

    public void dfs(int sum, int depth) {

        if (depth == numbers.length) {
            if (sum == target) {
                cnt++;
            }
            return;
        }

        dfs(sum - numbers[depth], depth + 1);
        dfs(sum + numbers[depth], depth + 1);
    }
}
```