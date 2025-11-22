public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public Grid playerGrid;
    public Grid oppGrid;
    public Player(Grid play, Grid opp){
        playerGrid = play;
        oppGrid = opp;
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction){
        s.setLocation(row, col);
        s.setDirection(direction);
        this.playerGrid.addShip(s);
    }
    
}
