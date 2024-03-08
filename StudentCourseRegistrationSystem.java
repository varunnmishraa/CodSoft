import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Course {
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String title, String description, int capacity, String schedule) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String name;
    Set<String> courses;

    public Student(String name) {
        this.name = name;
        this.courses = new HashSet<>();
    }
}

public class StudentCourseRegistrationSystem {
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    courseListingHandler();
                    break;
                case 2:
                    studentRegistrationHandler(scanner);
                    break;
                case 3:
                    courseRemovalHandler(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void initializeCourses() {
        courses.put("CS101", new Course("Introduction to Computer Science", "An intro to CS concepts", 100, "MWF 9:00-10:30"));
        courses.put("MATH201", new Course("Calculus I", "First course in calculus", 80, "TuTh 12:30-2:00"));
        // Add more courses as needed
    }

    private static void initializeStudents() {
        students.put("12345", new Student("John Doe"));
        students.put("67890", new Student("Jane Smith"));
        // Add more students as needed
    }

    private static void displayMenu() {
        System.out.println("\nStudent Course Registration System");
        System.out.println("1. Course Listing");
        System.out.println("2. Student Registration");
        System.out.println("3. Course Removal");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    private static void courseListingHandler() {
        System.out.println("Available Courses:");
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            Course course = entry.getValue();
            System.out.println(entry.getKey() + ": " + course.title + " (" + course.capacity + " slots available)");
            System.out.println("Description: " + course.description);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("---");
        }
    }

    private static void studentRegistrationHandler(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            System.out.print("Enter the course code you want to register for: ");
            String courseCode = scanner.nextLine();

            if (courses.containsKey(courseCode)) {
                Course course = courses.get(courseCode);
                if (course.capacity > 0) {
                    students.get(studentId).courses.add(courseCode);
                    course.capacity--;
                    System.out.println("You have been registered for " + course.title);
                } else {
                    System.out.println("Sorry, the course is currently full.");
                }
            } else {
                System.out.println("Invalid course code.");
            }
        } else {
            System.out.println("Invalid student ID.");
        }
    }

    private static void courseRemovalHandler(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            System.out.println("Currently registered courses for " + student.name + ": " + String.join(", ", student.courses));
            System.out.print("Enter the course code you want to drop: ");
            String courseCode = scanner.nextLine();

            if (student.courses.contains(courseCode)) {
                student.courses.remove(courseCode);
                Course course = courses.get(courseCode);
                course.capacity++;
                System.out.println("You have been unregistered from " + course.title);
            } else {
                System.out.println("You are not registered for this course.");
            }
        } else {
            System.out.println("Invalid student ID.");
        }
    }
}