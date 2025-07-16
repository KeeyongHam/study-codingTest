## 🌵 문제 정보

- **문제명:** [올바른 괄호](https://school.programmers.co.kr/learn/courses/30/lessons/12909)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어

* "()()" 또는 "(())()" 는 올바른 괄호입니다.
* ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
* '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.

제한사항

* 문자열 s의 길이 : 100,000 이하의 자연수
* 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.

### 🔸 입출력 예

| s       | answer |
|---------|--------|
| "()()"  | 	true  |
| "(())()"	 | true   |
| ")()("	 | false  |
| "(()("	 | false  |

---

## 🌵 알고리즘 설계

1. 문자열 `s`를 나누어 배열로 반환
2. 배열을 순회하며 `(` 경우 `cnt`를 1증가 시키고,
3. `)` 경우 `cnt`를 1감소 시킴
4. `cnt`의 값이 0일때 `)`가 입력되면 `flase`를 반환(`(` 보다 `)`가 먼저 나오는 경우라 판단)
5. `cnt`값이 0일 경우 `true`를 아닌 경우 `false`를 반환

---

## 🌵 코드

```java
boolean solution(String s) {

    int cnt = 0;

    for (String str : s.split("")) {
        if (str.equals("(")) {
            cnt++;
            continue;
        }

        if (cnt == 0) {
            return false;
        }

        cnt--;
    }

    return cnt == 0 ? true : false;
}
```