## 🌵 문제 정보

- **문제명:** [소수 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/42839)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항

* numbers는 길이 1 이상 7 이하인 문자열입니다.
* numbers는 0~9까지 숫자만으로 이루어져 있습니다.
* "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

### 🔸 입출력 예

| scoville             | K | return |
|----------------------|---|--------|
| [1, 2, 3, 9, 10, 12] | 7 | 2      |

| numbers | return |
|---------|--------|
| "17"    | 3      |
| "011"   | 2      |

---

## 🌵 알고리즘 설계

* 모든 순열 조합을 구해서 수로 만든다.
* 중복된 수는 제거하기 위해 `Set`에 저장한다.
* 만들어진 수들 중에서 소수만 필터링하여 개수를 센다.

1. DFS + 백트래킹을 사용하여 모든 숫자 조합 생성
    * 사용 여부를 기록하기 위해 `visited[]` 배열 사용
    * `"""`부터 시작하여 한 자리씩 붙여가며 조합 생성
    * 매 조합은` Set<Integer>`에 저장하여 중복 제거

2. 소수 판별 함수 구현
    * 2부터 `sqrt(n)`까지 나눠서 나누어 떨어지면 소수가 아님
    * 소수 조건: `n ≥ 2`

3. 최종 결과 반환
    * `Set`에 저장된 수들 중 소수만 걸러 개수를 반환

---

## 🌵 코드

```java
 public class 소수_찾기 {
    Set<Integer> set = new HashSet<>();
    char[] charArr;
    boolean[] visited;

    public int solution(String numbers) {
        charArr = numbers.toCharArray();
        visited = new boolean[charArr.length];

        dfs("");

        return gerResult();
    }

    private void dfs(String strNum) {

        if (!strNum.isEmpty()) {
            set.add(Integer.valueOf(strNum));
        }

        for (int i = 0; i < charArr.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(strNum + charArr[i]);
            visited[i] = false;
        }
    }

    private int gerResult() {
        return (int) set.stream()
                .mapToInt(i -> i)
                .filter(this::isPrime)
                .count();
    }

    private boolean isPrime(Integer num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```