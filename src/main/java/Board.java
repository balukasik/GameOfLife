public class Board {

    private int x,y;
    private int[][] data;

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
}
