public class Worker extends Thread {

    private final Board board;
    private final int[] rowsRange;
    private final int[] colsRange;
    private final int[][] workerResult;

    public Worker(Board board, int[] rowsRange, int[] colsRange) {
        this.board = board;
        this.rowsRange = rowsRange;
        this.colsRange = colsRange;
        this.workerResult = new int[board.getX()][board.getY()];
    }

    public void run() {
        calculate();
    }

    private void calculate() {
        int neighbours, cell;
        for (int x = rowsRange[0]; x < rowsRange[1]+1; x++) {
            for (int y = colsRange[0]; y < colsRange[1]+1; y++) {
                neighbours = board.getActiveNeighbours(x,y);
                cell = board.getData()[x][y];
                if(cell == 1){
                    if(neighbours < 2 || neighbours > 3) {
                        workerResult[x][y] = 0;
                    }else{
                        workerResult[x][y] = 1;
                    }
                }else{
                    if(neighbours == 3){
                        workerResult[x][y] = 1;
                    }else{
                        workerResult[x][y] = 0;
                    }
                }
            }
        }
    }

    public int[][] getWorkerResult() {
        return this.workerResult;
    }

    public int[] getRowsRange() {
        return this.rowsRange;
    }

    public int[] getColsRange() {
        return this.colsRange;
    }
}
