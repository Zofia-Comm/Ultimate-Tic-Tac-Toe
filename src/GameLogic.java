public class GameLogic {
    private static int size = 3;
    private boolean boardWinner(Boolean[][] board) {
        // sprawdź poziomo
        for (int row = 0; row < size; row++) {
            if (board[row][0] != null && board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
                return true;
            }
        }
        // sprawdź pionowo
        for (int col = 0; col < size; col++) {
            if (board[0][col] != null && board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
                return true;
            }
        }

        // sprawdź przekątnie
        return (board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2])
                || (board[0][2] != null && board[0][2] == board[1][1] && board[0][2] == board[2][0]);
    }
}