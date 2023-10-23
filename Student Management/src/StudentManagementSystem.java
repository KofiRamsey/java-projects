import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private Map<String, Double> grades;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public void addGrade(String course, double grade) {
        grades.put(course, grade);
    }

    public void displayGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades available for student " + name);
        } else {
            System.out.println("Grades for student " + name + ":");
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                System.out.println("Course: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }
}

class Course {
    private String courseId;
    private String name;

    public Course(String courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }
}

public class StudentManagementSystem {
    private List<Student> students;
    private List<Course> courses;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();

        // Adding students
        Student student1 = new Student("S001", "John Smith");
        Student student2 = new Student("S002", "Jane Doe");
        sms.addStudent(student1);
        sms.addStudent(student2);

        // Adding courses
        Course course1 = new Course("C001", "Mathematics");
        Course course2 = new Course("C002", "Science");
        sms.addCourse(course1);
        sms.addCourse(course2);

        // Adding grades
        student1.addGrade(course1.getCourseId(), 90.5);
        student1.addGrade(course2.getCourseId(), 85.0);
        student2.addGrade(course1.getCourseId(), 92.0);

        // Displaying grades
        student1.displayGrades();
        student2.displayGrades();

        // Searching for a student by ID
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to search: ");
        String searchId = scanner.nextLine();
        Student foundStudent = sms.findStudentById(searchId);
        if (foundStudent != null) {
            foundStudent.displayGrades();
        } else {
            System.out.println("Student not found.");
        }

        // Adding a new grade for a student
        System.out.print("Enter student ID to add grade: ");
        String studentId = scanner.nextLine();
        foundStudent = sms.findStudentById(studentId);
        if (foundStudent != null) {
            System.out.print("Enter course ID: ");
            String courseId = scanner.nextLine();
            Course foundCourse = sms.findCourseById(courseId);
            if (foundCourse != null) {
                System.out.print("Enter grade: ");
                double grade = scanner.nextDouble();
                foundStudent.addGrade(foundCourse.getCourseId(), grade);
                System.out.println("Grade added successfully.");
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }

        scanner.close();
    }
}
