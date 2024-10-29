package org.coursemate.controller;


import org.coursemate.dao.CourseDAOImpl;
import org.coursemate.model.Course;
import org.coursemate.service.CourseService;

public class CourseController {
    private final CourseService courseService;
    private final CourseDAOImpl courseDAO;

    public CourseController(CourseService courseService, CourseDAOImpl courseDAO) {
        this.courseService = courseService;
        this.courseDAO = courseDAO;
    }

    public void createCourse(String name, String description) {
        Course course = new Course();
        course.setCourseName(name);
        course.setCourseDescription(description);
        courseService.createCourse(course);
        System.out.println("Course created successfully.");
    }

    public void updateCourse(Course updatedCourse) {
        if (updatedCourse != null && updatedCourse.getCourseId() > 0) {
            courseDAO.updateCourse(updatedCourse);
        } else {
            throw new IllegalArgumentException("Invalid course data for update.");
        }
    }

    public void deleteCourse(int deleteId) {
        if (deleteId > 0) {
            courseDAO.deleteCourse(deleteId);
        } else {
            throw new IllegalArgumentException("Invalid course ID for deletion.");
        }
    }

    public Course getCourse(int courseId) {
        if (courseId > 0) {
            return courseDAO.getCourseById(courseId);
        } else {
            throw new IllegalArgumentException("Invalid course ID.");
        }
    }
    }


    // Other CRUD methods

