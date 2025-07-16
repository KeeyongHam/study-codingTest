## 🌵 문제 정보

- **문제명:** [최대값과 최소값](https://school.programmers.co.kr/learn/courses/30/lessons/12939)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다.<br/>
str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.<br/>
예를들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.

### 🔸 입출력 예

| 입력 | 출력 |
|------|------|
| `"1 2 3 4"` | `"1 4"` |
| `"-1 -2 -3 -4"` | `"-4 -1"` |

---

## 🌵 알고리즘 설계

1. 문자열 `s`를 공백 기준으로 나누어 배열로 변환
2. 각 문자열을 `int`로 파싱하여 리스트에 저장
3. 리스트를 정렬
4. 최솟값은 첫 번째 요소, 최댓값은 마지막 요소
5. 문자열로 결합하여 리턴

---

## 🌵 코드

```java
public String solution(String s) {
    List<Integer> numbers = Arrays.stream(s.split(" "))
            .map(Integer::valueOf)
            .sorted()
            .collect(Collectors.toList());

    return numbers.get(0) + " " + numbers.get(numbers.size() - 1);
}