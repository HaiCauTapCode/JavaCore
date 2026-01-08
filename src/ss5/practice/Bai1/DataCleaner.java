package ss5.practice.Bai1;

public class DataCleaner {

    public static String formatName(String name) {
        name = name.trim();

        String[] parts = name.split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String word = parts[i].toLowerCase();

            char firstChar = Character.toUpperCase(word.charAt(0));
            String rest = word.substring(1);

            sb.append(firstChar).append(rest);

            if (i < parts.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static String generateID(String name, int index) {
        String formatted = formatName(name);
        String[] parts = formatted.split(" ");

        StringBuilder id = new StringBuilder();

        for (String part : parts) {
            id.append(part.charAt(0));
        }

        id.append(index);

        return id.toString();
    }
}

