## 🌵 문제 정보

- **문제명:** [카펫](https://school.programmers.co.kr/learn/courses/30/lessons/42842)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/b1ebb809-f333-4df2-bc81-02682900dc2d/carpet.png" width="300" />

Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항

* 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
* 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
* 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.

### 🔸 입출력 예

| brown | yellow | return |
|-------|--------|--------|
| 10    | 2      | [4, 3] |
| 8     | 1      | [3, 3] |
| 24    | 24     | [8, 6] |

---

## 🌵 알고리즘 설계

* 가로의 길이가 세로의 길이와 같거나 크다. (가로 >= 세로)
* 노랑색 카펫은 갈색 카펫 내부에 있는 형태이다.
* 전체 격자 수 = 갈색 + 노란색 = 가로 × 세로
* 노란색 격자 수 = (가로 - 2) × (세로 - 2)

1. `area`에 전체 격자 수 (brown + yellow)를 저장한다.
2. 세로(`height`)는 3부터 시작하여 `area`보다 작거나 같은 값까지 반복한다.
3. 만약 `area % height != 0`이거나, 가로(`width = area / height`)가 세로보다 작은 경우는 제외한다.
4. `(width - 2) * (height - 2) == yellow`인 조합이 있다면, 해당 `(width, height)`를 정답으로 반환한다.
5. 위 과정을 만족하는 조합을 찾을 때까지 2~4를 반복한다.

---

## 🌵 코드

```java
public class 카펫 {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow;

        for (int h = 3; h < area; h++) {
            if (area % h != 0) {
                continue;
            }

            int w = area / h;

            if (h > w) {
                continue;
            }

            if ((w - 2) * (h - 2) == yellow) {
                return new int[]{w, h};
            }
        }

        return null;
    }
}
```