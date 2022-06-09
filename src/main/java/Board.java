import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public final class Board {

    private final int x, y;
    private int[][] data;
    private int Round = 0;

    public Board(int x, int y, int[][] data) {
        this.x = x;
        this.y = y;
        this.data = data;
        Round = 0;
    }
    public Board(int x, int y, int[][] data, int round) {
        this.x = x;
        this.y = y;
        this.data = data;
        Round = round;
    }


    public void setData(int[][] data) {
        this.data = data;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int[][] getData() {
        return this.data;
    }

    public int getRound() {
        return this.Round;
    }

    public void nextRound() {
        this.Round++;
    }

    public static Board loadBoard(String filename) throws FileNotFoundException {

        int x, y;
        File f = new File(filename);
        Scanner scanner = new Scanner(f).useLocale(Locale.ENGLISH);
        x = scanner.nextInt();
        y = x;
        int[][] data = new int[x][y];
        for (int r = 0; r < x; r++) {
            for (int c = 0; c < y; c++) {
                data[c][r] = scanner.nextInt();
            }
        }
        return new Board(x, y, data);
    }

    public static Board createBoard(int size) {
        int [][] data = new int[size][size];

        for (int r = 0;  r < size; r++) {
            for (int c=0; c < size; c++) {
                data[r][c] = (int) Math.round(Math.random());
            }
        }

        return new Board(size, size, data);
    }

    public static void showBoard(Board board) {
        System.out.println("Round: " + board.getRound());
        System.out.println();
        for (int x = 0; x < board.getX(); x++) {
            System.out.print("  ");
            for (int y = 0; y < board.getY(); y++) {
                System.out.print(board.getData()[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getActiveNeighbours(int x, int y) {

        int upperRow = x == 0 ? this.getX() - 1 : x - 1;
        int lowerRow = x == this.getX() - 1 ? 0 : x + 1;
        int leftCol = y == 0 ? this.getY() - 1 : y - 1;
        int rightCol = y == this.getY() - 1 ? 0 : y + 1;
        return this.getData()[upperRow][leftCol] + this.getData()[upperRow][y] + this.getData()[upperRow][rightCol] +
                this.getData()[x][leftCol] + this.getData()[x][rightCol] +
                this.getData()[lowerRow][leftCol] + this.getData()[lowerRow][y] + this.getData()[lowerRow][rightCol];
    }
}
