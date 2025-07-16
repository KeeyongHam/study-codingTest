## 🌵 문제 정보

- **문제명:** [다음 큰 숫자](https://school.programmers.co.kr/learn/courses/30/lessons/12911)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

* 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
* 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
* 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.

예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.

제한 사항

* n은 1,000,000 이하의 자연수 입니다.

### 🔸 입출력 예

| n	  | result |
|-----|--------|
| 78	 | 83     |
| 15	 | 23     |

---

## 🌵 알고리즘 설계

* 1의 개수가 같으면서 가장 작은 숫자를 찾아야 됨
* `Integer.bitCount()` 활용: 해당 숫자를 2진수로 변환 후 1의 개수를 카운트

1. `int n`의 1의 개수를 `targetOneCount`에 저장
2. `do-while`문을 통해 다음 숫자의 2진수 1의 개수를 비교

---

## 🌵 코드

```java
 public int solution(int n) {
    int targetOneCount = Integer.bitCount(n);
    int nextNumber = n;
    do {
        nextNumber += 1;
    } while (targetOneCount != Integer.bitCount(nextNumber));

    return nextNumber;
}
```