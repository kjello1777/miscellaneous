public class Battleship extends ConsoleProgram
{
    public int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public String[] ROW_MATCH = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    public static Grid playerGrid = new Grid();
    public static Grid oppGrid = new Grid();
    public static Grid compGrid = new Grid();
    public static Grid compView = new Grid();
    public void run()
    {
        System.out.println("-------------------------");
        System.out.println("\tBattleship");
        System.out.println("-------------------------");
        readLine("\n-[Enter] To begin-");
        System.out.println("\nPlease place your ships:");
        playerGrid.printShips();
        Player user = new Player(playerGrid, oppGrid);
        setUserGrids(user.playerGrid, user.oppGrid, user);
        readLine("[Enter] to proceed");
        System.out.println("\n-Opponent is placing ships-\n");
        Player computer = new Player(compGrid, compView);
        setComputerGrids(compGrid, compView, computer);
        readLine("Computer has finished\n[Enter] to begin the game");
        int playerShots = 0;
        int compShots = 0;
        
        while(playerShots<17&&compShots<17){
            System.out.println("-----\nPlayer's turn.");
            System.out.println("Your view:");
            user.oppGrid.printStatus();
            int rowC = within(readLine("Guessed Row: "));
            while(rowC==-1){
                rowC = within(readLine("Not a valid row. Please try again.\nGuessed Row (A-J): "));
            }
            
            int colC = readInt("Guessed Column: ")-1;
            while (colC<0||colC>9){
                colC= readInt("Not a valid column. Please try again\nGuessed column (1-10): ");
            }
            if (user.oppGrid.alreadyGuessed(rowC, colC)){
                System.out.println("Location was previously guessed.");
                continue;
            }
            askForGuess(user, computer, rowC, colC);

            if (computer.playerGrid.hasShip(rowC, colC)){
                    readLine("-----\n[Enter] to proceed");
                    playerShots++;
                    continue;
            }
            else{
                readLine("Turn ended.\n-----\n[Enter] to proceed");
            }
            
            System.out.println("-----\nComputer's turn.");
            while (true){
                int crowC = Randomizer.nextInt(0,9);
                int ccolC = Randomizer.nextInt(0,9);
                while ((computer.oppGrid.alreadyGuessed(crowC, ccolC))){
                    crowC=Randomizer.nextInt(0,9);
                    ccolC=Randomizer.nextInt(0,9);
                }
                
                askForGuess(computer, user, crowC, ccolC);
                compShots++;
                if (user.playerGrid.hasShip(crowC, ccolC)){
                    readLine("-----\n[Enter] to proceed");
                    continue;
                }
                else{
                    System.out.println("Turn ended.\n-----\nYour Ships:");
                    user.playerGrid.printStatus();
                    readLine("\n-----\n[Enter] to proceed");
                    break;
                }
                
            }
        }
        if (playerShots == 17){
            System.out.println("\n*****\nYou Win!\n*****\n");
        }
        else{
            System.out.println("\n*****Computer Wins.\n*****\n");
        }
    }
    
    public void askForGuess(Player user, Player comp, int row, int col){
            if (row<user.oppGrid.numRows()&&col<user.oppGrid.numCols()){
            System.out.println("Missiles fired towards: row "+ROW_MATCH[row]+", column "+(col+1)+".\n.\n.\n.");
                if (comp.playerGrid.hasShip(row, col)){
                    System.out.println("Hit.\n");
                    user.oppGrid.markHit(row, col);
                    comp.playerGrid.markHit(row, col);
                }
                else{
                    System.out.println("Missed.\n");
                    user.oppGrid.markMiss(row, col);
                    comp.playerGrid.markMiss(row, col);
                }
            }
    }
    public void setUserGrids(Grid p, Grid o, Player u){
        for (int i = 0; i<SHIP_LENGTHS.length; i++){
            Ship s = new Ship(SHIP_LENGTHS[i]);
            while (true){
                System.out.println("Placing ships with length "+SHIP_LENGTHS[i]);
                String rowString = readLine("Row (A-J): ");
                while(within(rowString)==-1){
                    rowString = readLine("Not a valid row. Please try again.\nRow (A-J): ");
                }
                int row = within(rowString);
                int col = readInt("Column (1-10): ")-1;
                while (col<0||col>9){
                    col = readInt("Not a valid column. Please try again.\nColumn (1-10): ")-1;
                }
                String directionString = readLine("Orientation (h)orizontal or (v)ertical: ").toLowerCase();
                while (!directionString.equals("h")&&!directionString.equals("v")){
                    directionString = readLine("Not a valid direction. Please try again.\nOrientation (h)orizontal or (v)ertical: ").toLowerCase();
                }
                int direction = (directionString.equals("h")) ? 0:1;
                int len = SHIP_LENGTHS[i];
                boolean works = true;
                if (direction == 0){
                    if (col+len>10){
                        System.out.println("-OUT OF BOUNDS-");
                        continue;
                    }
                }else{
                    if (row+len>10){
                        System.out.println("-OUT OF BOUNDS-");
                        continue;
                    }
                }
                if (direction==0){
                    for (int y  = col; y<col+s.getLength();y++){
                        if (p.hasShip(row, y)){
                            System.out.println("A ship has already been placed at one of hte coordinates!");
                            works = false;
                            continue;
                        }
                    }
                }else if (direction == 1){
                    for (int x = row; x<row+s.getLength(); x++){
                        if (p.hasShip(x, col)){
                            System.out.println("A ship has already been placed at one of the coordinates!");
                            works = false;
                            continue;
                        }
                    }
                }
                if (!works){
                    continue;
                }
                u.chooseShipLocation(s, row, col, direction);
                p.printShips();
                break;
            }
        }
    }
    public void setComputerGrids(Grid cg, Grid cv, Player p){
        for (int i =0; i<SHIP_LENGTHS.length; i++){
            Ship s = new Ship(SHIP_LENGTHS[i]);
            while (true){
                int row = Randomizer.nextInt(0,9);
                int column = Randomizer.nextInt(0,9);
                int direction = Randomizer.nextInt(0,1);
                int len = SHIP_LENGTHS[i];
                boolean works = true;
                
                if (direction == 0){
                    if (column + len >10){
                        continue;
                    }
                }else{
                    if (row + len > 10){
                        continue;
                    }
                }
                if (direction == 0){
                    for (int x = column; x<column + s.getLength(); x++){
                        if (cg.hasShip(row, x)){
                            works = false;
                            continue;
                        }
                    }
                }else if (direction == 1){
                    for (int x = row; x <row+s.getLength(); x++){
                        if (cg.hasShip(x,column)){
                            works = false;
                            continue;
                        }
                    }
                }
                if (!works){
                    continue;
                }
                p.chooseShipLocation(s, row, column, direction);
                cg.printShips();
                break;
            }
        }
    }
    public int within(String x){
        x = x.toUpperCase();
        for (int i = 0; i<ROW_MATCH.length; i++){
            if (ROW_MATCH[i].equals(x)){
                return i;
            }
        }
        return -1;
    }
}
