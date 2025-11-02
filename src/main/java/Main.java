import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final List<String> wordsList = Files.readAllLines(Path.of("src/main/resources/dictionary.txt"));
        final String LOWERCASE_RUSSIAN_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        System.out.println(wordsList.size() + " слов");
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[N]ew game or [E]xit?");
            final Character newGameOrExit = scanner.next().charAt(0);
            if ("e".equalsIgnoreCase(String.valueOf(newGameOrExit))) {
                System.out.println("Exit...");
                break;
            } else if ("n".equalsIgnoreCase(String.valueOf(newGameOrExit))) {
                final int randomIndex = random.nextInt(wordsList.size());
                final var hiddenWord = wordsList.get(randomIndex).toLowerCase();
                StringBuilder maskedWord = new StringBuilder();
                int attemptCount = 6;
                maskedWord.append("*".repeat(hiddenWord.length()));
                drawHangman(attemptCount);
                System.out.println(maskedWord);
                var usedLetters = new HashSet<Character>();
                while (attemptCount > 0) {
                    System.out.println("Введите букву: ");
                    final char letter = Character.toLowerCase(scanner.next().charAt(0));
                    if (!LOWERCASE_RUSSIAN_LETTERS.contains(String.valueOf(letter))) {
                        continue;
                    }
                    if (hiddenWord.contains(String.valueOf(letter)) && !usedLetters.contains(letter)) {
                        for (int i = 0; i < hiddenWord.length(); i++) {
                            if (hiddenWord.charAt(i) == letter) {
                                maskedWord.replace(i, i + 1, String.valueOf(letter));
                            }
                        }
                    } else if (!hiddenWord.contains(String.valueOf(letter)) && !usedLetters.contains(letter)) {
                        attemptCount--;
                        System.out.println("Такой буквы нет");
                        if (attemptCount > 0) {
                            System.out.println("У вас осталось " + attemptCount + " попыток");
                        } else {
                            System.out.println("Вы ПРОИГРАЛИ");
                            System.out.println("Загаданное слово было: " + hiddenWord);
                            drawHangman(attemptCount);
                            break;
                        }
                    }
                    drawHangman(attemptCount);
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
    }

    private static void drawHangman(int attempt) {
        switch (attempt) {
            case 6 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 5 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 4 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 3 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 2 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 1 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 0 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                System.out.println("      |");
                System.out.println("=========");
            }
        }
    }

}