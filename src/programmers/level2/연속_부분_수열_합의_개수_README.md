## 🌵 문제 정보

- **문제명:** [연속 부분 수열 합의 개수](https://school.programmers.co.kr/learn/courses/30/lessons/131701)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

철호는 수열을 가지고 놀기 좋아합니다. 어느 날 철호는 어떤 자연수로 이루어진 원형 수열의 연속하는 부분 수열의 합으로 만들 수 있는 수가 모두 몇 가지인지
알아보고 싶어졌습니다. 원형 수열이란 일반적인 수열에서 처음과 끝이 연결된 형태의 수열을 말합니다.
예를 들어 수열 [7, 9, 1, 1, 4] 로 원형 수열을 만들면 다음과 같습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/f207cd37-34dc-4cbd-96bb-83435bd6efd4/%EA%B7%B8%EB%A6%BC.png" width="300">

원형 수열은 처음과 끝이 연결되어 끊기는 부분이 없기 때문에 연속하는 부분 수열도 일반적인 수열보다 많아집니다.
원형 수열의 모든 원소 elements가 순서대로 주어질 때, 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 개수를 return 하도록 solution 함수를 완성해주세요.

제한사항

* 3 ≤ elements의 길이 ≤ 1,000
* 1 ≤ elements의 원소 ≤ 1,000

### 🔸 입출력 예

| elements    | result |
|-------------|--------|
| [7,9,1,1,4] | 18     |

---

## 🌵 알고리즘 설계

* 수열을 두 번 이어붙여 원형 회전을 선형 처리
* 모든 길이의 부분 수열을 순회하며 합 계산
* 중복 없는 합을 HashSet에 저장 → 개수 반환

1. 수열을 한 번 더 이어붙여서 길이 2n의 리스트를 만든다.
2. 1 ≤ 부분 수열 길이 ≤ n 범위에 대해 반복하며,시작 인덱스를 바꾸며 모든 연속 부분 수열을 잘라낸다.
3. 잘라낸 구간의 합을 계산하여 `HashSet`에 저장한다.
4. 모든 경우를 확인한 후, `HashSet`의 크기를 반환한다.

---

## 🌵 코드

```java
 public class 연속_부분_수열_합의_개수 {
    public int solution(int[] elements) {

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int element : elements) {
                list.add(element);
            }
        }

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                set.add(list.subList(i, i + j).stream()
                        .mapToInt(num -> num)
                        .sum());
            }
        }

        return set.size();
    }
}
```