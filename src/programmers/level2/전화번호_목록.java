package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;

public class 전화번호_목록 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, Comparator.naturalOrder());

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}