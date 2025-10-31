import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("[N]ew game or [E]xit?");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if ("e".equalsIgnoreCase(input)) {
                System.out.println("Exit...");
                break;
            } else if ("n".equalsIgnoreCase(input)) {
                System.out.println("Start");

            }
        }
    }
}
