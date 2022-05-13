import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Board {

    private int x,y;
    private int[][] data;
    private int Round = 0;

    public Board(int x, int y, int[][]data){
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int[][] getData(){
        return this.data;
    }

    public int getRound() {
        return this.Round;
    }
    public void nextRound(){
        this.Round++;
    }

    public static Board loadBoard(String filename) throws FileNotFoundException {

        int x,y;
        File f = new File(filename);
        Scanner scanner = new Scanner(f).useLocale(Locale.ENGLISH);
        x = scanner.nextInt();
        y = scanner.nextInt();
        int[][] data = new int[x][y];
        for (int r = 0; r < y; r++) {
            for (int c = 0; c < x; c++) {
                data[c][r] = scanner.nextInt();
            }
        }
        return new Board(x,y,data);
    }

    public static void showBoard(Board board){
        Utils.clearScreen();
        System.out.println("Round:" + board.getRound());
        System.out.println();
        for(int y = 0; y < board.getY();y++){
            System.out.print("  ");
            for(int x = 0;x < board.getX();x++){
                System.out.print(board.getData()[x][y] + " ");
            }
            System.out.println();
        }
    }
}
