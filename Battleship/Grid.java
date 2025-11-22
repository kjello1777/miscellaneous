public class Grid
{
    private Location[][] grid;
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    public Grid(){
        grid = new Location[NUM_ROWS][NUM_COLS];
        for (int i = 0; i<NUM_ROWS;i++){
            for (int j = 0; j<NUM_COLS; j++){
                grid[i][j]=new Location();
            }
        }
    }
    public void markHit(int row, int col){
        grid[row][col].setStatus(1);
        grid[row][col].markHit();
    }
    public void markMiss(int row, int col){
        grid[row][col].setStatus(2);
        grid[row][col].markMiss();
    }
    public int getStatus(int row, int col){
        return grid[row][col].getStatus();
    }
    public boolean alreadyGuessed(int row, int col){
        return (grid[row][col].getStatus()==0)? false:true;
    }
    public void setShip(int row, int col, boolean val){
        grid[row][col].setShip(val);
    }
    public boolean hasShip(int row, int col){
        return grid[row][col].hasShip();
    }
    public Location get(int row, int col){
        return grid[row][col];
    }
    public int numRows(){
        return NUM_ROWS;
    }
    public int numCols(){
        return NUM_COLS;
    }
    public void addShip(Ship s){
        int x = s.getRow();
        int y = s.getCol();
        if (s.getDirection()==0){
            for (int i = y; i<y+s.getLength(); i++){
                grid[x][i].setShip(true);
            }
        }
        if (s.getDirection()==1){
            for (int i = x; i<x+s.getLength(); i++){
                grid[i][y].setShip(true);
            }
        }
    }
    public void printStatus(){
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char chc = 'A';
        for (int i = 0; i<NUM_ROWS; i++){
            System.out.print(chc);
            for (int j = 0; j<NUM_COLS; j++){
            
                int guessed = grid[i][j].getStatus();
                boolean ships = grid[i][j].hasShip();
                if (guessed == 1){
                    System.out.print(" X");
                    continue;
                }
                if (guessed == 2){
                    System.out.print(" O");
                    continue;
                }
                else if (ships == true){
                    System.out.print(" S");
                }
                else if (guessed ==0){
                    System.out.print(" -");
                }
                
                
            }
            System.out.println("");
            chc++;
        }
    }
    public void printShips(){
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char chc = 'A';
        for (int i = 0; i<NUM_ROWS; i++){
            System.out.print(chc);
            for (int j = 0; j<NUM_COLS; j++){
            
                boolean ships = grid[i][j].hasShip();
                if (ships == false){
                    System.out.print(" -");
                }
                if (ships == true){
                    System.out.print(" X");
                }
            }
            System.out.println("");
            chc++;
        }
        System.out.println("");
    }
}
