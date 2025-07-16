package programmers.level2;

public class JadenCase_문자열_만들기 {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        boolean isFirst = true;

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                isFirst = true;
            } else {
                if (isFirst) {
                    sb.append(String.valueOf(c).toUpperCase());
                    isFirst = false;
                } else {
                    sb.append(String.valueOf(c).toLowerCase());
                }
            }
        }

        return sb.toString();
    }
}
