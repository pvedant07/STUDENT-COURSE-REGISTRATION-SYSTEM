import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public boolean registerStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        } else {
            return false;
        }
    }

    public void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    public boolean hasAvailableSlots() {
        return enrolled < capacity;
    }

    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nCapacity: " + capacity +
               "\nEnrolled: " + enrolled + "\nAvailable Slots: " + (capacity - enrolled) + "\nSchedule: " + schedule + "\n";
    }
}

class Student {
    String studentId;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course is full. Registration failed.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered in any courses.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.code + " - " + course.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    static HashMap<String, Course> courses = new HashMap<>();
    static HashMap<String, Student> students = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample courses
        courses.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basic concepts of CS.", 30, "Mon-Wed 10:00-11:30"));
        courses.put("MATH201", new Course("MATH201", "Calculus II", "Advanced calculus topics.", 25, "Tue-Thu 14:00-15:30"));
        courses.put("ENG301", new Course("ENG301", "Advanced English Literature", "Study of classic literature.", 20, "Mon-Wed 12:00-13:30"));

        // Sample students
        students.put("S123", new Student("S123", "Alice Johnson"));
        students.put("S456", new Student("S456", "Bob Smith"));

        // Main menu
        int choice;
        do {
            System.out.println("\nCourse Registration System");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    static void listAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    static void registerForCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student != null) {
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            Course course = courses.get(courseCode);

            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student != null) {
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            Course course = courses.get(courseCode);

            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    static void viewRegisteredCourses() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }
}
