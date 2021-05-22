package Utils;

import models.Student;

import java.util.SortedMap;

/**
 * is used for showing data in tabular form
 */
public class TabularData {
    public static void printStudentHeader() {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s", "Id", "|", "Name", "|", "Surname", "|", "Father Name", "|", "Email", "|", "Phone number"));
        System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------------------"));
    }

    public static void printStudent(Student student) {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10S %10S",  student.getId(), "|", student.getName(), "|", student.getSurName(), "|", student.getFatherName(), "|", student.getEmail(),  "|", student.getPhone()));
    }

    public static void printStudents(SortedMap<String, Student> students){
        printStudentHeader();
        for (Student student : students.values()){
            printStudent(student);
        }
    }

}
