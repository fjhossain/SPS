import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


class Student {
    public static int numOfStudents = 0;
    private String firstName, lastName, userName, password;
    private int studentNum;

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = id;
        this.password = generatePassword();
        numOfStudents ++;
        this.studentNum = numOfStudents;
    }

    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + ", UserName: " + this.userName + ", Password: " + this.password;
    } 
    
    public static String generatePassword() {
        int passwordLength = 12;
        char[] array = new char[passwordLength];
        Random random = new Random();
        for (int i = 0; i < passwordLength; i ++) {
            array[i] = (char) random.nextInt( 33, 127);
        }
        return new String(array);
    }
}
public class PassWord{
    public static List<String> readFileToList(String fileName) throws FileNotFoundException, IOException{
        List<String> list = new ArrayList<>();
        try( BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String str;
            while((str = reader.readLine()) != null){
                list.add(str.trim());
            }
        } return list;
            
        
    }
    public static void main(String[] args) throws Exception{
       List<String> firstNames = readFileToList("fisrt_names.txt");
       List<String> lastNames = readFileToList("fisrt_names.txt");
       Set<String> userNames = new TreeSet<>();
       Map<String, Student> studentIdMap = new HashMap<>();
       List<Student> students = new ArrayList<>();
       Random random =new Random();
       String firstName, lastName , userName;
       Student student;
       for( int i = 0; i< 20; i++){
        firstName = firstNames.get(random.nextInt(3));
        lastName = lastNames.get(random.nextInt(3));
        userName = firstName.toLowerCase()+ lastName.toLowerCase();
        if (userNames.contains(userName)){
            userName= firstName.substring(0, 1).toLowerCase()+ lastName.toLowerCase();
            int temp = 1;
            while(userNames.contains(userName)){
                userName = firstName.substring(0, 1).toLowerCase()+lastName.toLowerCase()+ temp;
                temp++;
            }
        }
        userNames.add(userName);
        student = new Student(firstName, lastName, userName);
        students.add(student);
        studentIdMap.put(userName, student);
       }
       for(String userId: userNames){
        System.out.println(userId + ":\n" + studentIdMap.get(userId)+ "\n");
       }
       System.out.println(Student.numOfStudents + "Student have been added.");
    }
}

