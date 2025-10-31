import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> words = Files.readAllLines(Path.of("src/main/resources/dictionary.txt"));
        System.out.println(words.size() + " слов");
        Random random = new Random();
        while (true) {
            System.out.println("[N]ew game or [E]xit?");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if ("e".equalsIgnoreCase(input)) {
                System.out.println("Exit...");
                break;
            } else if ("n".equalsIgnoreCase(input)) {
                System.out.println("Start");
                int randomIndex = random.nextInt(words.size());
                String word = words.get(randomIndex);
                System.out.println(word);

            }
        }
    }
}
