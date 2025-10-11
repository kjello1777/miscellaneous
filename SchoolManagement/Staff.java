public class Staff extends Person{
    private String position;
    public Staff(String name, String id, int age, String position){
        super(name, id, age);
        this.position = position;
    }
    public String getPosition(){
        return position;
    }
    
}
