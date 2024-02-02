package fi.tuni.prog3.studentregister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentRegister {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Attainment> attainments;

    public StudentRegister() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        attainments = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparing(Student::getName));
        return sortedStudents;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> sortedCourses = new ArrayList<>(courses);
        Collections.sort(sortedCourses, Comparator.comparing(Course::getName));
        return sortedCourses;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addAttainment(Attainment attainment) {
        attainments.add(attainment);
    }

    public void printStudentAttainments(String studentNumber, String order) {
        boolean studentFound = false;

        for (Student student : students) {
            if (student.getStudentNumber().equals(studentNumber)) {
                studentFound = true;
                System.out.println(student.getName() + " (" + student.getStudentNumber() + "):");
                break;
            }
        }

        if (!studentFound) {
            System.out.println("Unknown student number: " + studentNumber);
            return;
        }

        ArrayList<Attainment> studentAttainments = new ArrayList<>();
        for (Attainment attainment : attainments) {
            if (attainment.getStudentNumber().equals(studentNumber)) {
                studentAttainments.add(attainment);
            }
        }

        if (order.equals("by name")) {
            Collections.sort(studentAttainments, (a1, a2) -> {
                String courseName1 = getCourseNameByCode(a1.getCourseCode());
                String courseName2 = getCourseNameByCode(a2.getCourseCode());
                return courseName1.compareTo(courseName2);
            });
        } else if (order.equals("by code")) {
            Collections.sort(studentAttainments, Comparator.comparing(Attainment::getCourseCode));
        }

        for (Attainment attainment : studentAttainments) {
            for (Course course : courses) {
                if (course.getCode().equals(attainment.getCourseCode())) {
                    System.out.println("  " + course.getCode() + " " + course.getName() + ": " + attainment.getGrade());
                    break;
                }
            }
        }
    }

    public void printStudentAttainments(String studentNumber) {
        printStudentAttainments(studentNumber, "");
    }

    private String getCourseNameByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course.getName();
            }
        }
        return "Unknown Course";
    }
}