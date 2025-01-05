public class Connect4 {
    public static final int ROWS = 6; 
    public static final int COLUMNS = 7; 
    private char[][] board; 
    private char currentPlayer; 

    public Connect4() {
        board = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = ' '; 
            }
        }
        currentPlayer = 'X'; 
    }

    // Egy lépés végrehajtása egy oszlopban
    public boolean makeMove(int column, char player) {
        if (column < 0 || column >= COLUMNS) {
            return false;
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == ' ') {
                board[row][column] = player;
                return true;
            }
        }
        return false;
    }

    public char getBoardCell(int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            throw new IllegalArgumentException("Érvénytelen cella index.");
        }
        return board[row][column];
    }

    // Győzelem ellenőrzése
    public boolean checkWin(char player) {
        // Vízszintes győzelem
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == player && board[row][col + 1] == player &&
                        board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Függőleges győzelem
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board[row][col] == player && board[row + 1][col] == player &&
                        board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Átlós győzelem (balról jobbra)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player &&
                        board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Átlós győzelem (jobbról balra)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 3; col < COLUMNS; col++) {
                if (board[row][col] == player && board[row + 1][col - 1] == player &&
                        board[row + 2][col - 2] == player && board[row + 3][col - 3] == player) {
                    return true;
                }
            }
        }

        return false; 
    }


    public boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == ' ') {
                return false; 
            }
        }
        return true; 
    }


    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(char player) {
        this.currentPlayer = player;
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }
}
