import Utils.TabularData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Utils.InputReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Student;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static SortedMap<String, Student> nameStudents = new TreeMap<>();
    private static SortedMap<String, Student> surnameStudents = new TreeMap<>();
    private static SortedMap<String, Student> fatherNameStudents = new TreeMap<>();
    private static int id = 0;
    private static String dash = "-".repeat(67);

    public static void main(String[] args) throws IOException {
        getFileData();
        start();
    }

    public static int generateId(){
        return students.get(students.size() - 1).getId() + 1;
    }

    public static void getFileData() throws IOException {
        FileReader in = new FileReader("students.json");
        StringBuilder jsonString = new StringBuilder();
        int i;
        while((i=in.read())!=-1)
            jsonString.append((char)i);
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        students = objectMapper.readValue(jsonString.toString(),  new TypeReference<List<Student>>(){});
        students = students.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
        syncWithList();
    }

    private static int menu(){
        System.out.println(dash + "STUDENT MENU" + dash);
        System.out.println("1.CREATE A STUDENT");
        System.out.println("2.UPDATE A STUDENT");
        System.out.println("3.DELETE A STUDENT");
        System.out.println("4.SEARCH OVER STUDENTS");
        System.out.println("5.LOG OUT");
        System.out.println(dash + "STUDENT MENU" + dash);
        return InputReader.readInt("Enter number of menu: ",1, 5);
    }



    private static void start() throws JsonProcessingException {
        while (true){
            int choice = menu();
            switch (choice){
                case 1:
                    createStudent();
                     break;
                case 2:
                    updateStudent();
                     break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    search();
                    break;
                case 5:
                    System.out.println("Log outed");
                    System.exit(0);
            }
        }
    }

    private static int searchMenu(){
        System.out.println(dash + "SEARCH MENU" + dash);
        System.out.println("1.SEARCH BY NAME");
        System.out.println("2.SEARCH BY SURNAME");
        System.out.println("3.SEARCH BY FATHER NAME");
        System.out.println("4.GO BACK");
        System.out.println(dash + "SEARCH MENU" + dash);
        return InputReader.readInt("Enter number of menu: ",1, 4);
    }

    private static void search() throws JsonProcessingException {
        while (true){
            int choice = searchMenu();
            switch (choice){
                case 1:
                    searchByName();
                    break;
                case 2:
                    searchBySurname();
                    break;
                case 3:
                    searchByFatherName();
                    break;
                case 4:
                    return;
            }
        }
    }


    public static void createStudent() throws JsonProcessingException {
        System.out.println(dash + "CREATE STUDENT" + dash);
        String name = InputReader.readString("Enter name of the student : ");
        String surName = InputReader.readString("Enter surname of the student : ");
        String fatherName = InputReader.readString("Enter father name of the student : ");
        String email = InputReader.emailReader("Enter email of the student : ");
        String phoneNumber = InputReader.phoneReader("Enter phone number of the student : ");
        Student student = new Student(generateId(), name, surName, fatherName, email, phoneNumber);
        students.add(student);
        syncWithList();
        updateJsonFile();
        System.out.println("Student added successfully");
    }

    public static void updateStudent() throws JsonProcessingException {
        while(true) {
            System.out.println(dash + "UPDATE STUDENT" + dash);
            int id = InputReader.readInt("Enter id of student: ", 0, Integer.MAX_VALUE);
            if (!checkIfExists(id)) {
                continue;
            }
            Student student = getStudent(id);
            System.out.println("You are updating: " + student.toString());
            String name = InputReader.readString("Enter new name of the student : ");
            String surName = InputReader.readString("Enter new surname of the student : ");
            String fatherName = InputReader.readString("Enter new father name of the student : ");
            String email = InputReader.emailReader("Enter new email of the student : ");
            String phoneNumber = InputReader.phoneReader("Enter new phone number of the student : ");

            student.setName(name);
            student.setSurName(surName);
            student.setFatherName(fatherName);
            student.setEmail(email);
            student.setPhone(phoneNumber);
            updateJsonFile();
            syncWithList();
            System.out.println("Student updated successfully");
            return;
        }
    }

    public static void deleteStudent() throws JsonProcessingException {
        while (true) {
            System.out.println(dash + "DELETE STUDENT" + dash);
            int id = InputReader.readInt("Enter id of student: ", 0, Integer.MAX_VALUE);
            if (!checkIfExists(id)) {
                continue;
            }
            Student student = getStudent(id);
            students.remove(student);
            updateJsonFile();
            syncWithList();
            System.out.println("Student deleted successfully");
            return;
        }
    }

    public static void searchByName(){
        String keyWord = InputReader.readString("Type name of the student to search: ");
        TabularData.printStudents(nameStudents.subMap( keyWord.toLowerCase(), keyWord.toLowerCase() + Character.MAX_VALUE ));
    }


    public static void searchBySurname(){
        String keyWord = InputReader.readString("Type surname of the student to search: ");
        TabularData.printStudents(surnameStudents.subMap( keyWord.toLowerCase(), keyWord.toLowerCase() + Character.MAX_VALUE ));
    }


    public static void searchByFatherName(){
        String keyWord = InputReader.readString("Type father name of the student to search: ");
        TabularData.printStudents(fatherNameStudents.subMap( keyWord.toLowerCase(), keyWord.toLowerCase() + Character.MAX_VALUE ));
    }


    public static boolean checkIfExists(int id){
        for(Student student : students){
            if (student.getId() == id){
                return true;
            }
        }
        System.out.println("Can't find student with id "+id);
        return false;
    }

    public static Student getStudent(int id){
        for(Student student : students){
            if (student.getId() == id){
                return student;
            }
        }
        throw new NoSuchElementException();
    }



    public static void syncWithList(){
        nameStudents.clear();
        for (Student student: students){
            nameStudents.put(student.getName()+student.getId(), student);
            surnameStudents.put(student.getSurName()+student.getId() , student);
            fatherNameStudents.put(student.getFatherName()+student.getId() , student);
        }
    }

    public static void updateJsonFile() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = mapper.writeValueAsString(students);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("students.json"), "utf-8"))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
