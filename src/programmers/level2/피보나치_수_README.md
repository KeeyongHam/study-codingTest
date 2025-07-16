## 🌵 문제 정보

- **문제명:** [피보나치 수](https://school.programmers.co.kr/learn/courses/30/lessons/12945)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

피보나치 수는 F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.

예를들어

* F(2) = F(0) + F(1) = 0 + 1 = 1
* F(3) = F(1) + F(2) = 1 + 1 = 2
* F(4) = F(2) + F(3) = 1 + 2 = 3
* F(5) = F(3) + F(4) = 2 + 3 = 5

와 같이 이어집니다.

2 이상의 n이 입력되었을 때, n번째 피보나치 수를 1234567으로 나눈 나머지를 리턴하는 함수, solution을 완성해 주세요.

제한 사항

* n은 2 이상 100,000 이하인 자연수입니다.

### 🔸 입출력 예

| n	 | return |
|----|--------|
| 3	 | 2      |
| 5	 | 5      |

---

## 🌵 알고리즘 설계

* n번째 피보나치 수를 구하기전에 미리 배열에 피보나치 수를 저장
* 만약 재귀를 사용하여 작성하게 될 경우 중복 호출로 인해 시간 초과 발생
  * ```
    fibo(5)
    ├─ fibo(4)
    │  ├─ fibo(3)
    │  │  ├─ fibo(2)
    │  │  │  ├─ fibo(1)
    │  │  │  └─ fibo(0)
    │  │  └─ fibo(1)
    │  └─ fibo(2)
    │     ├─ fibo(1)
    │     └─ fibo(0)
    └─ fibo(3) ← 이미 위에서 계산했음
    ```
<br/>

1. `n + 1` 만큼의 배열 생성 
2. `n`번째 인덱스의 값에 `(n - 1 + n - 2) % 1234567` 결과 값을 저장 <- 중복 호출 제거 
3. `n`번째 인덱스 값 반환

---

## 🌵 코드

```java
 public class 피보나치_수 {
    public int solution(int n) {

        int[] fiboNumbers = getFiboNumbers(n);

        return fiboNumbers[n];
    }

    private int[] getFiboNumbers(int n) {
        int[] fiboNumbers = new int[n + 1];
        fiboNumbers[0] = 0;
        fiboNumbers[1] = 1;
        for (int i = 2; i < fiboNumbers.length; i++) {
            fiboNumbers[i] = (fiboNumbers[i - 1] + fiboNumbers[i - 2]) % 1234567;
        }

        return fiboNumbers;
    }
}
```