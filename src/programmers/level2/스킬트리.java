package programmers.level2;

public class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {

        int result = 0;

        for (String skillTree : skill_trees) {
            skillTree = skillTree.replaceAll("[^" + skill + "]", "");
            char[] charArray = skillTree.toCharArray();

            boolean isValid = true;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] != skill.charAt(i)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                result++;
            }
        }

        return result;
    }
}
