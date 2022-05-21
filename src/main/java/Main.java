import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        String filename = "data/test.txt"; //TODO ARGS
        int rounds = 100;
        int workers = 8;
        try {
            Board board = Board.loadBoard(filename);
            Board.showBoard(board);
            for (int i =0;i < rounds;i++){

                //TODO divide to workers
                //board.setData(data);
                board.nextRound();
                Board.showBoard(board);
                Thread.sleep(1000);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}