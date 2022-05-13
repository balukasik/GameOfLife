public class Worker extends Thread {

    public void run(Board board) {
        calculate(board);
    }

    private void calculate(Board board) {
        int data[][] = new int[board.getX()][board.getY()];
        int neighbours, cell;
        for (int x = 0; x < board.getX(); x++) {
            for (int y = 0; y < board.getY(); y++) {
                neighbours = board.getActiveNeighbours(x,y);
                cell = board.getData()[x][y];
                if(cell == 1){
                    if(neighbours <2 || neighbours > 4) {
                        data[x][y] = 0;
                    }else{
                        data[x][y] = 1;
                    }
                }else{
                    if(neighbours == 3){
                        data[x][y] = 1;
                    }else{
                        data[x][y] = 0;
                    }
                }
            }
        }
        board.setData(data);
    }
}
