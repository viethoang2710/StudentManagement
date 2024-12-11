package org.example;

import java.util.Arrays;

public class StudentStack {
    private static final int INITIAL_CAPACITY = 10;
    private Student[] stack;
    private int top;

    // Constructor
    public StudentStack() {
        stack = new Student[INITIAL_CAPACITY];
        top = -1;
    }

    // Push student to stack
    public void push(Student student) {
        if (top == stack.length - 1) {
            resize();
        }
        stack[++top] = student;
    }

    // Pop student from stack
    public Student pop() {
        if (isEmpty()) {
            System.out.println("Error: Stack is empty.");
            return null;
        }
        return stack[top--];
    }

    // Peek at the top student in stack
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Error: Stack is empty.");
            return null;
        }
        return stack[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Resize stack array when full
    private void resize() {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }

    // Get the current number of students in the stack
    public int size() {
        return top + 1;
    }

    // Display all students in the stack
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("Error: No students in the stack.");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i]);
        }
    }

    // QuickSort algorithm to sort students by marks
    public void quickSort() {
        quickSort(0, top);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = stack[high].getMarks();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (stack[j].getMarks() <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Student temp = stack[i];
        stack[i] = stack[j];
        stack[j] = temp;
    }

    // MergeSort algorithm to sort students by marks
    public void mergeSort() {
        mergeSort(0, top);
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(left, middle);
            mergeSort(middle + 1, right);
            merge(left, middle, right);
        }
    }

    private void merge(int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        System.arraycopy(stack, left, leftArray, 0, n1);
        System.arraycopy(stack, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getMarks() <= rightArray[j].getMarks()) {
                stack[k] = leftArray[i];
                i++;
            } else {
                stack[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            stack[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            stack[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
