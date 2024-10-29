package org.coursemate;

import org.coursemate.controller.CourseController;
import org.coursemate.dao.CourseDAOImpl;
import org.coursemate.service.CourseService;
import org.coursemate.model.Course;
import org.coursemate.util.UserAuth;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        CourseService courseService = new CourseService(courseDAO);
        CourseController courseController = new CourseController(courseService, courseDAO);

        // User authentication
        System.out.print("Enter your role(admin, student, instructor): ");
        String username = scanner.nextLine();
        String role = UserAuth.authenticate(username);

        if (role == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Welcome " + username + "! Your role is: " + role);

        while (true) {
            System.out.println("\nCourseMate Menu:");
            System.out.println("1. View All Courses");
            System.out.println("2. Get Course by ID");
            if ("ADMIN".equals(role)) {
                System.out.println("3. Add Course");
                System.out.println("4. Update Course");
                System.out.println("5. Delete Course");
            } else if ("INSTRUCTOR".equals(role)) {
                System.out.println("3. Update Course");
            }
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // View All Courses
                    List<Course> courses = courseService.listAllCourses();
                    System.out.println("\nCourses:");
                    for (Course course : courses) {
                        System.out.println("ID: " + course.getCourseId() + ", Name: " + course.getCourseName() +
                                ", Description: " + course.getCourseDescription());
                    }
                    break;

                case 2:
                    // Get Course by ID
                    System.out.print("Enter course ID: ");
                    int courseId = scanner.nextInt();
                    Course course = courseController.getCourse(courseId);
                    System.out.println("ID: " + course.getCourseId() + ", Name: " + course.getCourseName() +
                            ", Description: " + course.getCourseDescription());
                    break;

                case 3:
                    // Add Course (Admin Only)
                    if ("ADMIN".equals(role)) {
                        System.out.print("Enter course name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter course description: ");
                        String description = scanner.nextLine();
                        courseController.createCourse(name, description);
                    } else if ("INSTRUCTOR".equals(role)) {
                        // Update Course
                        System.out.print("Enter course ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new course name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new course description: ");
                        String newDescription = scanner.nextLine();
                        Course updatedCourse = new Course();
                        updatedCourse.setCourseId(updateId);
                        updatedCourse.setCourseName(newName);
                        updatedCourse.setCourseDescription(newDescription);
                        courseController.updateCourse(updatedCourse);
                    }
                    break;

                case 4:
                    // Update Course (Admin Only)
                    if ("ADMIN".equals(role)) {
                        System.out.print("Enter course ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new course name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new course description: ");
                        String newDescription = scanner.nextLine();
                        Course updatedCourse = new Course();
                        updatedCourse.setCourseId(updateId);
                        updatedCourse.setCourseName(newName);
                        updatedCourse.setCourseDescription(newDescription);
                        courseController.updateCourse(updatedCourse);
                    } else {
                        System.out.println("You do not have permission to update courses.");
                    }
                    break;

                case 5:
                    // Delete Course (Admin Only)
                    if ("ADMIN".equals(role)) {
                        System.out.print("Enter course ID to delete: ");
                        int deleteId = scanner.nextInt();
                        courseController.deleteCourse(deleteId);
                    } else {
                        System.out.println("You do not have permission to delete courses.");
                    }
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
