package leetcode;

/**
 * back-tracking
 * Created by zhengxiao on 15/11/2016.
 */
public class NQueen {

    int n;

    public NQueen(int n) {
        this.n = n;
    }

    void printBoard(boolean[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]) {
                    System.out.print("Q\t");
                } else {
                    System.out.print("_\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen(8);
        boolean[][] board = new boolean[10][10];
        if (!nQueen.theBoardSolver(board, 0)) {
            System.out.println("no Solution");
        }
        nQueen.printBoard(board);
    }

    boolean isValidPlace(boolean[][] board, int row, int col) {
        // 左方
        for (int i = 0; i < col; i++) {
            if (board[row][i]) {
                return false;
            }
        }

        // 左上方
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // 左下方
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j]){
                return false;
            }
        }

        return true;
    }


    boolean theBoardSolver(boolean[][] board, int col) {
        if (col >= n){
            // going out, done
            return true;
        }
        for (int i = 0; i < n; i++){
            if (isValidPlace(board, i, col)){
                board[i][col] = true;
                if (theBoardSolver(board, col + 1)){
                    return true;
                }
                // backtrack
                board[i][col] = false;
            }
        }

        return false;
    }

}
