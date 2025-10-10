import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public class MyProgram
{
    public static void main(String[] args)
    {
        
        int group;
        System.out.println("Please enter the number of groups: ");
        Scanner in = new Scanner(System.in);
        group = in.nextInt();
        in.close();
        System.out.println();
        System.out.println("Generating "+group+" groups...\n");
        
        try{
            ArrayList<String> students = new ArrayList<>();
            String student;
            File file = new File("names.csv");
            Scanner myReader= new Scanner(file);
            
            while(myReader.hasNextLine()){
                student = myReader.nextLine();
                students.add(student);
            }
            
            int classSize = students.size();

            ArrayList<ArrayList<String>> groups = new ArrayList<>();
            
            Collections.shuffle(students);
            for (int i =1; i<=group; i++){
                groups.add(new ArrayList<>());
            }
            
            for (int i = 0; i < classSize; i++){
                groups.get(i%group).add(students.get(i));
            }
            
            for (int i = 0; i<groups.size(); i++){
                int n = i+1;
                System.out.println("Group: "+n);
                ArrayList<String> sig = groups.get(i);
                for (String stu: sig){
                    System.out.println(stu);
                }
                System.out.println();
            }
            myReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
