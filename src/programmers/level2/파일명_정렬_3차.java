package programmers.level2;

import java.util.Arrays;

public class 파일명_정렬_3차 {
    public String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(File::new)
                .sorted()
                .map(File::getOriginal)
                .toArray(String[]::new);
    }

    static class File implements Comparable<File> {

        private String original;
        private String head;
        private int number;

        public File(String strFile) {
            original = strFile;
            head = strFile.split("[0-9]")[0].toLowerCase();
            number = extractNumber(strFile);
        }

        private int extractNumber(String strFile) {
            StringBuilder sb = new StringBuilder();

            boolean flag = false;
            for (char c : strFile.toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                    flag = true;
                } else if (flag) {
                    break;
                }
            }

            return Integer.parseInt(sb.toString());
        }

        public String getOriginal() {
            return original;
        }

        public String getHead() {
            return head;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public int compareTo(File o) {
            int headCompare = head.compareTo(o.getHead());

            if (headCompare != 0) {
                return headCompare;
            }

            return Integer.compare(number, o.getNumber());
        }
    }
}
