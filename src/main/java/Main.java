import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    private static int rounds = 1000;
    private static int size = 10;
    private static int numProcesses = 4;
    private static String fileName = null;
    private static WorkersService service;
    private static Board board;


    public static void main(String[] args){
        validateArguments(args);

        try {
            if (fileName != null) {
                board = Board.loadBoard(fileName);
            }
            else {
                board = Board.createBoard(size);
            }

            service = new WorkersService(board, numProcesses);
            service.divideBoard("byRows");

            long time = System.currentTimeMillis();

            for (int i = 0; i < rounds; i++) {
                //Board.showBoard(board);
                service.prepareWorkers();
                service.startWorkers();
                int[][] newBoard = service.getNextRoundBoard();
                board = new Board(board.getX(), board.getY(),newBoard,board.getRound() + 1);
                //board.setData(newBoard);
                //board.nextRound();
                //Thread.sleep(1000);
            }

            System.out.println("Time: " + (System.currentTimeMillis() - time));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static void validateArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "-f":
                case "--filename":
                    fileName = args[++i];
                    break;
                case "-s":
                case "--size":
                    size = Integer.parseInt(args[++i]);
                    break;
                case "-p":
                case "--processes":
                    numProcesses = Integer.parseInt(args[++i]);
                    break;
                default:
                    System.out.println("ERROR ARGUMENTS");
            }
        }
    }
}