import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean playing = true;
        boolean player1 = true;

        String player1_symbol = "○";
        String player2_symbol = "●";

        String[][] board = new String[9][9];
        String[][] goBoard = {
            {null, null, "-○", "-○", null, null, null, null, null},
            {null, "-○", "-●", "-●", "-○", null, null, null, null},
            {null, "-○", "-●", null, "-●", "-○", null, null, null},
            {null, "-○", "-●", "-●", "-●", "-○", null, null, null},
            {null, "-○", "-●", null, "-●", "-○", null, null, null},
            {null, null, "-○", "-●", "-●", "-○", null, null, null},
            {null, null, null, "-○", "-○", null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null}
        };

        boolean[][] lives = new boolean[9][9];
        boolean[][] territory = new boolean[9][9];
        boolean[][] beenChecked = new boolean[9][9];

        for (int i = 0; i < goBoard.length; i++) {
            for (int j = 0; j < goBoard[i].length; j++) {
                if(goBoard[i][j] != null) {
                    lives[i][j] = true;
                }else {
                    lives[i][j] = false;
                }
                territory[i][j] = false;

                beenChecked[i][j] = false;
            }
        }

        Scanner scn = new Scanner(System.in);

        while (playing) {
            for(int i = 0; i < goBoard.length; i++) {
                for(int j = 0; j < goBoard[i].length; j++) {
                    if(goBoard[i][j] == null) {
                        System.out.print("+ ");
                    }else {
                        System.out.print(goBoard[i][j] + " ");
                    }
                }
                System.out.println();
            }

            System.out.printf("%s, enter x coord: ", (player1 ? "Player 1" : "Player 2"));
            int moveX = scn.nextInt();

            System.out.printf("%s, enter y coord: ", (player1 ? "Player 1" : "Player 2"));
            int moveY = scn.nextInt();

            if(moveX >= 9 || moveY >= 9 || moveX < 0 || moveY < 0) {
                System.out.println("those coords are not valid.\n");
                continue;
            }

            int boardY = Math.abs(moveY - (goBoard.length - 1));

            if(goBoard[boardY][moveX] == null) {
                goBoard[boardY][moveX] = player1 ? player1_symbol : player2_symbol;
                lives[boardY][moveX] = true;
                player1 = !player1;
            }else {
                System.out.println("that position is occupied already.\n");
            }
        }
        scn.close();
    }
}