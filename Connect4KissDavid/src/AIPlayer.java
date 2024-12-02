import java.util.Random;

public class AIPlayer {
    private char symbol;
    private Random random;

    public AIPlayer(char symbol) {
        this.symbol = symbol;
        this.random = new Random();
    }

    public char getSymbol() {
        return symbol;
    }

    public int chooseColumn(Connect4 game) {
        int column;
        do {
            column = random.nextInt(Connect4.COLUMNS);
        } while (!game.isValidMove(column));
        return column;
    }
}
