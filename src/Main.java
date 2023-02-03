import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите текст:");
        String input = in.nextLine();
        input = deleteExcessChars(input);
        String[] splitInput = splitString(input);
        System.out.println("В тексте " + splitInput.length + " слов");
        printTop10Words(splitInput);
    }

    public static String deleteExcessChars(String line) {
        line = line.toLowerCase();
        String[] symbols = new String[]{"!", "?", ",", ".", "(", ")", ":", ";", "%", "+", "-", "=","«","»","0","1","2","3","4","5","6","7","8","9", "\""};
        for (String symbol : symbols) {
            line = line.replace(symbol, "");
        }
        line = line.replaceAll("\\s+", " ");
        return line;
    }

    public static String[] splitString(String line) {
        return line.split(" ");
    }

    public static void printTop10Words(String[] words) {
        Map<String, Integer> countedWords = new LinkedHashMap<>();
        for (String word : words) {
            if (countedWords.containsKey(word)) {
                countedWords.put(word, countedWords.get(word) + 1);
            } else {
                countedWords.put(word, 1);
            }
        }
        Map<String, Integer> top10Words = countedWords
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("TOP 10 слов:");
        for (Map.Entry<String, Integer> entry : top10Words.entrySet()) {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }
    }
}