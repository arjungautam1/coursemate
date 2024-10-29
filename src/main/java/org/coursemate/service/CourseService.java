package org.coursemate.service;

import org.coursemate.dao.CourseDAO;
import org.coursemate.model.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void createCourse(Course course) {
        courseDAO.addCourse(course);
    }

    public Course getCourse(int id) {
        return courseDAO.getCourseById(id);
    }

    public List<Course> listAllCourses() {
        return courseDAO.getAllCourses();
    }

    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    public void deleteCourse(int id) {
        courseDAO.deleteCourse(id);
    }
}
