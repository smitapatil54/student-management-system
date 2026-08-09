package com.student;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the console-based Student Management System.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("======================================");
        System.out.println("   Student Management System");
        System.out.println("======================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById();
                case 4 -> deleteStudentById();
                case 5 -> {
                    System.out.println("Thank you for using Student Management System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. Add a student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Delete student by ID");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        int studentId = readInt("Enter Student ID: ");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine().trim();
        double marks = readDouble("Enter Marks: ");

        Student student = new Student(studentId, name, course, marks);
        if (studentService.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with this ID already exists.");
        }
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("--- All Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudentById() {
        int studentId = readInt("Enter Student ID to search: ");
        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found: " + student);
        }
    }

    private static void deleteStudentById() {
        int studentId = readInt("Enter Student ID to delete: ");

        if (studentService.deleteStudentById(studentId)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
