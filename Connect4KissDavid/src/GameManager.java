import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) {
        Connect4 game = new Connect4();
        AIPlayer aiPlayer = new AIPlayer('O');
        Scanner scanner = new Scanner(System.in);

        System.out.println("Üdvözöllek a Connect4 játékban!");
        game.printBoard();

        while (!game.isBoardFull()) {
            if (game.getCurrentPlayer() == 'X') {
                System.out.print("Add meg az oszlop számát (0-tól 6-ig): ");
                int column = scanner.nextInt();
                if (game.makeMove(column, 'X')) {
                    if (game.checkWin('X')) {
                        game.printBoard();
                        System.out.println("Gratulálok! A játékos X nyert!");
                        return;
                    }
                    game.setCurrentPlayer('O');
                } else {
                    System.out.println("Érvénytelen lépés. Próbáld újra!");
                }
            } else {
                int aiMove = aiPlayer.chooseColumn(game);
                System.out.println("AI választotta az oszlopot: " + aiMove);
                game.makeMove(aiMove, 'O');
                if (game.checkWin('O')) {
                    game.printBoard();
                    System.out.println("A gép nyert! Jobb szerencsét legközelebb!");
                    return;
                }
                game.setCurrentPlayer('X');
            }
            game.printBoard();
        }

        System.out.println("Döntetlen! A tábla megtelt.");
    }
}
