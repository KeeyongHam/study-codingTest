## 🌵 문제 정보

- **문제명:** [JadenCase_문자열_만들기](https://school.programmers.co.kr/learn/courses/30/lessons/12951)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열입니다. 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됩니다. (첫 번째 입출력 예 참고)
문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.

제한 조건

* s는 길이 1 이상 200 이하인 문자열입니다.
* s는 알파벳과 숫자, 공백문자(" ")로 이루어져 있습니다.
    * 숫자는 단어의 첫 문자로만 나옵니다.
    * 숫자로만 이루어진 단어는 없습니다.
    * 공백문자가 연속해서 나올 수 있습니다.

### 🔸 입출력 예

| s	                       | return                  |
|--------------------------|-------------------------|
| "3people unFollowed me"	 | "3people Unfollowed Me" |
| "for the last week"	     | "For The Last Week"     |

---

## 🌵 알고리즘 설계

1. 문자열 `s`를 문자 배열로 만들고, 각 문자를 순회한다.
2. `isFirst`로 첫 문자인지 확인한다.
3. 문자 `c`가 공백일 경우 다음 문자가 첫 문자라고 판단하여 `isFirst`의 값을 `true`로 변경 후 `StringBuilder`에 값을 추가한다.
4. 문자 `c`가 공백이 아니고, `isFirst`가 `true`일 경우 첫 문자로 판단해 대문자로 변경 및 `isFirst`를 `false`로 변경한다.
5. 문자 `c`가 공백이 아니고, `isFirst`가 `false`일 경우 첫 문자가 아니라고 판단하여 소문자로 변경한다.

---

## 🌵 코드

```java
public String solution(String s) {
  StringBuilder sb = new StringBuilder();

  boolean isFirst = true;

  for (char c : s.toCharArray()) {
    if (c == ' ') {
      sb.append(c);
      isFirst = true;
    } else {
      if (isFirst) {
        sb.append(String.valueOf(c).toUpperCase());
        isFirst = false;
      } else {
        sb.append(String.valueOf(c).toLowerCase());
      }
    }
  }

  return sb.toString();
}
```