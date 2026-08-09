package com.student;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that manages student records in memory using an ArrayList.
 */
public class StudentService {

    private final ArrayList<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (findStudentById(student.getStudentId()) != null) {
            return false;
        }
        students.add(student);
        return true;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public boolean deleteStudentById(int studentId) {
        Student student = findStudentById(studentId);
        if (student == null) {
            return false;
        }
        students.remove(student);
        return true;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
