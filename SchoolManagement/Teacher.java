public class Teacher extends Person{
    private String subject;
    private int yearsExperience;
    public Teacher(String name, String id, int age, String subject, int yearsExperience){
        super(name, id, age);
        this.subject = subject;
        this.yearsExperience = yearsExperience;
    }
    
    public String getSubject(){
        return subject;
    }
    public int getYearsExperience(){
        return yearsExperience;
    }
}
