import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static final int ATTEMPT_COUNT = 6;
    private static final String RUSSIAN_LOWERCASE_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private final static String START = "н";
    private final static String QUIT = "в";
    private static List<String> words;

    public static void main(String[] args) {
        Path dictionaryPath = Path.of("src/main/resources/dictionary.txt");
        try {
            words = Files.readAllLines(dictionaryPath);
        } catch (IOException e) {
            System.err.println("Ошибка: файл словаря не найден!");
            System.err.println("Ожидаемый путь: " + dictionaryPath.toAbsolutePath());
            System.exit(1);
        }
        System.out.println("Количество слов: " + words.size());
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("[Н]овая игра или [В]ыход?");
                final String input = scanner.nextLine();
                if (input.length() == 1) {
                    final Character command = input.charAt(0);
                    if (QUIT.equalsIgnoreCase(String.valueOf(command))) {
                        System.out.println("Exit...");
                        break;
                    } else if (START.equalsIgnoreCase(String.valueOf(command))) {
                        playRound(words, scanner);
                    } else {
                        System.out.println("Некорректный ввод");
                    }
                } else {
                    System.out.println("Пожалуйста, введите только один символ.");
                }
            }
        }
    }

    private static void playRound(List<String> words, Scanner scanner) {
        final String hiddenWord = chooseRandomWord(words);
        StringBuilder maskedWord = maskWord(hiddenWord);
        int attemptsLeft = ATTEMPT_COUNT;
        Set<Character> usedLetters = new TreeSet<>();

        HangmanRenderer.drawHangman(attemptsLeft);
        System.out.println(maskedWord);

        while (true) {
            System.out.println("Введите букву: ");
            final char letter = Character.toLowerCase(scanner.nextLine().trim().charAt(0));
            if (!isValidLetter(letter)) {
                System.out.println("Должна быть введена русская буква");
                continue;
            }
            if (usedLetters.contains(letter)) {
                System.out.println("Это букву уже вводили");
                continue;
            }
            if (isHiddenWordLetter(hiddenWord, letter)) {
                updateMaskedWord(hiddenWord, letter, maskedWord);
            } else {
                attemptsLeft--;
                System.out.println("Такой буквы нет");
                if (attemptsLeft > 0) {
                    System.out.println("У вас осталось " + attemptsLeft + " попыток");
                } else {
                    HangmanRenderer.drawHangman(attemptsLeft);
                    System.out.println("Вы ПРОИГРАЛИ");
                    System.out.println("Загаданное слово было: " + hiddenWord);
                    break;
                }
            }

            HangmanRenderer.drawHangman(attemptsLeft);
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

    private static boolean isHiddenWordLetter(String hiddenWord, char letter) {
        return hiddenWord.contains(String.valueOf(letter));
    }
}