import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentSystem {

    static class Student {
        private String id;
        private String name;
        private String course;

        public Student(String id, String name, String course) {
            this.id = id;
            this.name = name;
            this.course = course;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCourse() {
            return course;
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Name: %s | Course: %s", id, name, course);
        }
    }

    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EnrollmentSystem system = new EnrollmentSystem();
        system.run();
    }

    private void run() {
        System.out.println("Welcome to the Enrollment System");
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    listStudents();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting Enrollment System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Enroll Student");
        System.out.println("2. List Enrolled Students");
        System.out.println("3. Remove Student by ID");
        System.out.println("4. Exit");
    }

    private void enrollStudent() {
        System.out.println("\n--- Enroll New Student ---");
        String id;
        while (true) {
            id = readString("Enter student ID: ").trim();
            if (id.isEmpty()) {
                System.out.println("Student ID cannot be empty. Please try again.");
            } else if (findStudentById(id) != null) {
                System.out.println("Student ID already enrolled. Please enter a different ID.");
            } else {
                break;
            }
        }
        String name = readString("Enter student name: ").trim();
        while (name.isEmpty()) {
            System.out.println("Student name cannot be empty. Please try again.");
            name = readString("Enter student name: ").trim();
        }
        String course = readString("Enter course: ").trim();
        while (course.isEmpty()) {
            System.out.println("Course cannot be empty. Please try again.");
            course = readString("Enter course: ").trim();
        }

        students.add(new Student(id, name, course));
        System.out.println("Student enrolled successfully.");
    }

    private void listStudents() {
        System.out.println("\n--- Enrolled Students ---");
        if (students.isEmpty()) {
            System.out.println("No students enrolled yet.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        if (students.isEmpty()) {
            System.out.println("No students to remove.");
            return;
        }
        String id = readString("Enter student ID to remove: ").trim();
        Student toRemove = findStudentById(id);
        if (toRemove == null) {
            System.out.println("No student found with ID \"" + id + "\".");
        } else {
            students.remove(toRemove);
            System.out.println("Student with ID \"" + id + "\" has been removed.");
        }
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                int val = Integer.parseInt(line);
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}

