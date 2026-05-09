import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subject {
    private final String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Teacher {
    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Allocation {
    private final Student student;
    private final Teacher teacher;
    private final Subject subject;

    public Allocation(Student student, Teacher teacher, Subject subject) {
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
    }

    public void display() {
        System.out.printf("%-20s %-20s %-20s%n",
                student.getName(),
                teacher.getName(),
                subject.getName());
    }
}

class AllocationManager {
    private final List<Allocation> allocations;

    public AllocationManager() {
        allocations = new ArrayList<>();
    }

    public void assignSubjects(List<Student> students, List<Teacher> teachers, List<Subject> subjects) {
        int totalAllocations = Math.min(students.size(), Math.min(teachers.size(), subjects.size()));

        for (int i = 0; i < totalAllocations; i++) {
            allocations.add(new Allocation(students.get(i), teachers.get(i), subjects.get(i)));
        }
    }

    public void displayAllocations() {
        if (allocations.isEmpty()) {
            System.out.println("No subjects assigned.");
            return;
        }

        System.out.println("\nAssigned Subjects");
        System.out.println("-----------------");
        System.out.printf("%-20s %-20s %-20s%n", "Student", "Teacher", "Subject");

        for (Allocation allocation : allocations) {
            allocation.display();
        }
    }
}

public class SubjectAllocationSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Student> students = readStudents();
        List<Teacher> teachers = readTeachers();
        List<Subject> subjects = readSubjects();

        AllocationManager manager = new AllocationManager();
        manager.assignSubjects(students, teachers, subjects);
        manager.displayAllocations();

        scanner.close();
    }

    private static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        int count = readInt("Enter number of students: ");

        for (int i = 1; i <= count; i++) {
            String name = readLine("Enter student " + i + " name: ");
            students.add(new Student(name));
        }

        return students;
    }

    private static List<Teacher> readTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        int count = readInt("\nEnter number of teachers: ");

        for (int i = 1; i <= count; i++) {
            String name = readLine("Enter teacher " + i + " name: ");
            teachers.add(new Teacher(name));
        }

        return teachers;
    }

    private static List<Subject> readSubjects() {
        List<Subject> subjects = new ArrayList<>();
        int count = readInt("\nEnter number of subjects: ");

        for (int i = 1; i <= count; i++) {
            String name = readLine("Enter subject " + i + " name: ");
            subjects.add(new Subject(name));
        }

        return subjects;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
