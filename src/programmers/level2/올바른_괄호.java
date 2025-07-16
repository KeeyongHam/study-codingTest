package programmers.level2;

public class 올바른_괄호 {
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
}
