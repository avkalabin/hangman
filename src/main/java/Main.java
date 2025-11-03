import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static final int ATTEMPT_COUNT = 6;
    private static final String RUSSIAN_LOWERCASE_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) throws IOException {
        final List<String> wordsList = Files.readAllLines(Path.of("src/main/resources/dictionary.txt"));
        System.out.println(wordsList.size() + " слов");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("[Н]овая игра или [В]ыход?");
                final Character command = scanner.next().charAt(0);
                if ("в".equalsIgnoreCase(String.valueOf(command))) {
                    System.out.println("Exit...");
                    break;
                } else if ("н".equalsIgnoreCase(String.valueOf(command))) {
                    playRound(wordsList, scanner);
                }
            }
        }
    }

    private static void playRound(List<String> wordsList, Scanner scanner) {
        final String hiddenWord = chooseRandomWord(wordsList);
        StringBuilder maskedWord = maskWord(hiddenWord);
        int attemptsLeft = ATTEMPT_COUNT;
        Set<Character> usedLetters = new HashSet<>();

        Hangman.drawHangman(attemptsLeft);
        System.out.println(maskedWord);

        while (true) {
            System.out.println("Введите букву: ");
            final char letter = Character.toLowerCase(scanner.next().charAt(0));
            if (!isValidLetter(letter)) {
                System.out.println("Должна быть введена русская буква");
                continue;
            }
            if (usedLetters.contains(letter)) {
                System.out.println("Это букву уже вводили");
                continue;
            }
            if (hiddenWord.contains(String.valueOf(letter))) {
                updateMaskedWord(hiddenWord, letter, maskedWord);
            } else {
                attemptsLeft--;
                System.out.println("Такой буквы нет");
                if (attemptsLeft > 0) {
                    System.out.println("У вас осталось " + attemptsLeft + " попыток");
                } else {
                    Hangman.drawHangman(attemptsLeft);
                    System.out.println("Вы ПРОИГРАЛИ");
                    System.out.println("Загаданное слово было: " + hiddenWord);
                    break;
                }
            }

            Hangman.drawHangman(attemptsLeft);
            System.out.println(maskedWord);
            usedLetters.add(letter);
            System.out.println("Использованы буквы: " + usedLetters + "\n");
            if (!maskedWord.toString().contains("*")) {
                System.out.println("ПОБЕДА!!!");
                break;
            }
        }
    }

    private static void updateMaskedWord(String hiddenWord, char letter, StringBuilder maskedWord) {
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == letter) {
                maskedWord.setCharAt(i, letter);
            }
        }
    }

    private static String chooseRandomWord(List<String> words) {
        return words.get(new Random().nextInt(words.size())).toLowerCase();
    }

    private static StringBuilder maskWord(String word) {
        return new StringBuilder("*".repeat(word.length()));
    }

    private static boolean isValidLetter(char letter) {
        return RUSSIAN_LOWERCASE_LETTERS.contains(String.valueOf(letter));
    }
}