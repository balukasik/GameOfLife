import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WorkersService {
    private final int threadsNum;
    private List<Worker> workers;
    private List<int[]> workersIndexRanges;
    private final Board board;
    private final int[] defaultRowsRange;
    private final int[] defaultColsRange;

    public WorkersService(Board board, int threadsNum) {
        this.board = board;
        this.threadsNum = validateThreadsNum(threadsNum);
        this.workers = new LinkedList<>();
        this.workersIndexRanges = new LinkedList<>();
        this.defaultRowsRange = new int[]{0, board.getX()-1};
        this.defaultColsRange = new int[]{0, board.getY()-1};
    }

    public void divideBoard(String option) {
        int value;

        if (option.equals("byRows")) {
            value = this.board.getX();
        } else if (option.equals("byCols")){
            value = this.board.getY();
        } else {
            value = 0;
        }

        int counter = 0;
        int index = 0;
        int[] indexes = new int[this.threadsNum];
        Arrays.fill(indexes, 0);

        while (counter < value) {
            if (index == indexes.length)
                index = 0;
            indexes[index] += 1;
            index++;
            counter++;
        }

//        System.out.println("--------------Indexes-------------:     " + Arrays.toString(indexes));

        int startIndex = 0;
        int indexesIndex = 0;
        for (int i = 0; i < value; i++) {
            indexes[indexesIndex] -= 1;

            if(indexes[indexesIndex] == 0) {
                int[] rowsRange = {startIndex, i};
//                System.out.println("------------------------------");
//                System.out.println(Arrays.toString(rowsRange) + "  " + Arrays.toString(defaultColsRange));
//                System.out.println("------------------------------");
                workersIndexRanges.add(rowsRange);
                indexesIndex++;
                startIndex = i+1;
            }
        }
    }

    public void prepareWorkers() {
        workers = new LinkedList<>();
        for (int[] range: workersIndexRanges) {
            workers.add(new Worker(this.board, range, defaultColsRange));
        }
    }

    public void startWorkers() {
        for (Worker worker : workers) worker.start();
    }

    public int[][] getNextRoundBoard() {
        int[][] newBoard = new int[this.board.getX()][this.board.getY()];

        for (Worker worker: workers) {
            try {
                worker.join();
                joinBoardPart(worker, newBoard);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return newBoard;
    }

    private boolean checkIfPerfectSquare() {
        double squareRoot = Math.sqrt(this.threadsNum);
        int intVal = (int) (squareRoot + 0.5);
        return intVal*intVal == this.threadsNum;
    }

    private void joinBoardPart(Worker worker, int[][] newBoard) {
        for (int x = worker.getRowsRange()[0]; x < worker.getRowsRange()[1]+1;  x++) {
            for (int y = worker.getColsRange()[0]; y < worker.getColsRange()[1]+1; y++) {
                newBoard[x][y] = worker.getWorkerResult()[x][y];
            }
        }
    }

    private int validateThreadsNum(int threadsNum) {
        if (threadsNum > this.board.getX() || threadsNum > this.board.getY())
            return this.board.getX();
        return threadsNum;
    }
}
