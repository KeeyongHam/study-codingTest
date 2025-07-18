package programmers.level2;

import java.util.HashMap;

public class 할인_행사 {
    public int solution(String[] want, int[] number, String[] discount) {

        int result = 0;
        HashMap<String, Integer> wantedMap = createWantedMap(want, number);

        for (int i = 0; i <= discount.length - 10; i++) {
            HashMap<String, Integer> discountMap = createDiscountMap(discount, i);

            if (wantedMap.equals(discountMap)) {
                result++;
            }
        }
        return result;
    }

    private HashMap<String, Integer> createWantedMap(String[] want, int[] number) {
        HashMap<String, Integer> wantedMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantedMap.put(want[i], number[i]);
        }

        return wantedMap;
    }

    private HashMap<String, Integer> createDiscountMap(String[] discount, int i) {
        HashMap<String, Integer> discountMap = new HashMap<>();

        for (int j = 0; j < 10; j++) {
            String item = discount[i + j];
            discountMap.put(item, discountMap.getOrDefault(item, 0) + 1);
        }
        return discountMap;
    }
}