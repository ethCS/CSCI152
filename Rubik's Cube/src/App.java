import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        RubiksCube cube = new RubiksCube();
        Scanner scn = new Scanner(System.in);
        int argsIndex = 0;

        try {
            while (true) {
                String move = "";
                if (args.length > argsIndex) {
                    move = args[argsIndex];
                    argsIndex++;
                } else {
                    break;
                }
                move = move.trim().toLowerCase();
                cube.move(move);
            }

            cube.print();
            boolean hasRNG = false;
            for (String arg : args) {
                if (arg.trim().equalsIgnoreCase("rng")) {
                    hasRNG = true;
                    System.out.println("Your cube has been scrambled!!!!!");
                    break;
                }
            }

            if (!hasRNG && args.length > 0) {
                System.out.println("Undo solution:");
                for (int i = args.length - 1; i >= 0; i--) {
                    String move = args[i].trim().toLowerCase();
                    String invertedMove = move.contains("'") ? move.replace("'", "") : move + "'";
                    System.out.print(invertedMove + " ");
                }
                System.out.println();
            }

        } catch(Exception exception) {
            System.out.println("Invalid input, so you have received the error type: " + exception.getMessage() + ".");
            System.out.println("LUTHER: I HAVE THIS PROJECT SETUP SO THE CLI NEEDS TO HAVE BACKSLASH FOR PRIME INPUTS.");
            System.out.println("Instead use: (u, d, r, l, f, b) or ensure backslash: (u\\', d\\', r\\', l\\', f\\', b\\') ");
        } finally {
            scn.close();
        }
    }
}