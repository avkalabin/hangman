import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> words = Files.readAllLines(Path.of("src/main/resources/dictionary.txt"));
        System.out.println(words.size() + " слов");
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[N]ew game or [E]xit?");
            Character newGameOrExit = scanner.next().charAt(0);
            if ("e".equalsIgnoreCase(String.valueOf(newGameOrExit))) {
                System.out.println("Exit...");
                break;
            } else if ("n".equalsIgnoreCase(String.valueOf(newGameOrExit))) {
                int randomIndex = random.nextInt(words.size());
                var word = words.get(randomIndex);
                System.out.println(word);
                StringBuilder maskedWord = new StringBuilder();
                maskedWord.append("*".repeat(word.length()));
                System.out.println(maskedWord);
                int attempt = 6;
                var usedLetters = new HashSet<Character>();
                while (attempt > 0) {
                    System.out.println("Введите букву: ");
                    char letter = scanner.next().charAt(0);

                    if (word.contains(String.valueOf(letter)) && !usedLetters.contains(letter)) {
                        for (int i = 0; i < word.length(); i++) {
                            if (word.charAt(i) == letter) {
                                maskedWord.replace(i, i + 1, String.valueOf(letter));
                            }
                        }
                    } else if (!word.contains(String.valueOf(letter))) {
                        attempt--;
                        System.out.println("Такой буквы нет");
                        if (attempt > 0) {
                            System.out.println("У вас осталось " + attempt + " попыток");
                            draw(attempt);
                        } else {
                            System.out.println("Вы проиграли");
                            System.out.println("Загаданное слово было: " + word);
                        }
                    }
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

    private static void draw(int attempt) {
        switch (attempt) {
            case 5 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 4 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
            }
            case 3 -> {
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
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
