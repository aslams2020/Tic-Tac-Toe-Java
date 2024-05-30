
import java.util.Scanner;

class TicTacToe {
    // Board
    static char[][] board;

    public TicTacToe() { // Constructor to intialise at the time of instantiation.
        board = new char[3][3];
        initBoard();
    }

    void initBoard() { // to initialise board's all element = ' '
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void displayBoard() { // to display the board to the user
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void placeMark(int row, int col, char mark) { // to place 'X' and 'O'
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        }
    }

    static boolean checkColWin() { // col is fixed
        for (int j = 0; j <= 2; j++) {
            // if (board[0][j] != ' ') { // if first char is not empty
            if (board[0][j] != ' ' &&
                    board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
            // }
        }
        return false;
    }

    static boolean checkRowWin() { // row is fixed
        for (int i = 0; i <= 2; i++) {
            // if (board[i][0] != ' ') { // if first char is not empty
            if (board[i][0] != ' ' &&
                    board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            // }
        }
        return false;
    }

    static boolean checkDiagWin() {
        // if (board[0][0] != ' ') { // if first char is not empty
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        // }
        return false;
    }

}

class HumanPlayer {
    String name;
    char mark;

    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }

    void makeMove() {
        int row, col;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter row and col: ");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);
    }

}

public class Human_TicTacToe {
    public static void main(String[] args) {
        System.out.printf("\nWELCOME TO TIC TAC TOE! \n\n");
        TicTacToe t = new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("Aslam", 'X');
        HumanPlayer p2 = new HumanPlayer("player2", 'O');

        HumanPlayer currP;
        currP = p1;

        while (true) {
            System.out.println(currP.name + "'s Turn");
            currP.makeMove();
            TicTacToe.displayBoard();
            if (TicTacToe.checkRowWin() || TicTacToe.checkColWin()
                    || TicTacToe.checkDiagWin()) {
                System.out.println(currP.name + " Won! ^_^");
                break;
            } else {
                if (currP == p1)
                    currP = p2;
                else
                    currP = p1;
            }
        }

    }

}
