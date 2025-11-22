public class Ship
{
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int row;
    private int col;
    private int length;
    private int direction;
    
    public Ship(int length){
        this.length = length;
        direction = UNSET;
    }
    public boolean isLocationSet(){
        boolean isSet = (row!=0 && col!=0)? true:false;
        return isSet;
    }
    public boolean isDirectionSet(){
        boolean isSet = (direction==UNSET)? false : true;
        return isSet;
    }
    public void setLocation(int row, int col){
        this.row = row;
        this.col = col;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getLength(){
        return length;
    }
    public int getDirection(){
        return direction;
    }
    private String directionToString(){
        if (direction==VERTICAL) return "Vertical";
        if (direction==HORIZONTAL) return "Horizontal";
        return "Unset";
    }
    public String toString(){
        return "Ship of length "+length+" at row: "+row+" col: "+col+" direction: "+this.directionToString();
    }
}
