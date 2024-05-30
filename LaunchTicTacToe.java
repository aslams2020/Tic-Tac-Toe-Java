import java.util.*;
// import java.util.Random();

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

    static boolean checkDraw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
}

abstract class Player {
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }
}

class HumanPlayer extends Player {

    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
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

class AIPlayer extends Player {

    AIPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        int row, col;
        do {
            Random r = new Random();
            row = r.nextInt(3); // 0,1,2
            col = r.nextInt(3);
        } while (!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);
    }
}

public class LaunchTicTacToe {
    public static void main(String[] args) {
        System.out.printf("\nWELCOME TO TIC TAC TOE! \n\n");
        TicTacToe t = new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("Aslam", 'X');
        AIPlayer p2 = new AIPlayer("player2", 'O');

        Player currP;
        currP = p1;

        while (true) {
            System.out.println(currP.name + "'s Turn");
            currP.makeMove();
            TicTacToe.displayBoard();
            if (TicTacToe.checkRowWin() || TicTacToe.checkColWin()
                    || TicTacToe.checkDiagWin()) {
                System.out.println(currP.name + " Won! ");
                break;
            } else if (TicTacToe.checkDraw()) {
                System.out.println("The Game is DRAW :)");
                break;
            } else {
                if (currP == p1)
                    currP = p2;
                else
                    currP = p1;
            }
        }

        System.out.println("Thank You For Playing. ^_^");
    }
}