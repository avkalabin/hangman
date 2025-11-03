public class Hangman {
    private static final String[][] HANGMAN_STAGES = {
            {
                    "  +---+",
                    "  |   |",
                    "  O   |",
                    " /|\\  |",
                    " / \\  |",
                    "      |",
                    "========="
            },
            {
                    "  +---+",
                    "  |   |",
                    "  O   |",
                    " /|\\  |",
                    " /    |",
                    "      |",
                    "========="
            },
            {
                    "  +---+",
                    "  |   |",
                    "  O   |",
                    " /|\\  |",
                    "      |",
                    "      |",
                    "========="
            },
            {
                    "  +---+",
                    "  |   |",
                    "  O   |",
                    " /|   |",
                    "      |",
                    "      |",
                    "========="
            },
            {
                    "  +---+",
                    "  |   |",
                    "  O   |",
                    "  |   |",
                    "      |",
                    "      |",
                    "========="
            },
            {
                    "  +---+",
                    "  |   |",
                    "  O   |",
                    "      |",
                    "      |",
                    "      |",
                    "========="
            },
            {
                    "  +---+",
                    "  |   |",
                    "      |",
                    "      |",
                    "      |",
                    "      |",
                    "========="
            }
    };

    public static void drawHangman(int attempt) {
        String[] stage = HANGMAN_STAGES[attempt];
        for (String line : stage) {
            System.out.println(line);
        }
    }

}
