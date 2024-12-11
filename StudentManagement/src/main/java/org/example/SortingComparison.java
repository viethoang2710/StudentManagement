package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SortingComparison {

    // Tạo danh sách học sinh ngẫu nhiên
    public static Student[] generateRandomStudents(int numberOfStudents) {
        Random random = new Random();
        Student[] students = new Student[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            String id = "ID" + (i + 1);
            String name = "Student " + (i + 1);
            double marks = random.nextDouble() * 10;  // Điểm ngẫu nhiên từ 0 đến 10
            students[i] = new Student(id, name, marks);
        }
        return students;
    }

    // So sánh thời gian của QuickSort, MergeSort và BubbleSort
    public static void compareSortingAlgorithms(Student[] students, int choice) {
        long startTime, endTime, duration;


        // QuickSort
        if (choice == 2) {
            Student[] quickSortedStudents = students.clone();
            startTime = System.nanoTime();
            StudentStack quickStack = new StudentStack();
            for (Student student : quickSortedStudents) {
                quickStack.push(student);
            }
            quickStack.quickSort();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("QuickSort Duration: " + duration + " nanoseconds");
        }

        // MergeSort
        if (choice == 1) {
            Student[] mergeSortedStudents = students.clone();
            startTime = System.nanoTime();
            StudentStack mergeStack = new StudentStack();
            for (Student student : mergeSortedStudents) {
                mergeStack.push(student);
            }
            mergeStack.mergeSort();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("MergeSort Duration: " + duration + " nanoseconds");
        }

        // BubbleSort
        if (choice == 3) {
            Student[] bubbleSortedStudents = students.clone();
            startTime = System.nanoTime();
            bubbleSort(bubbleSortedStudents);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("BubbleSort Duration: " + duration + " nanoseconds");
        }
    }

    // Thuật toán BubbleSort
    public static void bubbleSort(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students[j].getMarks() > students[j + 1].getMarks()) {
                    // Hoán đổi
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số lượng học sinh từ người dùng
        int numberOfStudents = 0;
        while (numberOfStudents <= 0) {
            System.out.print("Enter the number of students: ");
            try {
                numberOfStudents = scanner.nextInt();
                if (numberOfStudents <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            }
        }

        // Tạo danh sách học sinh ngẫu nhiên
        Student[] students_Merge = generateRandomStudents(numberOfStudents);

        Student [] students2_Quick = students_Merge.clone();
        Student [] students3_Bubble = students_Merge.clone();

        // Hiển thị danh sách học sinh ngẫu nhiên
//        System.out.println("\nGenerated List of Students:");
//        for (Student student : students_Quick) {
//            System.out.println(student);
//        }

        // Lựa chọn thuật toán sắp xếp
        while (true) {
            System.out.println("\n--- Sorting Options ---");
            System.out.println("1. MergeSort");
            System.out.println("2. QuickSort");
            System.out.println("3. BubbleSort");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    compareSortingAlgorithms(students_Merge, 1);
                }
                case 2 -> {
                    compareSortingAlgorithms(students2_Quick, 2);
                }
                case 3 -> {
                    compareSortingAlgorithms(students3_Bubble, 3);
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;  // Thoát chương trình
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
