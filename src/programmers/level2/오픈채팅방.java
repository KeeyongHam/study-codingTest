package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        LEAVE("Leave","님이 나갔습니다.");

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
