## 🌵 문제 정보

- **문제명:** [영어 끝말잇기](https://school.programmers.co.kr/learn/courses/30/lessons/12981)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

1부터 n까지 번호가 붙어있는 n명의 사람이 영어 끝말잇기를 하고 있습니다. 영어 끝말잇기는 다음과 같은 규칙으로 진행됩니다.

1. 1번부터 번호 순서대로 한 사람씩 차례대로 단어를 말합니다.
2. 마지막 사람이 단어를 말한 다음에는 다시 1번부터 시작합니다.
3. 앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 합니다.
4. 이전에 등장했던 단어는 사용할 수 없습니다.
5. 한 글자인 단어는 인정되지 않습니다.

다음은 3명이 끝말잇기를 하는 상황을 나타냅니다.

tank → kick → know → wheel → land → dream → mother → robot → tank

위 끝말잇기는 다음과 같이 진행됩니다.

* 1번 사람이 자신의 첫 번째 차례에 tank를 말합니다.
* 2번 사람이 자신의 첫 번째 차례에 kick을 말합니다.
* 3번 사람이 자신의 첫 번째 차례에 know를 말합니다.
* 1번 사람이 자신의 두 번째 차례에 wheel을 말합니다.
* (계속 진행)

끝말잇기를 계속 진행해 나가다 보면, 3번 사람이 자신의 세 번째 차례에 말한 tank 라는 단어는 이전에 등장했던 단어이므로 탈락하게 됩니다.

사람의 수 n과 사람들이 순서대로 말한 단어 words 가 매개변수로 주어질 때, 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지를 구해서 return 하도록 solution 함수를
완성해주세요.

제한 사항

* 끝말잇기에 참여하는 사람의 수 n은 2 이상 10 이하의 자연수입니다.
* words는 끝말잇기에 사용한 단어들이 순서대로 들어있는 배열이며, 길이는 n 이상 100 이하입니다.
* 단어의 길이는 2 이상 50 이하입니다.
* 모든 단어는 알파벳 소문자로만 이루어져 있습니다.
* 끝말잇기에 사용되는 단어의 뜻(의미)은 신경 쓰지 않으셔도 됩니다.
* 정답은 [ 번호, 차례 ] 형태로 return 해주세요.
* 만약 주어진 단어들로 탈락자가 생기지 않는다면, [0, 0]을 return 해주세요.

### 🔸 입출력 예

| n | 	words                                                                                                                                                              | result |
|---|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| 3 | 	["tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"]                                                                                      | [3,3]  |
| 5 | 	["hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"] | [0,0]  |
| 2 | 	["hello", "one", "even", "never", "now", "world", "draw"]                                                                                                          | [1,3]  |

---

## 🌵 알고리즘 설계

* `HashSet`을 활용해 중복되는 단어를 체크
* 이전 단어의 마지막과 현재 단어의 시작 문자를 체크

1. `HashSet`을 생성하여 중복 단어를 판별할 수 있도록 준비한다.
2. 첫 번째 단어를 HashSet에 미리 추가한다.
3. 두 번째 단어부터 마지막 단어까지 순회한다. 
4. 현재 단어가 이전 단어의 마지막 문자로 시작하지 않거나, 이미 등장한 단어일 경우 → 탈락 조건 
5. 탈락 조건을 만족하면 (i % n) + 1, (i / n) + 1을 계산하여 [번호, 차례] 배열에 담고 반환한다.
6. 모든 단어가 조건을 만족하면 [0, 0] 반환한다. 

---

## 🌵 코드

```java
 public class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {

        int[] result = new int[]{0, 0};

        HashSet<String> set = new HashSet<>();
        set.add(words[0]);

        for (int i = 1; i < words.length; i++) {

            String beforeWord = words[i - 1];
            String currentWord = words[i];

            if (beforeWord.charAt(beforeWord.length() - 1) != currentWord.charAt(0) || !set.add(words[i])) {
                result[0] = (i % n) + 1;
                result[1] = (i / n) + 1;
                break;
            }
        }

        return result;
    }
}

```