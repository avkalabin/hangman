import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final List<String> wordsList = Files.readAllLines(Path.of("src/main/resources/dictionary.txt"));
        System.out.println(wordsList.size() + " слов");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[N]ew game or [E]xit?");
            final Character newGameOrExit = scanner.next().charAt(0);
            if ("e".equalsIgnoreCase(String.valueOf(newGameOrExit))) {
                System.out.println("Exit...");
                break;
            } else if ("n".equalsIgnoreCase(String.valueOf(newGameOrExit))) {
                playRound(wordsList, scanner);
            }
        }
    }

    private static void playRound(List<String> wordsList, Scanner scanner) {
        final String RUSSIAN_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Random random = new Random();
        final int randomIndex = random.nextInt(wordsList.size());
        final var hiddenWord = wordsList.get(randomIndex).toLowerCase();
        StringBuilder maskedWord = new StringBuilder();
        int attemptCount = 6;
        maskedWord.append("*".repeat(hiddenWord.length()));
        Hangman.drawHangman(attemptCount);
        System.out.println(maskedWord);
        var usedLetters = new HashSet<Character>();
        while (true) {
            System.out.println("Введите букву: ");
            final char letter = Character.toLowerCase(scanner.next().charAt(0));
            if (!RUSSIAN_LETTERS.contains(String.valueOf(letter))) {
                System.out.println("Должна быть введена русская буква");
                continue;
            }
            if (usedLetters.contains(letter)) {
                System.out.println("Это букву уже вводили");
                continue;
            }
            if (hiddenWord.contains(String.valueOf(letter))) {
                for (int i = 0; i < hiddenWord.length(); i++) {
                    if (hiddenWord.charAt(i) == letter) {
                        maskedWord.setCharAt(i, letter);
                    }
                }
            } else {
                attemptCount--;
                System.out.println("Такой буквы нет");
                if (attemptCount > 0) {
                    System.out.println("У вас осталось " + attemptCount + " попыток");
                } else {
                    Hangman.drawHangman(attemptCount);
                    System.out.println("Вы ПРОИГРАЛИ");
                    System.out.println("Загаданное слово было: " + hiddenWord);
                    break;
                }
            }
            Hangman.drawHangman(attemptCount);
            System.out.println(maskedWord);
            usedLetters.add(letter);
            System.out.println("Использованы буквы: " + usedLetters + "\n");
            if (!maskedWord.toString().contains("*")) {
                System.out.println("ПОБЕДА!!!");
                break;
            }
        }
    }
}