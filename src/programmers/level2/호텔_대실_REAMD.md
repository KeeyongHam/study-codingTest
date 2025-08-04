## 🌵 문제 정보

- **문제명:** [호텔 대실](https://school.programmers.co.kr/learn/courses/30/lessons/155651)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

호텔을 운영 중인 코니는 최소한의 객실만을 사용하여 예약 손님들을 받으려고 합니다. 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님들이 사용할 수 있습니다.
예약 시각이 문자열 형태로 담긴 2차원 배열 book_time이 매개변수로 주어질 때, 코니에게 필요한 최소 객실의 수를 return 하는 solution 함수를 완성해주세요.

**제한사항**

* 1 ≤ book_time의 길이 ≤ 1,000
    * book_time[i]는 ["HH:MM", "HH:MM"]의 형태로 이루어진 배열입니다
        * [대실 시작 시각, 대실 종료 시각] 형태입니다.
    * 시각은 HH:MM 형태로 24시간 표기법을 따르며, "00:00" 부터 "23:59" 까지로 주어집니다.
        * 예약 시각이 자정을 넘어가는 경우는 없습니다.
        * 시작 시각은 항상 종료 시각보다 빠릅니다.

### 🔸 입출력 예

| book_time                                                                                            | result |
|------------------------------------------------------------------------------------------------------|--------|
| [["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]] | 3      |
| [["09:10", "10:10"], ["10:20", "12:20"]]                                                             | 1      |
| [["10:20", "12:30"], ["10:20", "12:30"], ["10:20", "12:30"]]                                         | 3      |

**입출력 예 #1**

입출력 예 #1

<img src="https://user-images.githubusercontent.com/62426665/199907266-561e3b75-84eb-4da1-930c-a6ac8fa82a79.png" width="300">

위 사진과 같습니다.

입출력 예 #2

첫 번째 손님이 10시 10분에 퇴실 후 10분간 청소한 뒤 두 번째 손님이 10시 20분에 입실하여 사용할 수 있으므로 방은 1개만 필요합니다.

입출력 예 #3

세 손님 모두 동일한 시간대를 예약했기 때문에 3개의 방이 필요합니다.

---

## 🌵 알고리즘 설계

* 우선 순위 큐를 사용하여 문제를 해결할 수 있다.

1. 우선 순위 큐를 생성하고 기본값인 오름차순 정렬을 사용한다.
2. `book_time` 배열을 시작 시작 기준으로 오름차순하여 정렬한다.
3. 정렬된 배열을 순회하며 다음과 같은 작업을 수행한다.
   * 배열에서 시작 시간과 종료 시간을 가져와 `LocalTime`으로 변환한다.
   * 종료시간이 23:50분 미만이라면 종료시간 + 10분으로 설정하며, 23:50분 이상이라면 자정을 넘어가는 것을 방지하기 위해 종료시간을 23:59분으로 설정한다.
   * 큐에 값이 있고, 시작시간이 큐에 값이 시작 시간 이후가 아니라면 큐의 값을 꺼낸다.
   * 큐에 종료시간을 넣는다.
4. 배열의 순회가 끝나고 큐의 크기를 반환한다.
---

## 🌵 코드

```java
public class 호텔_대실 {
    public int solution(String[][] book_time) {
        Queue<LocalTime> endTimeQueue = new PriorityQueue<>();

        Arrays.sort(book_time, Comparator.comparing(o -> o[0]));
        for (String[] bookTime : book_time) {
            LocalTime startTime = LocalTime.parse(bookTime[0]);
            LocalTime endTime = LocalTime.parse(bookTime[1]);

            if (endTime.isBefore(LocalTime.of(23, 50))) {
                endTime = endTime.plusMinutes(10);
            } else {
                endTime = LocalTime.of(23, 59);
            }

            if (!endTimeQueue.isEmpty() && !endTimeQueue.peek().isAfter(startTime)) {
                endTimeQueue.poll();
            }

            endTimeQueue.offer(endTime);
        }

        return endTimeQueue.size();
    }
}
```