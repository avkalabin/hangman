import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int HANGMAN_SIZE = 6;
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
                int fail = 0;
                var usedLetters = new ArrayList<Character>();
                while (fail < HANGMAN_SIZE) {
                    System.out.println("Введите букву: ");
                    char letter = scanner.next().charAt(0);
                    if (word.contains(String.valueOf(letter)) && !usedLetters.contains(letter)) {
                        for (int i = 0; i < word.length(); i++) {
                            if (word.charAt(i) == letter) {
                                maskedWord.replace(i, i, String.valueOf(letter));
                            }
                            }
                        } else {
                        fail++;
                    }
                    System.out.println(maskedWord);
                    usedLetters.add(letter);
                    System.out.println("Использованы буквы: " + usedLetters);
                }
            }
        }
    }
}
