import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connect4 game = new Connect4();
        AIPlayer aiPlayer = new AIPlayer('O');

        System.out.println("Connect 4 játék indítása!");
        game.printBoard();

        while (true) {

            System.out.println("Játékos (X) lépése, válassz egy oszlopot (0-6): ");
            int column = scanner.nextInt();
            if (!game.makeMove(column, 'X')) {
                System.out.println("Érvénytelen lépés, próbáld újra!");
                continue;
            }
            game.printBoard();


            if (game.checkWin('X')) {
                System.out.println("Gratulálunk! Az emberi játékos nyert!");
                break;
            }


            if (game.isBoardFull()) {
                System.out.println("A tábla megtelt! Döntetlen!");
                break;
            }

            System.out.println("AI (O) lépése...");
            int aiColumn = aiPlayer.chooseColumn(game);
            game.makeMove(aiColumn, 'O');
            game.printBoard();


            if (game.checkWin('O')) {
                System.out.println("Az AI nyert!");
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("A tábla megtelt! Döntetlen!");
                break;
            }
        }

        scanner.close();
    }
}
