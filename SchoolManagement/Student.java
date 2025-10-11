public class Student extends Person{
    private int gradeLevel;
    private double GPA;
    private String[][] gradebook;
    public Student(String name, String id, int age, int gradeLevel, double GPA){
        super(name, id, age);
        this.gradeLevel = gradeLevel;
        this.GPA = GPA;
        this.gradebook = new String[10][2];
    }
    public int getGradeLevel(){
        return gradeLevel;
    }
    public double getGPA(){
        return GPA;
    }
    public void assignGrade(String subject, double grade){
        int count = 0;
        for (String[] entry : gradebook){
            if (entry[0]!=null){
                count++;
            }
        }
        if (count>=7){
            return;
        }
        for (int i = 0; i<gradebook.length;i++){
            if (gradebook[i][0]==null){
                gradebook[i][0]=subject;
                gradebook[i][1]=String.valueOf(grade);
                break;
            }
        }
        updateGPA();
    }
    private void updateGPA() {
        double sum = 0.0;
        int count = 0;
        for (String[] entry : gradebook) {
            if (entry[0] != null && entry[1] != null) {
                try {
                    double grade = Double.parseDouble(entry[1]);
                    if (grade >= 90) sum += 4.0;
                    else if (grade >= 80) sum += 3.0;
                    else if (grade >= 70) sum += 2.0;
                    else if (grade >= 60) sum += 1.0;
                    count++;
                } catch (NumberFormatException e) {}
            }
        }
        if (count>0){
            GPA = sum/count;
        } else{
            GPA = 0.0;
        }
    }
    
    public void displayGrades() {
        for (String[] entry : gradebook) {
            if (entry[0] != null && entry[1] != null) {
                System.out.println(entry[0] + ": " + entry[1]);
            }
        }
    }

}
