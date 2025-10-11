public class Person {
    private String name;
    private String id;
    private int age;
    
    public Person(String n, String i, int a){
        name = n;
        id = i;
        age = a;
    }
    
    public String getName(){
        return name;
    }
    
    public String getId(){
        return id;
    }
    
    public int getAge(){
        return age;
    }
    
    public String toString(){
        return "Name: "+name+"\nID: "+id+"\nAge: "+age;
    }
    
    public boolean equals(Person other){
        if (other.getId().equals(id)){
            return true;
        }
        return false;
    }
}
