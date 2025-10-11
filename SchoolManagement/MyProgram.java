import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class MyProgram
{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int choice = 0;
        ArrayList<Person> people = new ArrayList<>();
        loadPeople(people); // load pre-existing data
        // loops menu
        while (choice!=6){
            System.out.println("\nMenu System (Enter 1-6)");
            System.out.println("\t1. Add Students");
            System.out.println("\t2. Add Teachers");
            System.out.println("\t3. Add Staff Members");
            System.out.println("\t4. Assign Student Grades");
            System.out.println("\t5. Display All Users");
            System.out.println("\t6. Save & Exit");
            choice = in.nextInt();
            in.nextLine();
            
            // if adding people
            if (choice==1||choice==2||choice==3){
                System.out.print("Enter name: ");
                String name = in.nextLine();
                
                System.out.print("Enter ID: ");
                String id = in.nextLine();
                
                System.out.print("Enter age: ");
                int age = in.nextInt();
                in.nextLine();
            
                switch(choice){
                    case 1:
                        System.out.print("Enter student's grade level:  ");
                        int gradeLevel = in.nextInt();
                        in.nextLine();
                        
                        System.out.print("Enter student's GPA: ");
                        double gpa = in.nextDouble();
                        in.nextLine();
                        Student a = new Student(name,id,age,gradeLevel,gpa);
                        people.add(a);
                        break;
                        
                    case 2:
                        System.out.print("Enter subject taught: ");
                        String subject = in.nextLine();
                        System.out.print("Enter years of experience: ");
                        int yearsExp = in.nextInt();
                        in.nextLine();
                        Teacher t = new Teacher(name, id, age, subject, yearsExp);
                        people.add(t);
                        break;
                    
                    case 3:
                        System.out.print("Enter position: ");
                        String position = in.nextLine();
                        Staff s = new Staff(name, id, age, position);
                        people.add(s);
                        break;
                        
                }
            }
            switch(choice){
            // assigning grades
                case 4:
                    System.out.print("Enter student ID: ");
                    String studentId = in.nextLine();
                    boolean found = false;
                
                    for (Person person : people) {
                        if (person instanceof Student && person.getId().equals(studentId)) {
                            Student student = (Student) person;
                            System.out.print("Enter subject: ");
                            String subject = in.nextLine();
                            System.out.print("Enter grade (0-100): ");
                            double grade = in.nextDouble();
                            in.nextLine(); // Clear buffer
                            student.assignGrade(subject, grade);
                            found = true;
                            break;
                        }
                    }
                
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
            
            
            // displaying users
                case 5:
                    System.out.println("=== All School Personnel ===");
                    for (Person person : people) {
                        System.out.println("\n"+person); // Uses toString()
                        if (person instanceof Student) {
                            ((Student) person).displayGrades();
                            System.out.println("GPA: " + ((Student) person).getGPA());
                        }
                    }
                    break;
            }
        }
        if (choice ==6){
            // Writing into a file
            savePeople(people);
        }
            
        
    }
    
    public static void savePeople(ArrayList<Person> people) {
        try (FileWriter writer = new FileWriter("school_data.txt")) {
            for (Person person : people) {
                if (person instanceof Student) {
                    Student s = (Student) person;
                    String line = String.format("Student->%s->%s->%d->%d->%.2f",
                            s.getName(), s.getId(), s.getAge(), s.getGradeLevel(), s.getGPA());
                    writer.write(line + "\n");
                } else if (person instanceof Teacher) {
                    Teacher t = (Teacher) person;
                    String line = String.format("Teacher->%s->%s->%d->%s->%d",
                            t.getName(), t.getId(), t.getAge(), t.getSubject(), t.getYearsExperience());
                    writer.write(line + "\n");
                } else if (person instanceof Staff) {
                    Staff st = (Staff) person;
                    String line = String.format("Staff->%s->%s->%d->%s",
                            st.getName(), st.getId(), st.getAge(), st.getPosition());
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
    
    public static void loadPeople(ArrayList<Person> people) {
        try (Scanner fileScanner = new Scanner(new File("school_data.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("->");
                String type = parts[0];
                
                switch (type) {
                    case "Student":
                        Student s = new Student(
                            parts[1], // name
                            parts[2], // id
                            Integer.parseInt(parts[3]), // age
                            Integer.parseInt(parts[4]), // gradeLevel
                            Double.parseDouble(parts[5]) // GPA
                        );
                        people.add(s);
                        break;
                    case "Teacher":
                        Teacher t = new Teacher(
                            parts[1], parts[2], 
                            Integer.parseInt(parts[3]), 
                            parts[4], 
                            Integer.parseInt(parts[5])
                        );
                        people.add(t);
                        break;
                    case "Staff":
                        Staff st = new Staff(
                            parts[1], parts[2], 
                            Integer.parseInt(parts[3]), 
                            parts[4]
                        );
                        people.add(st);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("No saved data found.");
        }
    }

    
}
