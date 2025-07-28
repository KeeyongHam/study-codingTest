## 🌵 문제 정보

- **문제명:** [가장 큰 수](https://school.programmers.co.kr/learn/courses/30/lessons/42746)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항

* numbers의 길이는 1 이상 100,000 이하입니다.
* numbers의 원소는 0 이상 1,000 이하입니다.
* 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.

### 🔸 입출력 예

| numbers           | return    |
|-------------------|-----------|
| [6, 10, 2]        | "6210"    |
| [3, 30, 34, 5, 9] | "9534330" |

---

## 🌵 알고리즘 설계

* `Comparator`를 활용하여 문제를 풀 수 있다.

1. `numbers`를 스트림으로 변환한다.
2. 변환된 스트림의 값을 문자열로 변환한다.
3. `sorted()`에 사용할 `Comparator`를 구현한다.
* `o1 + o2`와 `o2 + o1`의 값을 비교 후 -1을 곱하여 반전한다.(default가 오름차순이기 때문)
4. 만약 반환된 `list`의 첫 번째 값이 0일 경우, 뒤의 값들 모두 0이라 판단하고 0을 반환한다.
5. 첫 번째 값이 0이 아닐 경우 `StringBuilder`를 사용하여 모든 값을 하나의 문자열로 합쳐 반환한다.

---

## 🌵 코드

```java
public class 가장_큰_수 {
    public String solution(int[] numbers) {

        List<String> strNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((o1, o2) -> ((o1 + o2).compareTo(o2 + o1)) * -1)
                .collect(Collectors.toList());

        if (strNumbers.get(0).equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        strNumbers.forEach(sb::append);

        return sb.toString();
    }
}
```