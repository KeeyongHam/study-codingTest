## 🌵 문제 정보

- **문제명:** [피로도](https://school.programmers.co.kr/learn/courses/30/lessons/87946)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

XX게임에는 피로도 시스템(0 이상의 정수로 표현합니다)이 있으며, 일정 피로도를 사용해서 던전을 탐험할 수 있습니다. 이때, 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"와 던전 탐험을 마쳤을 때
소모되는 "소모 피로도"가 있습니다. "최소 필요 피로도"는 해당 던전을 탐험하기 위해 가지고 있어야 하는 최소한의 피로도를 나타내며, "소모 피로도"는 던전을 탐험한 후 소모되는 피로도를 나타냅니다. 예를 들어 "
최소 필요 피로도"가 80, "소모 피로도"가 20인 던전을 탐험하기 위해서는 유저의 현재 남은 피로도는 80 이상 이어야 하며, 던전을 탐험한 후에는 피로도 20이 소모됩니다.

이 게임에는 하루에 한 번씩 탐험할 수 있는 던전이 여러개 있는데, 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 합니다. 유저의 현재 피로도 k와 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원
배열 dungeons 가 매개변수로 주어질 때, 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.

제한사항

* k는 1 이상 5,000 이하인 자연수입니다.
    * dungeons의 세로(행) 길이(즉, 던전의 개수)는 1 이상 8 이하입니다.
    * dungeons의 가로(열) 길이는 2 입니다.
    * dungeons의 각 행은 각 던전의 ["최소 필요 피로도", "소모 피로도"] 입니다.
    * "최소 필요 피로도"는 항상 "소모 피로도"보다 크거나 같습니다.
    * "최소 필요 피로도"와 "소모 피로도"는 1 이상 1,000 이하인 자연수입니다.
    * 서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.

### 🔸 입출력 예

| k  | dungeons                  | result |
|----|---------------------------|--------|
| 80 | [[80,20],[50,40],[30,10]] | 3      |

---

## 🌵 알고리즘 설계

* DFS와 `visited` 배열을 활용해 모든 던전 방문하고, 최대로 탐험 가능한 던전 수를 계산한다.

1. `visited` 배열을 생성하여 방문 여부를 체크한다.
2. 현재 채력과 방문한 던전의 수를 가지고 `dfs()`를 시작한다.
3. 모든 던전에 대해 반복하면서 다음 조건을 확인한다
   * 이미 방문한 던전이면 `continue`
   * 현재 체력 < 해당 던전의 최소 필요 피로도이면 `continue`
4. 조건에 통과하면
   * `visited[i] = true`로 표시 
   * 피로도를 소모하고 `dfs(현재 체력 - 소모 피로도, cnt + 1)` 재귀 호출
   * 호출이 끝난 뒤 `visited[i] = false`로 되돌림
5. 탐색 중 매 단계마다 `cnt`와 `result`를 비교해 최대값을 저장한다.
6. 모든 경로를 탐색후 결과를 반환한다.
---

## 🌵 코드

```java
public class 피로도 {
  int result = 0;
  int[][] dungeons;
  boolean[] visited;

  public int solution(int k, int[][] dungeons) {
    this.dungeons = dungeons;
    visited = new boolean[dungeons.length];

    dfs(k, 0);

    return result;
  }

  private void dfs(int fatigue, int cnt) {

    for (int i = 0; i < dungeons.length; i++) {

      if (visited[i]) {
        continue;
      }

      if (fatigue < dungeons[i][0]) {
        continue;
      }

      result = Math.max(result, cnt + 1);

      visited[i] = true;
      dfs(fatigue - dungeons[i][1], cnt + 1);
      visited[i] = false;
    }
  }
}
```