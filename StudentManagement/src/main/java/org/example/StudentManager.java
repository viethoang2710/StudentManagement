package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManager {
    private final StudentStack stack = new StudentStack();

    // Add student using StudentStack (push)
    public void addStudent(String id, String name, double marks) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: Student ID cannot be empty.");
            return;
        }
        if (name == null || name.isEmpty()) {
            System.out.println("Error: Student Name cannot be empty.");
            return;
        }
        if (marks < 0 || marks > 10) {
            System.out.println("Error: Marks should be between 0 and 10.");
            return;
        }
        stack.push(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    // Edit student by ID
    public boolean editStudent(String id, String newName, double newMarks) {
        boolean found = false;
        Student tempStudent = null;

        StudentStack tempStack = new StudentStack();

        while (!stack.isEmpty()) {
            Student student = stack.pop();
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                found = true;
                tempStudent = student;
                break;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        if (found) {
            stack.push(tempStudent);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Error: Student with ID " + id + " not found.");
        }
        return found;
    }

    // Delete student by ID
    public boolean deleteStudent(String id) {
        boolean found = false;
        StudentStack tempStack = new StudentStack();

        while (!stack.isEmpty()) {
            Student student = stack.pop();
            if (student.getId().equals(id)) {
                System.out.println("Student deleted successfully!");
                found = true;
                break;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Error: Student with ID " + id + " not found.");
        }

        return found;
    }

    // Search student by ID
    public Student searchStudent(String id) {
        StudentStack tempStack = new StudentStack();

        while (!stack.isEmpty()) {
            Student student = stack.pop();
            if (student.getId().equals(id)) {
                while (!tempStack.isEmpty()) {
                    stack.push(tempStack.pop());
                }
                return student;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return null;
    }

    // Display all students
    public void displayAllStudents() {
        stack.displayAll();
    }

    // Main Method
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentManager manager = new StudentManager();

            while (true) {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student");
                System.out.println("5. Display All Students");
                System.out.println("6. Sort Students by Marks (QuickSort)");
                System.out.println("7. Sort Students by Marks (MergeSort)");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a number between 1 and 8.");
                    scanner.nextLine(); // Clear buffer
                    continue;
                }
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = scanner.nextDouble();
                        manager.addStudent(id, name, marks);
                    }
                    case 2 -> {
                        System.out.print("Enter ID of the student to edit: ");
                        String editId = scanner.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Marks: ");
                        double newMarks = scanner.nextDouble();
                        manager.editStudent(editId, newName, newMarks);
                    }
                    case 3 -> {
                        System.out.print("Enter ID of the student to delete: ");
                        String deleteId = scanner.nextLine();
                        manager.deleteStudent(deleteId);
                    }
                    case 4 -> {
                        System.out.print("Enter ID to search: ");
                        String searchId = scanner.nextLine();
                        Student student = manager.searchStudent(searchId);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found.");
                        }
                    }
                    case 5 -> manager.displayAllStudents();
                    case 6 -> {
                        manager.stack.quickSort();
                        System.out.println("Students sorted using QuickSort.");
                        manager.displayAllStudents();
                    }
                    case 7 -> {
                        manager.stack.mergeSort();
                        System.out.println("Students sorted using MergeSort.");
                        manager.displayAllStudents();
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
