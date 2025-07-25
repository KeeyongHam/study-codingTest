## 🌵 문제 정보

- **문제명:** [오픈 채팅방](https://school.programmers.co.kr/learn/courses/30/lessons/42888)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

카카오톡 오픈채팅방에서는 친구가 아닌 사람들과 대화를 할 수 있는데, 본래 닉네임이 아닌 가상의 닉네임을 사용하여 채팅방에 들어갈 수 있다.

신입사원인 김크루는 카카오톡 오픈 채팅방을 개설한 사람을 위해, 다양한 사람들이 들어오고, 나가는 것을 지켜볼 수 있는 관리자창을 만들기로 했다. 채팅방에 누군가 들어오면 다음 메시지가 출력된다.

"[닉네임]님이 들어왔습니다."

채팅방에서 누군가 나가면 다음 메시지가 출력된다.

"[닉네임]님이 나갔습니다."

채팅방에서 닉네임을 변경하는 방법은 다음과 같이 두 가지이다.

채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
채팅방에서 닉네임을 변경한다.
닉네임을 변경할 때는 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경된다.

예를 들어, 채팅방에 "Muzi"와 "Prodo"라는 닉네임을 사용하는 사람이 순서대로 들어오면 채팅방에는 다음과 같이 메시지가 출력된다.

"Muzi님이 들어왔습니다."
"Prodo님이 들어왔습니다."

채팅방에 있던 사람이 나가면 채팅방에는 다음과 같이 메시지가 남는다.

"Muzi님이 들어왔습니다."
"Prodo님이 들어왔습니다."
"Muzi님이 나갔습니다."

Muzi가 나간후 다시 들어올 때, Prodo 라는 닉네임으로 들어올 경우 기존에 채팅방에 남아있던 Muzi도 Prodo로 다음과 같이 변경된다.

"Prodo님이 들어왔습니다."
"Prodo님이 들어왔습니다."
"Prodo님이 나갔습니다."
"Prodo님이 들어왔습니다."

채팅방은 중복 닉네임을 허용하기 때문에, 현재 채팅방에는 Prodo라는 닉네임을 사용하는 사람이 두 명이 있다. 이제, 채팅방에 두 번째로 들어왔던 Prodo가 Ryan으로 닉네임을 변경하면 채팅방 메시지는 다음과
같이 변경된다.

"Prodo님이 들어왔습니다."
"Ryan님이 들어왔습니다."
"Prodo님이 나갔습니다."
"Prodo님이 들어왔습니다."

채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어질 때, 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return
하도록 solution 함수를 완성하라.

제한사항

* record는 다음과 같은 문자열이 담긴 배열이며, 길이는 1 이상 100,000 이하이다.
* 다음은 record에 담긴 문자열에 대한 설명이다.
    * 모든 유저는 [유저 아이디]로 구분한다.
    * [유저 아이디] 사용자가 [닉네임]으로 채팅방에 입장 - "Enter [유저 아이디] [닉네임]" (ex. "Enter uid1234 Muzi")
    * [유저 아이디] 사용자가 채팅방에서 퇴장 - "Leave [유저 아이디]" (ex. "Leave uid1234")
    * [유저 아이디] 사용자가 닉네임을 [닉네임]으로 변경 - "Change [유저 아이디] [닉네임]" (ex. "Change uid1234 Muzi")
    * 첫 단어는 Enter, Leave, Change 중 하나이다.
    * 각 단어는 공백으로 구분되어 있으며, 알파벳 대문자, 소문자, 숫자로만 이루어져있다.
    * 유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별한다.
    * 유저 아이디와 닉네임의 길이는 1 이상 10 이하이다.
* 채팅방에서 나간 유저가 닉네임을 변경하는 등 잘못 된 입력은 주어지지 않는다.

### 🔸 입출력 예

| record                                                                                                    | result                                                                     |
|-----------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------|
| ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"] | ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."] |

---

## 🌵 알고리즘 설계

* 유저 ID에 대한 닉네임 정보를 최신 상태로 유지해야 하며, 메시지를 출력할 때는 항상 가장 최근 닉네임을 사용해야 한다.
* 입장(`Enter`) 또는 퇴장(`Leave`) 기록은 순서를 보존하며 출력되어야 한다.
* 닉네임 변경(`Change`)은 메시지를 출력하지 않고, 닉네임만 갱신한다.

1. 닉네임 정보를 저장할 맵(`Map`)을 캡슐화한 `Repository` 클래스를 사용한다.
    * `userId` → `User` 객체를 저장하여, 닉네임 변경 시 객체 내부 상태를 수정할 수 있도록 한다.
2. 입력으로 주어지는 `record` 배열을 한 번 순회하며 다음을 처리한다.
    * `Enter`: 닉네임을 등록하거나 변경하고, 출력 메시지를 위한 로그에 기록한다.
    * `Leave`: 메시지 로그에만 기록한다.
    * `Change`: 닉네임만 변경하고 메시지는 남기지 않는다.
3. 메시지 로그(`History`)를 순회하며, 각 명령어에 따른 메시지를 생성한다.
    * 메시지는 항상 현재 닉네임을 기준으로 출력된다.

---

## 🌵 코드

```java
 public class 오픈채팅방 {
    public String[] solution(String[] record) {

        Repository repository = new Repository();
        List<History> result = new ArrayList<>();

        for (String s : record) {
            String[] parsed = s.split(" ");
            String command = parsed[0];
            String userId = parsed[1];

            if (command.equals(Command.ENTER.getCommand())) {
                String nickName = parsed[2];
                repository.add(new User(userId, nickName));
                result.add(new History(userId, Command.ENTER));
            } else if (command.equals(Command.LEAVE.getCommand())) {
                result.add(new History(userId, Command.LEAVE));
            } else if (command.equals(Command.CHANGE.getCommand())) {
                String newNickName = parsed[2];
                repository.changeNickName(userId, newNickName);
            }
        }

        return result.stream()
                .map(h -> repository.getUser(h.userId).getNickName() + h.command.getMessage())
                .toArray(String[]::new);
    }

    static class User {

        private String userId;
        private String nickName;

        public User(String userId, String nickName) {
            this.userId = userId;
            this.nickName = nickName;
        }

        public void changeNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public String getNickName() {
            return nickName;
        }
    }

    enum Command {
        CHANGE("Change", ""),
        ENTER("Enter", "님이 들어왔습니다."),
        LEAVE("Leave", "님이 나갔습니다.");

        private final String command;
        private final String message;

        Command(String command, String message) {
            this.command = command;
            this.message = message;
        }

        public String getCommand() {
            return command;
        }

        public String getMessage() {
            return message;
        }
    }

    static class History {
        String userId;
        Command command;

        public History(String userId, Command command) {
            this.userId = userId;
            this.command = command;
        }
    }

    static class Repository {

        private final Map<String, User> store = new HashMap<>();

        public void add(User user) {
            store.put(user.getUserId(), user);
        }

        public void changeNickName(String userId, String nickName) {
            User user = store.get(userId);
            user.changeNickName(nickName);
        }

        public User getUser(String userId) {
            return store.get(userId);
        }
    }
}
```