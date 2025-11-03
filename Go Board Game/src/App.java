
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * my go game implementation that allows two players to play on a 9x9 board
 */
public class App {

    static boolean playing = true;
    static boolean player1 = true;

    static int player1Score = 0;
    static int player2Score = 0;

    private final static String PLAYER1_SYMBOL = "b";
    private final static String PLAYER2_SYMBOL = "w";

    static String[][] board = new String[9][9];
    static boolean[][] beenChecked = new boolean[9][9];
    static boolean[][] dead = new boolean[9][9];

    /**
     * main game loop that handles player moves and board display
     */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        try {
            while (playing) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == null) {
                            System.out.print("+ ");
                        } else {
                            System.out.print(board[i][j] + " ");
                        }
                    }
                    System.out.println();
                }

                System.out.println();
                System.out.println("(Ctrl+C to end game & print results)");
                System.out.println();
                System.out.printf("%s, enter x coord: ", (player1 ? "Player 1" : "Player 2"));
                int moveX = scn.nextInt();

                System.out.printf("%s, enter y coord: ", (player1 ? "Player 1" : "Player 2"));
                int moveY = scn.nextInt();

                if (moveX == -1 && moveY == -1) {
                    playing = false;
                }

                if (moveX >= 9 || moveY >= 9 || moveX < 0 || moveY < 0) {
                    System.out.println("those coords are not valid.\n");
                    continue;
                }

                int boardY = Math.abs(moveY - (board.length - 1));

                if (board[boardY][moveX] == null) {
                    board[boardY][moveX] = player1 ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
                    player1 = !player1;
                } else {
                    System.out.println("that position is occupied already.\n");
                }

                beenChecked = new boolean[9][9];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (!isAlive(i, j)) {
                            dead[i][j] = true;
                        }
                    }
                }

                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (dead[i][j]) {
                            board[i][j] = null;
                        }
                    }
                    System.out.println();
                }
                dead = new boolean[9][9];
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println();
            scn.close();
            scoreArea();
            System.out.println("player one score:" + player1Score);
            System.out.println("player two score:" + player2Score);
        }
    }

    /**
     * calculates final scores by counting territory and stones for each player
     */
    private static void scoreArea() {
        boolean[][] seen = new boolean[9][9];
        int blackStones = countCharacter(PLAYER1_SYMBOL);
        int whiteStones = countCharacter(PLAYER2_SYMBOL);

        int blackTerritory = 0;
        int whiteTerritory = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // we have a spot that we have never been before
                // and we have a spot that we know is territory
                if (board[i][j] == null && !seen[i][j]) {
                    Set<String> borderingColors = new HashSet<>();
                    int emptyCount = dfs(i, j, borderingColors, seen);

                    if (borderingColors.size() == 1 && borderingColors.contains(PLAYER1_SYMBOL)) {
                        blackTerritory += emptyCount;
                    } else if (borderingColors.size() == 1 && borderingColors.contains(PLAYER2_SYMBOL)) {
                        whiteTerritory += emptyCount;
                    }
                }
            }
        }

        player1Score += (blackStones + blackTerritory);
        player2Score += (whiteStones + whiteTerritory);
    }

    /**
     * performs depth-first search to count territory and identify bordering
     * stone colors
     */
    private static int dfs(int i, int j, Set<String> borderingColors, boolean[][] seen) {
        int searchCount = 0;
        if (i > 8 || i < 0 || j > 8 || j < 0) {
            return searchCount;
        }

        if (seen[i][j]) {
            return searchCount;
        }

        if (board[i][j] != null) {
            borderingColors.add(board[i][j]);
            return searchCount;
        }

        seen[i][j] = true;
        searchCount += 1;

        // right
        searchCount += dfs(i + 1, j, borderingColors, seen);
        // left
        searchCount += dfs(i - 1, j, borderingColors, seen);
        // top
        searchCount += dfs(i, j + 1, borderingColors, seen);
        // bottom
        searchCount += dfs(i, j - 1, borderingColors, seen);

        return searchCount;
    }

    /**
     * counts the number of stones of a specific color on the board
     */
    private static int countCharacter(String searchChar) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].equals(searchChar)) {
                    count += 1;
                }
            }
        }

        return count;
    }

    /**
     * checks if a stone has any adjacent empty spaces (liberties)
     */
    private static boolean canBreathe(int x, int y) {
        if (board[x][y] == null) {
            return true;
        }
        if (x < 8 && board[x + 1][y] == null) {
            return true;
        }
        if (x > 0 && board[x - 1][y] == null) {
            return true;
        }
        if (y < 8 && board[x][y + 1] == null) {
            return true;
        }
        if (y > 0 && board[x][y - 1] == null) {
            return true;
        }
        return false;
    }

    /**
     * determines if a stone group is alive by checking for liberties or
     * connections to living stones
     */
    private static boolean isAlive(int x, int y) {
        beenChecked[x][y] = true;

        if (canBreathe(x, y)) {
            return true;
        }
        if (x < 8 && board[x][y].equals(board[x + 1][y])) {
            if (!beenChecked[x + 1][y]) {
                return isAlive(x + 1, y);
            }
        }
        if (x > 0 && board[x][y].equals(board[x - 1][y])) {
            if (!beenChecked[x - 1][y]) {
                return isAlive(x - 1, y);
            }
        }
        if (y < 8 && board[x][y].equals(board[x][y + 1])) {
            if (!beenChecked[x][y + 1]) {
                return isAlive(x, y + 1);
            }
        }
        if (y > 0 && board[x][y].equals(board[x][y - 1])) {
            if (!beenChecked[x][y - 1]) {
                return isAlive(x, y - 1);
            }
        }
        return false;
    }
}
