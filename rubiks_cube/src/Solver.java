
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Cube {

    static Stack<String> moves = new Stack<>();

    static void solve(Stack<String> moves) {
        while (!moves.empty()) {
            String move = moves.pop();
            String reverse = (move.contains("'")) ? move.substring(0, 1) : move + "'";
            System.out.print(reverse + " ");
        }
    }

    static void solve(Stack<String> moves, ArrayList<String> solution) {
        while (!moves.empty()) {
            String move = moves.pop();
            String reverse = (move.contains("'")) ? move.substring(0, 1) : move + "'";
            System.out.print(reverse + " ");
            solution.add(reverse);
        }
    }

    static void printCube() {
        System.out.println("Final state of cube");
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int argsIndex = 0;

        ArrayList<String> soln = new ArrayList<>();

        while (true) {
            String move = "";
            if (args.length > argsIndex) {
                move = args[argsIndex];
                argsIndex++;
            } else {
                printCube();
                move = scn.nextLine();
            }

            switch (move.toUpperCase()) {
                case "U":
                    moves.push(move.toUpperCase());
                    break;
                case "D":
                    moves.push(move.toUpperCase());
                    break;
                case "R":
                    moves.push(move.toUpperCase());
                    break;
                case "L":
                    moves.push(move.toUpperCase());
                    break;
                case "F":
                    moves.push(move.toUpperCase());
                    break;
                case "B":
                    moves.push(move.toUpperCase());
                    break;
                case "U'":
                    moves.push(move.toUpperCase());
                    break;
                case "D'":
                    moves.push(move.toUpperCase());
                    break;
                case "R'":
                    moves.push(move.toUpperCase());
                    break;
                case "L'":
                    moves.push(move.toUpperCase());
                    break;
                case "F'":
                    moves.push(move.toUpperCase());
                    break;
                case "B'":
                    moves.push(move.toUpperCase());
                    break;
                case "S":
                    solve(moves, soln);
                    break;
                case "Q":
                    soln.add("e");
                    break;
            }

        }

    }
}
