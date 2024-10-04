package view;

import model.*;

import java.util.Scanner;

public class View {

    private Facade facade;

    public View(Facade facade) {
        this.facade = facade;
    }

    public int readInt() {
        Scanner kbd = new Scanner(System.in);
        while (!kbd.hasNextInt()) {
            System.out.println("It's not a number, try again !");
            kbd.next();
        }
        return kbd.nextInt();
    }

    public String readText() {
        Scanner kbd = new Scanner(System.in);
        while (!kbd.hasNextLine()) {
            System.out.println("It's not a word, try again !");
            kbd.next();
        }
        return kbd.nextLine();
    }

    public int dimensionGame(String dimension) {
        if (dimension == "Height") {
            System.out.println("Write the dimension of your table");
        }
        System.out.print(dimension + " :");
        int value = readInt();
        while (value < 4) {
            System.out.println("Dimension too low, try again");
            value = readInt();
        }
        return value;
    }

    public void PaintCase() {
        int inline = 1;
        System.out.print("  ");
        for (int i = 1; i <= facade.getWidth(); i++) {
            System.out.print("▫️" + i);
        }
        System.out.print("\n");
        System.out.print("  ");
        for (int i = 1; i <= facade.getWidth(); i++) {
            System.out.print("▫️" + "—");
        }
        System.out.print("\n");
        System.out.print(1 + "|");
        int a = 1;
        for (int i = 0; i < facade.getBoard().getHeight(); i++) {
            for (int j = 0; j < facade.getBoard().getWidth(); j++) {
                System.out.printf("%3s", facade.getTableau()[i][j].getColor());
                if (inline == facade.getWidth()) {
                    System.out.print("\n");
                    if (a < facade.getHeight()) {
                        if (a < 9) {
                            System.out.printf("%1s", a + 1 + "|");
                        } else {
                            System.out.printf("%1s", a + 1);
                        }
                        a++;
                    }
                    inline = 0;
                }
                inline++;
            }
        }
    }

    public int askPosition(String axe) {
        System.out.println("————————————————————————————————————————");
        System.out.println("Write the position of " + axe + " : ");
        System.out.print(axe + " : ");
        int x = readInt();
        while (x <= 0) {
            System.out.println("It's too low try again");
            x = readInt();
        }
        System.out.println("————————————————————————————————————————");
        return x;
    }

    public void endGame() {
        System.out.println();
        System.out.println();
        System.out.println(" ---------------------------");
        System.out.println("|/    The Game is Ended     \\|");
        System.out.println(" ---------------------------");
        System.out.println();
        System.out.println("Your final Score is : " + facade.getScore());
        System.out.println();
    }

    public String askingLevelColor() {
        System.out.println("————————————————————————————————————————");
        System.out.println();
        System.out.println("Select game levels : easy, medium or hard");
        System.out.print("level : ");
        String text = readText();
        while (!text.equals("easy") && !text.equals("medium") && !text.equals("hard")) {
            System.out.println("This level doesn't exist");
            System.out.println("You must either write \'easy\' , \'medium\' or \'hard\'");
            text = readText();
        }
        return text;
    }

    public String askingPlayer() {
        System.out.println("————————————————————————————————————————");
        System.out.println();
        System.out.println("Write your choice : continue , quit , undo or redo");
        String decision = readText();
        while (!decision.equals("continue") && !decision.equals("undo") && !decision.equals("quit") && !decision.equals("redo")) {
            System.out.println("You must either write \'continue\' , \'quit\' , \'undo\' or \'redo\'");
            decision = readText();
        }
        return decision;
    }

    public void Score() {
        System.out.println("————————————————————————————————————————");
        System.out.println("        Current score : " + facade.getScore());
        System.out.println("————————————————————————————————————————");
    }

    public void sameGameTitle() {
        System.out.println();
        System.out.println(
                "  .dBBBBP  dBBBBb     dBBBBBBb  dBBBP       dBBBBb  dBBBBb     dBBBBBBb  dBBBP \n" +
                        "  BP        dBP B    dB'   dB'             dB        dBP B    dB'   dB' dB     \n" +
                        "  `BBBBb   dBP BB   dB'dB'dB' dBBP        dBBBB     dBP BB   dB'dB'dB' dBBP    \n" +
                        "     dBP  dBP  BB  dB'dB'dB' dBP         dB' BB    dBP  BB  dB'dB'dB' dBP      \n" +
                        "dBBBBP'  dBBBBBBB dB'dB'dB' dBBBBP      dBBBBBB   dBBBBBBB dB'dB'dB' dBBBBP    \n" +
                        "                                                                             ");
    }
}
