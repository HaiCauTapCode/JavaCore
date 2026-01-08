package ss5.exercise;

public class Util {
    public static String formatName(String name) {
        if (name == null || name.isBlank()) {
            return "";
        }

        String[] parts = name.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String word = parts[i].toLowerCase();

            if (word.isEmpty()) {
                continue;
            }

            char firstChar = Character.toUpperCase(word.charAt(0));
            String rest = word.substring(1);

            sb.append(firstChar).append(rest);

            if (i < parts.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
